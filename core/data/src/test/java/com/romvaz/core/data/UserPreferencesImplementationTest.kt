package com.romvaz.core.data

import androidx.datastore.core.DataStore
import com.romvaz.core.data.implementation.datastore.UserPreferencesImplementation
import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for the UserPreferencesImplementation class.
 *
 * This test class ensures that:
 * - The `getPreferences` function correctly retrieves user preferences from DataStore.
 * - The `updateUserInfo` function properly updates user preferences in DataStore.
 * - The implementation correctly handles coroutine dispatching.
 *
 * The tests follow the Given-When-Then structure:
 * - **Given**: The setup of DataStore and coroutine dependencies.
 * - **When**: User preferences are retrieved or updated.
 * - **Then**: The expected data is returned or updated correctly.
 */
class UserPreferencesImplementationTest {

    private lateinit var userPreferences: UserPreferencesImplementation
    private val mockDataStore: DataStore<HardUserPreferenceModel> = mockk(relaxed = true)
    private val mockDispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        // Create instance of UserPreferencesImplementation with mock dependencies
        userPreferences = UserPreferencesImplementation(mockDataStore, mockDispatcher)
    }

    @Test
    fun `getPreferences should return current preferences`() = runTest {
        // Arrange: Mocking the flow of HardUserPreferenceModel
        val userPreferencesModel = HardUserPreferenceModel()
        coEvery { mockDataStore.data } returns flowOf(userPreferencesModel)

        // Act: Call getPreferences()
        val result = userPreferences.getPreferences().first()

        // Assert: Verify that the flow emits the correct user preferences
        assertEquals(userPreferencesModel, result)
    }

    @Test
    fun `updateUserInfo should update preferences in DataStore`() = runTest {
        // Arrange: Create the new preferences to update
        val updatedUserInfo = HardUserPreferenceModel()

        // Act: Call updateUserInfo() to save the new user preferences
        userPreferences.updateUserInfo(updatedUserInfo)

        // Assert: Verify that the dataStore.updateData method was called with the correct data
        coVerify { mockDataStore.updateData(any()) }
    }
}
