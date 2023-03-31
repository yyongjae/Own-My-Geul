package omg.omgspringbootapp.global.exception

import org.springframework.http.HttpStatus

abstract class OmgCommonException(message: String) : RuntimeException(message) {
    // Enum 타입에 지정한 에러코드
    abstract fun getErrorCode(): String

    // HttpStatus Code
    abstract fun getHttpStatus(): HttpStatus
}