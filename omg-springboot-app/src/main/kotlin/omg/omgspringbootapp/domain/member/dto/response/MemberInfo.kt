package omg.omgspringbootapp.domain.member.dto.response

import javax.validation.constraints.NotBlank

data class MemberInfo(
    @field: NotBlank
    val name: String,
    @field: NotBlank
    val id: String
) {
}