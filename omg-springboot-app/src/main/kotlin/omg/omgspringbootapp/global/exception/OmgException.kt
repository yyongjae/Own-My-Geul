package omg.omgspringbootapp.global.exception

enum class OmgException (
    val code: String,
    val message: String
    ){
    // 000번대 회원
    NO_SUCH_MEMBER("BE001", "No Such Member"),
    NOT_MATCH_PASSWORD("BE002", "Incorrect Password"),
    MEMBER_ALREADY_EXIST("BE003", "Member Already Exists"),
    // 100번대 폰트
    NO_SUCH_FONT("BE101", "No Such Font"),
    FAIL_FONT_CREATION("BE102","Cannot Create Font"),
    // 200번대 토큰
    INVALID_TOKEN("BE200", "Invalid Token"),
    EXPIRED_ACCESS_TOKEN("BE201", "Expired Access Token"),
    // 500번대 서버에러
    INTERNAL_SERVER_ERROR("BE500", "Internal Server Error")
}