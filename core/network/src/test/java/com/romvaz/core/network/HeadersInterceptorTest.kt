package com.romvaz.core.network

import com.romvaz.core.network.interceptors.HeadersInterceptor
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import org.junit.Test

class HeadersInterceptorTest {

    private val interceptor = HeadersInterceptor()

    @Test
    fun `test that the accept header is added to the request`() {
        // Mock the Interceptor.Chain
        val chain = mockk<Interceptor.Chain>(relaxed = true)

        // Create a mock request to pass into the chain
        val request = Request.Builder().url("http://example.com").build()

        // Mock the chain's request and response
        every { chain.request() } returns request
        every { chain.proceed(any()) } returns Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .build()

        // Create a slot to capture the request passed to chain.proceed()
        val capturedRequestSlot = slot<Request>()

        // Call the interceptor
        interceptor.intercept(chain)

        // Verify that the request passed to chain.proceed() has the accept header
        verify { chain.proceed(capture(capturedRequestSlot)) }

        // Check that the "accept" header is correctly added
        val addedHeader = capturedRequestSlot.captured.header("accept")
        assertEquals("application/json", addedHeader)
    }
}
