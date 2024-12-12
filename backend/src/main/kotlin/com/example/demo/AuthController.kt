package com.example.demo

import jakarta.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AuthController(
    @Value("\${credentials.username}") private val username: String,
    @Value("\${credentials.password}") private val password: String
) {

    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: LoginRequest,
        session: HttpSession
    ): String {
        if (loginRequest.username == username && loginRequest.password == password) {
            session.setAttribute("USER", loginRequest.username)
            return "Login successful"
        } else {
            throw IllegalArgumentException("Invalid credentials")
        }
    }

    @PostMapping("/logout")
    fun logout(session: HttpSession): String {
        session.invalidate()
        return "Logout successful"
    }

    @GetMapping("/protected")
    fun protectedResource(session: HttpSession): String {
        val user = session.getAttribute("USER") ?: throw IllegalAccessException("Not logged in")
        return "Hello, $user!"
    }

    data class LoginRequest(val username: String, val password: String)
}
