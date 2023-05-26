package omg.omgspringbootapp.domain.member.controller

import omg.omgspringbootapp.domain.member.dto.request.LoginRequest
import omg.omgspringbootapp.domain.member.dto.request.JoinRequest
import omg.omgspringbootapp.domain.member.dto.response.LoginResponse
import omg.omgspringbootapp.domain.member.service.MemberService
import omg.omgspringbootapp.global.dto.response.CommonResponse
import omg.omgspringbootapp.global.dto.response.CommonDataResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService
) {
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

    @GetMapping("/test")
    fun test(
        request: HttpServletRequest
    ): ResponseEntity<CommonResponse> {
        val memberId = request.getAttribute("memberId")
        println(memberId)
        return ResponseEntity.ok(CommonResponse().response(true, "테스트 요청 성공"))
    }
}