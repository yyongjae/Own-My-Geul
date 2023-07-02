package omg.omgspringbootapp.domain.member.dto.request

import omg.omgspringbootapp.domain.member.entity.Member
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class JoinRequest(
    @field: Email(message = "이메일 형식에 맞춰서 작성했는지 확인하세요.")
    @field: NotBlank(message = "이메일은 필수 입력 항목입니다.")
    val email: String,
    @field: NotBlank(message = "비밀번호는 필수입니다.")
    val password: String,
    @field: NotBlank(message = "이름은 필수입니다.")
    val name: String
) {
    fun toEntity(): Member {
        return Member(
            password = password,
            name = name,
            email = email
        )
    }
}