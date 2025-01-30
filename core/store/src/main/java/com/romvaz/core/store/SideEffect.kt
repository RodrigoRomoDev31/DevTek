package com.romvaz.core.store

import kotlinx.coroutines.flow.Flow

interface SideEffect<State, Action>

/**
 * This type of side effect is triggered everytime that the state changes on a store, it can trigger
 * an async task that may or may not trigger an action at the end that can make a new update to the
 * store state.
 */
abstract class StateSideEffect<State, Action> : SideEffect<State, Action> {

    abstract fun observe(stateFlow: Flow<State>): Flow<Action>
}

/**
 * This type of side effect is triggered everytime that an action is emitted by a store, it can trigger
 * an async task that may or may not trigger an action at the end that can make a new update to the
 * store state.
 */
abstract class ActionSideEffect<State, Action> : SideEffect<State, Action> {
    abstract fun observe(actionsFlow: Flow<Action>, currentState: () -> State): Flow<Action>
}
