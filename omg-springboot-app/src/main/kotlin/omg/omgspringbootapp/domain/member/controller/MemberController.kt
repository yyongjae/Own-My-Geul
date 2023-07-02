package omg.omgspringbootapp.domain.member.controller

import omg.omgspringbootapp.domain.member.dto.request.JoinRequest
import omg.omgspringbootapp.domain.member.dto.request.LoginRequest
import omg.omgspringbootapp.domain.member.dto.response.LoginResponse
import omg.omgspringbootapp.domain.member.service.MemberService
import omg.omgspringbootapp.global.dto.TokenInfo
import omg.omgspringbootapp.global.dto.response.CommonDataResponse
import omg.omgspringbootapp.global.dto.response.CommonResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/")
    fun hello(
    ): String {
        return "hello"
    }

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid loginRequest: LoginRequest
    ): ResponseEntity<CommonDataResponse<LoginResponse>> {
        val response = memberService.login(loginRequest)

        return ResponseEntity.ok(CommonDataResponse(true, "로그인을 성공하였습니다.", response))
    }

    @PostMapping("/join")
    fun createMember(
        @RequestBody @Valid joinRequest: JoinRequest
    ): ResponseEntity<CommonResponse> {
        memberService.join(joinRequest)

        return ResponseEntity.ok(CommonResponse().response(true, "회원 가입을 성공하였습니다."))
    }

    @GetMapping("/reissue/{id}")
    fun reissue(
        @PathVariable id: String
    ): ResponseEntity<CommonDataResponse<TokenInfo>> {
        val tokenInfo = memberService.reissueToken(id)

        return ResponseEntity.ok(CommonDataResponse(true, "토큰 재발급을 성공하였습니다.", tokenInfo))
    }
}