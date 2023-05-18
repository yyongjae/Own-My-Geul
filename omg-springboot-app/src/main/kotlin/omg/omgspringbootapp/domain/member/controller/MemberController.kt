package omg.omgspringbootapp.domain.member.controller

import omg.omgspringbootapp.domain.member.dto.MemberFormDTO
import omg.omgspringbootapp.domain.member.service.MemberService
import omg.omgspringbootapp.global.dto.response.CommonResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/join")
    fun createMember(
        @RequestBody @Valid memberFormDTO: MemberFormDTO
    ): ResponseEntity<CommonResponse> {
        memberService.join(memberFormDTO)

        return ResponseEntity.ok(CommonResponse().response(false, "회원 가입을 성공하였습니다."))
    }
}