package com.romvaz.core.store

/**
 * Reducer interface that should be used to indicate to a store how to react to different actions
 * triggered, it will normally take the original state and produce a new one.
 */
interface Reducer<State, Action> {

    /**
     * This method is called everytime that an action is triggered on a store.
     *
     * @param state current state of the storage when the action was triggered
     * @param action action that triggered this method
     *
     * @return The new state after the action was processed.
     */
    fun reduce(state: State, action: Action): State
}
