package omg.omgspringbootapp.domain.member.dto

import omg.omgspringbootapp.domain.member.entity.Member
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class MemberFormDTO(
    @field: Email(message = "이메일은 필수입니다.")
    var email: String,
    @field: NotBlank
    val password: String,
    @field: NotBlank
    val name: String
) {
    fun toEntity(): Member {
        return Member(email, password, name)
    }
}