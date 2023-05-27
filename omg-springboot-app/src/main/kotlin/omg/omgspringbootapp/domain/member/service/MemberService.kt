package omg.omgspringbootapp.domain.member.service

import omg.omgspringbootapp.domain.member.dto.request.LoginRequest
import omg.omgspringbootapp.domain.member.dto.request.JoinRequest
import omg.omgspringbootapp.domain.member.dto.response.LoginResponse
import omg.omgspringbootapp.domain.member.dto.response.MemberInfo
import omg.omgspringbootapp.domain.member.entity.Member
import omg.omgspringbootapp.domain.member.exception.NoSuchMemberException
import omg.omgspringbootapp.domain.member.exception.NotMatchPassword
import omg.omgspringbootapp.domain.member.repository.MemberRepository
import omg.omgspringbootapp.global.dto.TokenInfo
import omg.omgspringbootapp.global.exception.OmgException
import omg.omgspringbootapp.global.utils.jwt.JwtUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
    private val jwtUtil: JwtUtil
) {
    @Transactional
    fun join(joinRequest: JoinRequest): UUID? {
        val member = memberRepository.save(joinRequest.toEntity())
        return member.id
    }

    @Transactional
    fun login(loginRequest: LoginRequest): LoginResponse {
        val member = findByEmail(loginRequest.email)
        val memberId = member.id!! // null이 아님을 확신할 때 사용

        // 비밀번호 확인
        checkPassword(member, loginRequest)

        // access token, refresh token 발급
        val accessToken = jwtUtil.generateAccessToken(memberId)
        val refreshToken = jwtUtil.generateRefreshToken(memberId)

        // refresh token 저장
        member.updateRefreshToken(refreshToken)

        val memberInfo = MemberInfo(
            member.name
        )
        val tokenInfo = TokenInfo(
            "Bearer",
            accessToken,
            refreshToken
        )

        return LoginResponse(
            memberInfo,
            tokenInfo
        )
    }

    private fun checkPassword(member: Member, loginRequest: LoginRequest) {
        if (member.password != loginRequest.password) {
            throw NotMatchPassword("비밀번호가 일치하지 않습니다.", OmgException.NOT_MATCH_PASSWORD)
        }
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