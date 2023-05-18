package omg.omgspringbootapp.domain.member.service

import omg.omgspringbootapp.domain.member.dto.request.LoginRequest
import omg.omgspringbootapp.domain.member.dto.request.JoinRequest
import omg.omgspringbootapp.domain.member.dto.response.LoginResponse
import omg.omgspringbootapp.domain.member.entity.Member
import omg.omgspringbootapp.domain.member.exception.NoSuchMemberException
import omg.omgspringbootapp.domain.member.exception.NotMatchPassword
import omg.omgspringbootapp.domain.member.repository.MemberRepository
import omg.omgspringbootapp.global.exception.OmgException
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun join(joinRequest: JoinRequest): UUID? {
        val member = memberRepository.save(joinRequest.toEntity())
        return member.id
    }

    fun login(loginRequest: LoginRequest): LoginResponse {
        val member = findByEmail(loginRequest.email)

        if (member.password != loginRequest.password) {
            throw NotMatchPassword("비밀번호가 일치하지 않습니다.", OmgException.NOT_MATCH_PASSWORD)
        }

        return LoginResponse(
            member.email,
            member.name
        )
    }

    fun findById(id: UUID): Member {
        return memberRepository.findById(id)
            .orElseThrow {
                NoSuchMemberException("회원이 존재하지 않습니다.", OmgException.NO_SUCH_MEMBER)
            }
    }

    fun findByEmail(email: String): Member {
        return memberRepository.findByEmail(email)
            .orElseThrow{
                NoSuchMemberException("회원이 존재하지 않습니다.", OmgException.NO_SUCH_MEMBER)
            }
    }
}