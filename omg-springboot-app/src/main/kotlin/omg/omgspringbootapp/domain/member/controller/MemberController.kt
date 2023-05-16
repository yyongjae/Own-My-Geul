package omg.omgspringbootapp.domain.member.controller

import omg.omgspringbootapp.domain.member.dto.MemberFormDTO
import omg.omgspringbootapp.domain.member.service.MemberService
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
    fun createMember(@RequestBody @Valid memberFormDTO: MemberFormDTO) {
        memberService.join(memberFormDTO)
    }
}