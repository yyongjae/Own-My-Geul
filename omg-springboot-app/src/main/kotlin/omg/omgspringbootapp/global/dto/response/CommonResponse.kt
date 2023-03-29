package omg.omgspringbootapp.global.dto.response

import lombok.Builder

@Builder
class CommonResponse (
    val message: String = "성공적으로 요청을 처리하였습니다.",
    val status: Boolean = true
){
    fun response(message: String): CommonResponse {
        return CommonResponse(
            message
        )
    }
}