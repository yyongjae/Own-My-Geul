package omg.omgspringbootapp.global.dto.response

class CommonDataResponse<T> (
    val status: Boolean,
    val message: String = "성공적으로 요청을 처리하였습니다.",
    val data: T
) {
}