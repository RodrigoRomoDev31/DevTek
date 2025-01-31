package com.romvaz.core.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.romvaz.core.data.implementation.network.InternetStatusImplementation
import com.romvaz.core.domain.network.InternetStatusService
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for the InternetStatusService implementation.
 *
 * This test class verifies that:
 * - `theresInternet` correctly returns `true` when an active network with internet capabilities is available.
 * - `theresInternet` correctly returns `false` when no active network or internet capabilities are detected.
 *
 * The tests use mock Context, ConnectivityManager, Network, and NetworkCapabilities to simulate network conditions
 * and validate the behavior of the service under different scenarios (internet available or not).
 */
class InternetStatusImplementationTest {

    private lateinit var internetStatusService: InternetStatusService
    private val mockContext: Context = mockk()
    private var mockConnectivityManager: ConnectivityManager = mockk()
    private val mockNetworkCapabilities: NetworkCapabilities = mockk()
    private var mockNetwork: Network = mockk()

    @Before
    fun setUp() {
        // Mocking the context and connectivity manager
        mockConnectivityManager = mockk(relaxed = true)
        mockNetwork = mockk(relaxed = true)
        every { mockContext.getSystemService(Context.CONNECTIVITY_SERVICE) } returns mockConnectivityManager
        internetStatusService = InternetStatusImplementation(mockContext)
    }

    @Test
    fun `theresInternet should return true when internet is available`() = runTest {
        // Arrange: Mock that there is an active network with internet capabilities
        every { mockConnectivityManager.activeNetwork } returns mockNetwork
        every { mockConnectivityManager.getNetworkCapabilities(mockNetwork) } returns mockNetworkCapabilities
        every { mockNetworkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) } returns true

        // Act: Call theresInternet
        val result = internetStatusService.theresInternet()

        // Assert: Verify that it returns true when internet is available
        assertTrue(result)
    }

    @Test
    fun `theresInternet should return false when no internet available`() = runTest {
        // Arrange: Mock that there is no active network
        every { mockConnectivityManager.activeNetwork } returns null
        every { mockConnectivityManager.getNetworkCapabilities(null) } returns null

        // Act: Call theresInternet
        val result = internetStatusService.theresInternet()

        // Assert: Verify that it returns false when no internet is available
        assertFalse(result)
    }
}
