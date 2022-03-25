package com.ahold.triggers.trigger2

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.specification.RequestSpecification
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.server.LocalServerPort


@Tag("ComponentTests")
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("build-local")
internal class Trigger2ControllerIntegrationTest {

    @Value("\${test.hostUrl}")
    lateinit var hostUrl: String

    @LocalServerPort
    val localPort: Int = 0

    lateinit var request: RequestSpecification

    @BeforeEach
    fun setUp() {
        request = Given {
            baseUri(hostUrl)
            port(localPort)
            basePath("/trigger2")
            log().ifValidationFails()
            accept(ContentType.JSON)
            contentType(ContentType.JSON)
        }
    }

    @Test
    fun `Should return a trigger object` () {

        request When {
            queryParam("trigger", "Hello")
            get()
        } Then {
            statusCode(200)
        }
    }

    @Test
    fun `Should return a trigger object and expect failure` () {

        request When {
            queryParam("trigger", "Hello")
            get()
        } Then {
            statusCode(400)
        }
    }

}