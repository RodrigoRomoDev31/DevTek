package com.romvaz.core.network.utils

// Sealed class representing different types of API responses: Success, HttpError, and Error.
sealed class ApiResponse<out T> {
    // Success response containing the data of type T
    // To maintain code quality, user RESULT
    data class Success<out T>(val data: T) : ApiResponse<T>()

    // HTTP error response with error details
    //Modified if needed
    data class HttpError(
        val errorJson: String?,
        val statusMessage: String,
        val httpCode: HttpCode
    ) : ApiResponse<Nothing>()

    // Generic error response for exceptions that occur during the API call
    data class Error(val exception: Throwable) : ApiResponse<Nothing>()
}

// Function to safely execute a Retrofit API call, wrapping the result in ApiResponse
@Suppress("TooGenericExceptionCaught")
inline fun <D> safeCall(
    execute: () -> retrofit2.Response<D>
): ApiResponse<D> {
    return try {
        // Executes the API call and converts it to ApiResponse
        execute().asApiResponse()
    } catch (e: Exception) {
        // If an exception occurs, wrap it in an ApiResponse.Error
        ApiResponse.Error(e)
    }
}

// Extension function to convert Retrofit's Response into an ApiResponse
fun <D> retrofit2.Response<D>.asApiResponse(): ApiResponse<D> {
    return if (isSuccessful) {
        // If the response is successful, return the body in a Success response
        body()?.let { data ->
            ApiResponse.Success(data = data)
        } ?: run {
            // If the body is null, return an error indicating the body is missing
            ApiResponse.Error(NullPointerException("Body is null"))
        }
    } else {
        // If the response is not successful, return an HttpError with relevant error details
        ApiResponse.HttpError(
            errorJson = errorBody()?.string(),
            statusMessage = message(),
            httpCode = HttpCode.withCode(code())
        )
    }
}

// Custom exception for HTTP errors containing error details
class HttpErrorException(
    val errorJson: String?,
    val statusMessage: String,
    val httpCode: HttpCode
) : Exception()

// Extension function to convert ApiResponse into Kotlin's Result type for more functional error handling
fun <R> ApiResponse<R>.asResult(): Result<R> {
    return when (this) {
        is ApiResponse.Error -> Result.failure(exception) // Wraps Error in a failure Result
        is ApiResponse.HttpError -> Result.failure(
            HttpErrorException(
                errorJson = errorJson,
                statusMessage = statusMessage,
                httpCode = httpCode
            )
        )
        is ApiResponse.Success -> Result.success(data) // Wraps Success in a success Result
    }
}

// Enum class representing HTTP status codes for easy matching and handling
@Suppress("MagicNumber")
enum class HttpCode(val value: Int) {
    BadRequest(400),
    Unauthorized(401),
    Forbidden(403),
    NotFound(404),
    MethodNotAllowed(405),
    NotAcceptable(406),
    RequestTimeout(408),
    Conflict(409),
    InternalServerError(500),
    NotImplemented(501),
    BadGateway(502),
    ServiceUnavailable(503),
    GatewayTimeout(504),
    Unknown(-1);

    companion object {
        // Maps HTTP status codes to their corresponding enum values
        private val map = entries.associateBy(HttpCode::value)

        // Helper function to get an HttpCode by its code, returning Unknown for unrecognized codes
        fun withCode(errorCode: Int) = map[errorCode] ?: Unknown
    }
}

