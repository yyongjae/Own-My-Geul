package omg.omgspringbootapp.global.exception

enum class OmgException (
    val code: String,
    val message: String
    ){
    // 000번대 회원
    // 100번대 폰트
    NO_SUCH_FONT("BE101", "No Such Font"),
    FAIL_FONT_CREATION("BE102","Cannot Create Font")
}