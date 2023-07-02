package omg.omgspringbootapp.global.dto.response

class CommonFailResponse (
    var status: Boolean = false,
    var message: String = "요청 처리에 실패하였습니다.",
    var exception: Exception
){
}