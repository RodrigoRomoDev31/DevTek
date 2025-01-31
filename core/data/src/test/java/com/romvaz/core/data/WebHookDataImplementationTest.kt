package com.romvaz.core.data

import com.romvaz.core.data.implementation.api.WebHookDataImplementation
import com.romvaz.core.domain.api.WebHookApi
import com.romvaz.core.domain.api.weebhook.WebHookDataService
import com.romvaz.core.domain.models.api.requests.CurrentLocationModel
import com.romvaz.core.domain.models.api.requests.SendHelpPostModel
import com.romvaz.core.domain.models.api.requests.SendLocationPostModel
import com.romvaz.core.domain.models.api.response.WebHookResponseModel
import com.romvaz.core.network.utils.ApiResponse
import com.romvaz.core.network.utils.HttpCode
import com.romvaz.core.network.utils.HttpErrorException
import com.romvaz.core.network.utils.asApiResponse
import com.romvaz.core.network.utils.asResult
import com.romvaz.core.network.utils.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

/**
 * Unit tests for the WebHookDataImplementation class.
 *
 * This test class ensures that:
 * - API requests to send help and location data are handled correctly.
 * - API responses are properly processed, including success and error cases.
 * - The `safeCall` function correctly wraps API responses into `ApiResponse`.
 * - The `asApiResponse` function correctly maps Retrofit responses to `ApiResponse`.
 * - The `asResult` function properly converts `ApiResponse` into Kotlin’s `Result`.
 * - The implementation correctly integrates with coroutines and dependency injections.
 *
 * The tests follow the Given-When-Then structure:
 * - **Given**: The setup of API dependencies and test cases.
 * - **When**: API requests are made.
 * - **Then**: The expected responses and behaviors are validated.
 */
class WebHookDataImplementationTest {

    // Mock instance of WebHookApi for testing
    @Mock
    private lateinit var webHookApi: WebHookApi

    private lateinit var webHookDataService: WebHookDataService
    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this)

        // Set the main dispatcher for testing
        Dispatchers.setMain(testDispatcher)

        // Initialize the service with the mocked API and test dispatcher
        webHookDataService = WebHookDataImplementation(webHookApi, testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Reset the main dispatcher to its original state after tests
        Dispatchers.resetMain()
    }

    @Test
    fun `test sendHelp success`() = runTest {
        // Arrange: Mock the API response
        val sendHelpPostModel = SendHelpPostModel(
            "",
            "",
            "",
            "", CurrentLocationModel(0.0, 0.0),
            ""
        )

        val response = WebHookResponseModel(true)

        `when`(webHookApi.sendHelp(sendHelpPostModel)).thenReturn(Response.success(response))

        // Act
        val result = webHookDataService.sendHelp(sendHelpPostModel)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(response, result.getOrNull())
    }

    @Test
    fun `test sendHelp failure`() = runTest {
        // Arrange: Mock the API response
        val sendHelpPostModel = SendHelpPostModel(
            "",
            "",
            "",
            "", CurrentLocationModel(0.0, 0.0),
            ""
        )

        // Simulate an error response from the API (e.g., HTTP 400)
        val errorResponse = "{\"error\": \"Not Found\"}".toResponseBody()
        val httpCode = HttpCode.NotFound
        val response = Response.error<WebHookResponseModel>(httpCode.value, errorResponse)

        // Mock the webHookApi to return the error response
        `when`(webHookApi.sendHelp(sendHelpPostModel)).thenReturn(response)

        // Act: Call the sendHelp method
        val result = webHookDataService.sendHelp(sendHelpPostModel)

        // Assert: Verify that the result is a failure
        assertTrue(result.isFailure)

        // Verify the exception is thrown when accessing the failure result
        val exception = result.exceptionOrNull()
        assertTrue(exception is HttpErrorException)
        assertEquals(httpCode, (exception as HttpErrorException).httpCode)
    }

    @Test
    fun `test sendLocation success`() = runTest {
        val sendLocationPostModel = SendLocationPostModel(
            0.0,
            0.0,
            0L,
            0f
        )

        val response = WebHookResponseModel(true)

        `when`(webHookApi.sendLocation(sendLocationPostModel)).thenReturn(Response.success(response))

        // Act
        val result = webHookDataService.sendLocation(sendLocationPostModel)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(response, result.getOrNull())
    }

    @Test
    fun `test sendLocation failure`() = runTest {
        val sendLocationPostModel = SendLocationPostModel(
            0.0,
            0.0,
            0L,
            0f
        )

        // Simulate an error response from the API (e.g., HTTP 400)
        val errorResponse = "{\"error\": \"Not Found\"}".toResponseBody()
        val httpCode = HttpCode.NotFound
        val response = Response.error<WebHookResponseModel>(httpCode.value, errorResponse)

        // Mock the webHookApi to return the error response
        `when`(webHookApi.sendLocation(sendLocationPostModel)).thenReturn(response)

        // Act: Call the sendHelp method
        val result = webHookDataService.sendLocation(sendLocationPostModel)

        // Assert: Verify that the result is a failure
        assertTrue(result.isFailure)

        // Verify the exception is thrown when accessing the failure result
        val exception = result.exceptionOrNull()
        assertTrue(exception is HttpErrorException)
        assertEquals(httpCode, (exception as HttpErrorException).httpCode)
    }

    @Test
    fun `safeCall returns Success when API call is successful`() = runTest(testDispatcher) {
        // Arrange
        val mockData = "Success Data"
        val response = Response.success(mockData)

        // Act
        val result = safeCall { response }

        // Assert
        assertTrue(result is ApiResponse.Success)
        assertEquals(mockData, (result as ApiResponse.Success).data)
    }

    @Test
    fun `safeCall returns HttpError when API call returns error`() = runTest(testDispatcher) {
        // Arrange
        val errorResponse = "{\"error\": \"Bad Request\"}".toResponseBody()
        val httpCode = HttpCode.BadRequest
        val response = Response.error<String>(httpCode.value, errorResponse)

        // Act
        val result = safeCall { response }

        // Assert
        assertTrue(result is ApiResponse.HttpError)
        assertEquals(httpCode, (result as ApiResponse.HttpError).httpCode)
    }

    @Test
    fun `asApiResponse returns Success when response is successful`() = runTest(testDispatcher) {
        // Arrange
        val mockData = "Success Data"
        val response = Response.success(mockData)

        // Act
        val result = response.asApiResponse()

        // Assert
        assertTrue(result is ApiResponse.Success)
        assertEquals(mockData, (result as ApiResponse.Success).data)
    }

    @Test
    fun `asApiResponse returns HttpError when response is not successful`() = runTest(testDispatcher) {
        // Arrange
        val errorResponse = "{\"error\": \"Bad Request\"}".toResponseBody()
        val httpCode = HttpCode.BadRequest
        val response = Response.error<String>(httpCode.value, errorResponse)

        // Act
        val result = response.asApiResponse()

        // Assert
        assertTrue(result is ApiResponse.HttpError)
        assertEquals(httpCode, (result as ApiResponse.HttpError).httpCode)
    }

    @Test
    fun `asApiResponse returns Error when body is null`() = runTest(testDispatcher) {
        // Arrange
        val response = Response.success<String>(null)

        // Act
        val result = response.asApiResponse()

        // Assert
        assertTrue(result is ApiResponse.Error)
        assertTrue((result as ApiResponse.Error).exception is NullPointerException)
    }

    @Test
    fun `asResult returns Success when ApiResponse is Success`() = runTest(testDispatcher) {
        // Arrange
        val mockData = "Success Data"
        val apiResponse = ApiResponse.Success(mockData)

        // Act
        val result = apiResponse.asResult()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(mockData, result.getOrNull())
    }

    @Test
    fun `asResult returns Failure when ApiResponse is HttpError`() = runTest(testDispatcher) {
        // Arrange
        val errorJson = "{\"error\": \"Bad Request\"}"
        val statusMessage = "Bad Request"
        val httpCode = HttpCode.BadRequest
        val apiResponse = ApiResponse.HttpError(errorJson, statusMessage, httpCode)

        // Act
        val result = apiResponse.asResult()

        // Assert
        assertTrue(result.isFailure)
        val exception = result.exceptionOrNull()
        assertTrue(exception is HttpErrorException)
        assertEquals(httpCode, (exception as HttpErrorException).httpCode)
    }

    @Test
    fun `asResult returns Failure when ApiResponse is Error`() = runTest(testDispatcher) {
        // Arrange
        val exception = RuntimeException("Network error")
        val apiResponse = ApiResponse.Error(exception)

        // Act
        val result = apiResponse.asResult()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
