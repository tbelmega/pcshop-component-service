package de.oncoding.pcshop.componentservice

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(Throwable::class)
    fun handleError(request: HttpServletRequest, exception: Throwable): ResponseEntity<ErrorInfo> {

        exception.printStackTrace()

        val (code, message) = when (exception) {
            is BadRequestException -> HttpStatus.BAD_REQUEST to exception.message
            is NotFoundException -> HttpStatus.NOT_FOUND to exception.message
            else -> throw exception
        }

        val errorInfo = ErrorInfo(error = message ?: "", path = request.requestURI)

        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)

        return ResponseEntity(errorInfo, headers, code)
    }

}

data class ErrorInfo(val error: String,
                     val path: String? = null)

class NotFoundException(message: String = "Not Found") : Exception(message)
class BadRequestException(message: String = "Bad Request") : Exception(message)

