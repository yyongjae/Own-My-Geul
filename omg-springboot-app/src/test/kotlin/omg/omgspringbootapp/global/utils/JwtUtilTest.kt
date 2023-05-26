package omg.omgspringbootapp.global.utils

import omg.omgspringbootapp.global.utils.jwt.JwtUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestPropertySource(properties = ["AI_SERVER_URL=http://localhost:8000", "JWT_SECRET=thisissecretkeythisissecretkeythisissecretkeythisissecretkeythisissecretkey"])
internal class JwtUtilTest @Autowired constructor(
    private val jwtUtil: JwtUtil
) {
    @Test
    @DisplayName("토큰에서 회원 id 가져오는 테스트")
    fun getUserIdFromToken() {
        /** given **/
        val memberId = UUID.randomUUID()
        val accessToken = jwtUtil.generateAccessToken(memberId)

        /** when **/
        val memberIdFromToken = jwtUtil.getUserIdFromToken(accessToken)

        /** then **/
        assertThat(memberIdFromToken)
            .isEqualTo(memberId)
    }

    @Test
    @DisplayName("유효한 토큰의 유효성 검증 테스트")
    fun validateToken() {
        /** given **/
        val memberId = UUID.randomUUID()
        val refreshToken = jwtUtil.generateRefreshToken(memberId)

        /** when **/
        val isValid = jwtUtil.validateToken(refreshToken)

        /** then **/
        assertTrue(isValid)
    }

    @Test
    @DisplayName("유효하지 않은 토큰의 유효성 검증 테스트")
    fun validateTokenFail() {
        /** given **/
        val refreshToken = "itisnotavalidtoken"

        /** when **/
        val isValid = jwtUtil.validateToken(refreshToken)

        /** then **/
        assertFalse(isValid)
    }
}