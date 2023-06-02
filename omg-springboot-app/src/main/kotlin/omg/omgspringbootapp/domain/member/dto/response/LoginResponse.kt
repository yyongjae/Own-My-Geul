package omg.omgspringbootapp.domain.member.dto.response

import omg.omgspringbootapp.global.dto.TokenInfo
import javax.validation.constraints.NotBlank

data class LoginResponse(
    @field: NotBlank
    val memberInfo: MemberInfo,
    @field: NotBlank
    val tokenInfo: TokenInfo
){
}