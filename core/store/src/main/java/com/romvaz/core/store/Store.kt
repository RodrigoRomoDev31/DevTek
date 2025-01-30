package com.romvaz.core.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Store class represents a storage for a state object, this state can represent any type of state
 * that should be maintained in memory and that represents the state of a component.
 *
 * @param initialState Initial state of the store that would be used as default state when no other
 * action has being executed on the state.
 * @param reducer implementation of the Reducer interface that tells the store how to manage each action
 * and how the action should affect the state based on the action
 * @param coroutineScope coroutine scope that should be used to process the state on the store
 * @param middleware list of side effects that can run asynchronously in response of an state change or an
 * action triggered on the store.
 */
class Store<State, Action>(
    initialState: State,
    private val reducer: Reducer<State, Action>,
    private val coroutineScope: CoroutineScope,
    private val middleware: List<SideEffect<State, Action>> = emptyList(),
) {

    private val actionsFlow = MutableSharedFlow<Action>(
        extraBufferCapacity = 10,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val _stateFlow = MutableStateFlow(initialState)

    init {
        observeActions()
        attachSideEffects()
    }

    /**
     * Triggers an action in the store that can trigger a middleware or a change in the state
     * depending on the dispatcher and middleware configurations
     *
     * @param action action that will be sent to the dispatcher or to any middleware listening for
     * this specific action
     */
    fun dispatch(action: Action) {
        coroutineScope.launch {
            actionsFlow.emit(action)
        }
    }

    /**
     * This method should be used to listen for changes in the state and should be used to update
     * the UI or the relevant behaviour of a component.
     */
    fun observe(): StateFlow<State> = _stateFlow

    fun latest(): State = _stateFlow.value

    private fun observeActions() {
        coroutineScope.launch {
            actionsFlow.collect { action ->
                _stateFlow.update { currentState -> reducer.reduce(currentState, action) }
            }
        }
    }

    private fun attachSideEffects() {
        middleware.forEach { sideEffect ->
            when (sideEffect) {
                is StateSideEffect -> attachStateSideEffect(sideEffect)
                is ActionSideEffect -> attachActionSideEffect(sideEffect)
            }
        }

    }

    private fun attachStateSideEffect(sideEffect: StateSideEffect<State, Action>) {
        coroutineScope.launch {
            sideEffect.observe(_stateFlow).collect { dispatch(it) }
        }
    }

    private fun attachActionSideEffect(sideEffect: ActionSideEffect<State, Action>) {
        coroutineScope.launch {
            sideEffect.observe(actionsFlow, this@Store::latest).collect { dispatch(it) }
        }
    }
}
