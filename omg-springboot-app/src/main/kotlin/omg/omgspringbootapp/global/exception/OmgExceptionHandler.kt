package omg.omgspringbootapp.global.exception

import io.jsonwebtoken.ExpiredJwtException
import omg.omgspringbootapp.global.dto.response.CommonFailResponse
import omg.omgspringbootapp.global.dto.response.CommonResponse
import omg.omgspringbootapp.global.exception.jwt.InvalidTokenException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class OmgExceptionHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(OmgCommonException::class)
    fun handleBaseException(e: OmgCommonException): ResponseEntity<CommonResponse>{
        log.error("Omg Exception Code : " + e.getErrorCode())
        log.error("Omg Exception Message : " + e.message)

        return ResponseEntity.ok(e.message?.let { CommonResponse().response(false, it) })
    }

    @ExceptionHandler(MethodArgumentNotValidException::class) // validation 에러
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<CommonResponse>{
        log.error("Exception Message : " + e.message)

        val bindingResult = e.bindingResult
        val fieldError = bindingResult.fieldError
        val errorMessage = fieldError?.defaultMessage ?: "잘못된 형식이 포함되어 있습니다."

        return ResponseEntity.ok(CommonResponse().response(false, errorMessage))
    }

    @ExceptionHandler(InvalidTokenException::class) // Jwt 에러
    fun handleInvalidTokenException(e: InvalidTokenException): ResponseEntity<CommonFailResponse>{
        log.error("Omg Exception Message : " + e.message)

        return ResponseEntity.ok(CommonFailResponse(false, "유효하지 않은 토큰입니다.", e))
    }

    @ExceptionHandler(IllegalArgumentException::class) // validation 에러
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<CommonResponse>{
        log.error("Exception Message : " + e.message)

        return ResponseEntity.ok(e.message?.let { CommonResponse().response(false, it) })
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<CommonResponse>{
        log.error("Exception Message : " + e.message)

        return ResponseEntity.ok(CommonResponse().response(false, OmgException.INTERNAL_SERVER_ERROR.message))
    }
}