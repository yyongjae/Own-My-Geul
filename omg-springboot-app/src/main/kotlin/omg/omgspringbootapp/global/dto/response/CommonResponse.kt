package omg.omgspringbootapp.global.dto.response

class CommonResponse (
    val status: Boolean = true,
    val message: String = "성공적으로 요청을 처리하였습니다."
){
    fun response(
        status: Boolean,
        message: String
    ): CommonResponse {
        return CommonResponse(
            status,
            message
        )
    }
}