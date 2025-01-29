package com.romvaz.core.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    fun dispatch(action: Action) {
        coroutineScope.launch {
            actionsFlow.emit(action)
        }
    }

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
