package com.romvaz.core.store

import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for the Store class.
 *
 * This test class verifies the behavior of the Store implementation, ensuring that:
 * - Actions are properly dispatched and result in correct state updates.
 * - The reducer correctly processes actions to modify the state.
 * - Middleware (side effects) can be integrated into the store.
 * - The store maintains consistency when handling multiple actions.
 *
 * The tests follow the Given-When-Then structure:
 * - **Given**: The initial setup of the store, including its reducer and middleware.
 * - **When**: Actions are dispatched to the store.
 * - **Then**: The state updates as expected.
 */
@ExperimentalCoroutinesApi
class StoreTest {

    private lateinit var store: Store<String, String> // Example: using String as both State and Action
    private lateinit var reducer: Reducer<String, String>
    private lateinit var coroutineScope: TestCoroutineScope
    private lateinit var mockSideEffect: SideEffect<String, String>

    @Before
    fun setUp() {
        // Setup coroutine scope for tests
        coroutineScope = TestCoroutineScope()

        // Setup a simple reducer for testing
        reducer = object : Reducer<String, String> {
            override fun reduce(state: String, action: String): String {
                return state + action
            }
        }

        // Mock middleware
        mockSideEffect = mockk()

        store = Store(
            initialState = "initial",
            reducer = reducer,
            coroutineScope = coroutineScope,
            middleware = listOf(mockSideEffect)
        )
    }

    @Test
    fun `test dispatch action triggers state change`() = runTest {
        store.dispatch("action1")

        // Test that the state has been updated correctly
        val updatedState = store.observe().first()
        assertEquals("initialaction1", updatedState)
    }

    @Test
    fun `test multiple actions and state updates`() = runTest {
        store.dispatch("action1")
        store.dispatch("action2")

        // Verify the final state after multiple actions
        val updatedState = store.observe().first()
        assertEquals("initialaction1action2", updatedState)
    }
}
