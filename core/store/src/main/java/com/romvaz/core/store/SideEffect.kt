package com.romvaz.core.store

import kotlinx.coroutines.flow.Flow

interface SideEffect<State, Action>

abstract class StateSideEffect<State, Action> : SideEffect<State, Action> {
    abstract fun observe(stateFlow: Flow<State>): Flow<Action>
}

abstract class ActionSideEffect<State, Action> : SideEffect<State, Action> {
    abstract fun observe(actionsFlow: Flow<Action>, currentState: () -> State): Flow<Action>
}
