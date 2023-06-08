package omg.omgspringbootapp.domain.member.service

import omg.omgspringbootapp.domain.member.dto.request.LoginRequest
import omg.omgspringbootapp.domain.member.dto.request.JoinRequest
import omg.omgspringbootapp.domain.member.dto.response.LoginResponse
import omg.omgspringbootapp.domain.member.dto.response.MemberInfo
import omg.omgspringbootapp.domain.member.entity.Member
import omg.omgspringbootapp.domain.member.exception.MemberAlreadyExistException
import omg.omgspringbootapp.domain.member.exception.NoSuchMemberException
import omg.omgspringbootapp.domain.member.exception.NotMatchPassword
import omg.omgspringbootapp.domain.member.repository.MemberRepository
import omg.omgspringbootapp.global.dto.TokenInfo
import omg.omgspringbootapp.global.exception.OmgException
import omg.omgspringbootapp.global.exception.jwt.InvalidTokenException
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
        if (checkEmail(joinRequest.email)) {
            val member = memberRepository.save(joinRequest.toEntity())
            return member.id
        }
        throw MemberAlreadyExistException("이미 가입된 이메일입니다.", OmgException.MEMBER_ALREADY_EXIST)
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
            member.name,
            memberId.toString()
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

    fun reissueToken(id: String): TokenInfo{
        val memberUUID = UUID.fromString(id)

        val member = findById(memberUUID)
        val refreshToken = member.refreshToken ?: throw InvalidTokenException("토큰이 존재하지 않습니다.", OmgException.INVALID_TOKEN)

        val isValid = jwtUtil.validateToken(refreshToken)

        if (isValid) {
            val accessToken = jwtUtil.generateAccessToken(memberUUID)
            return TokenInfo(
                "Bearer",
                accessToken,
                refreshToken
            )
        }

        throw InvalidTokenException("유효하지 않은 토큰입니다.", OmgException.INVALID_TOKEN)
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

    fun checkEmail(email: String): Boolean {
        return memberRepository.findByEmail(email)
            .isEmpty
    }
}