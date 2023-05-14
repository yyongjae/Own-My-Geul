package omg.omgspringbootapp.domain.member.service

import omg.omgspringbootapp.domain.member.dto.MemberFormDTO
import omg.omgspringbootapp.domain.member.entity.Member
import omg.omgspringbootapp.domain.member.exception.NoSuchMemberException
import omg.omgspringbootapp.domain.member.repository.MemberRepository
import omg.omgspringbootapp.global.exception.OmgException
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun join(memberFormDTO: MemberFormDTO): UUID? {
        val member = memberRepository.save(memberFormDTO.toEntity())
        return member.id
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