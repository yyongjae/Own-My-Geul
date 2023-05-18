package omg.omgspringbootapp.domain.member.dto.response

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class LoginResponse(
    @field: Email
    @field: NotBlank
    val email: String,
    @field: NotBlank
    val name: String
) {
}