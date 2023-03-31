package omg.omgspringbootapp.global.exception

import lombok.extern.slf4j.Slf4j
import omg.omgspringbootapp.global.dto.response.CommonResponse
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class OmgExceptionHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(OmgCommonException::class)
    fun handleBaseException(e: OmgCommonException): ResponseEntity<CommonResponse>{
        log.warn("Omg Exception Code : " + e.getErrorCode())
        log.warn("Omg Exception Message : " + e.message)

        return ResponseEntity.ok(e.message?.let { CommonResponse().response(false, it) })
    }
}