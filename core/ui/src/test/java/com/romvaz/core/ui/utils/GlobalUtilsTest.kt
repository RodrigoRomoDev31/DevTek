package com.romvaz.core.ui.utils

import com.romvaz.core.ui.utils.GlobalUtils.validateMail
import com.romvaz.core.ui.utils.GlobalUtils.validatePassword
import org.junit.Assert
import org.junit.Test

class GlobalUtilsTest {

    @Test
    fun `validateMail should return true for valid email`() {
        // Given
        val validEmail = "test@example.com"

        // When
        val result = validEmail.validateMail()

        // Then
        Assert.assertTrue(result)
    }

    @Test
    fun `validateMail should return false for invalid email`() {
        // Given
        val invalidEmail = "invalid-email"

        // When
        val result = invalidEmail.validateMail()

        // Then
        Assert.assertFalse(result)
    }

    @Test
    fun `validatePassword should return true for valid password`() {
        // Given
        val validPassword = "Password.123!"

        // When
        val result = validPassword.validatePassword()

        // Then
        Assert.assertTrue(result)
    }

    @Test
    fun `validatePassword should return false for invalid password`() {
        // Given
        val invalidPassword = "weak"

        // When
        val result = invalidPassword.validatePassword()

        // Then
        Assert.assertFalse(result)
    }
}
