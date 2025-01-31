package com.romvaz.core.network.utils

/**
 * Sealed class representing different types of API responses: Success, HttpError, and Error.
 * This class helps in handling API responses in a structured way, making error handling and
 * data retrieval more predictable.
 */
sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()

    data class HttpError(
        val errorJson: String?,
        val statusMessage: String,
        val httpCode: HttpCode
    ) : ApiResponse<Nothing>()

    data class Error(val exception: Throwable) : ApiResponse<Nothing>()
}

@Suppress("TooGenericExceptionCaught")
inline fun <D> safeCall(
    execute: () -> retrofit2.Response<D>
): ApiResponse<D> {
    return try {
        execute().asApiResponse()
    } catch (e: Exception) {
        ApiResponse.Error(e)
    }
}

fun <D> retrofit2.Response<D>.asApiResponse(): ApiResponse<D> {
    return if (isSuccessful) {
        body()?.let { data ->
            ApiResponse.Success(data = data)
        } ?: run {
            ApiResponse.Error(NullPointerException("Body is null"))
        }
    } else {
        ApiResponse.HttpError(
            errorJson = errorBody()?.string(),
            statusMessage = message(),
            httpCode = HttpCode.withCode(code())
        )
    }
}

class HttpErrorException(
    val errorJson: String?,
    val statusMessage: String,
    val httpCode: HttpCode
) : Exception()

fun <R> ApiResponse<R>.asResult(): Result<R> {
    return when (this) {
        is ApiResponse.Error -> Result.failure(exception)
        is ApiResponse.HttpError -> Result.failure(
            HttpErrorException(
                errorJson = errorJson,
                statusMessage = statusMessage,
                httpCode = httpCode
            )
        )
        is ApiResponse.Success -> Result.success(data)
    }
}

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
        private val map = entries.associateBy(HttpCode::value)

        fun withCode(errorCode: Int) = map[errorCode] ?: Unknown
    }
}

