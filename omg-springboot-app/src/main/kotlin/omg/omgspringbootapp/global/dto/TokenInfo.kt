package omg.omgspringbootapp.global.dto

import javax.validation.constraints.NotBlank

data class TokenInfo(
    @field: NotBlank
    val grantType: String,
    @field: NotBlank
    val accessToken: String,
    @field: NotBlank
    val refreshToken: String
) {
}