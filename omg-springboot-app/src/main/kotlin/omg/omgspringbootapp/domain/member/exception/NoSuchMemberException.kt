package omg.omgspringbootapp.domain.member.exception

import omg.omgspringbootapp.global.exception.OmgCommonException
import omg.omgspringbootapp.global.exception.OmgException
import org.springframework.http.HttpStatus

class NoSuchMemberException(
    message: String,
    omgException: OmgException
) :OmgCommonException(message) {
    private val omgException: OmgException

    init {
        this.omgException = omgException
    }

    override fun getErrorCode(): String {
        return omgException.code
    }

    override fun getHttpStatus(): HttpStatus {
        return HttpStatus.OK
    }
}