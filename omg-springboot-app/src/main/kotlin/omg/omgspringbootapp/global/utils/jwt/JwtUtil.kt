package omg.omgspringbootapp.global.utils.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import omg.omgspringbootapp.global.exception.OmgException
import omg.omgspringbootapp.global.exception.jwt.InvalidTokenException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*


@Component
class JwtUtil(
    @Value("\${jwt.secret}")
    private val jwtSecret: String
) {
    companion object {
        private const val ACCESS_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 2
        private const val REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 24 * 2
    }

    private val secretKey : Key = Keys.hmacShaKeyFor(jwtSecret.toByteArray(StandardCharsets.UTF_8))
    private val jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build()

    fun generateAccessToken(userId: UUID): String {
        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDATION_SECOND))
            .signWith(secretKey)
            .compact()
    }

    fun generateRefreshToken(userId: UUID): String {
        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDATION_SECOND))
            .signWith(secretKey)
            .compact()
    }

    fun getMemberIdFromToken(token: String): UUID? {
        return try {
            val claims = jwtParser
                .parseClaimsJws(token) // 서명 있는 경우엔 Jws 사용
                .body
            UUID.fromString(claims.subject.toString())
        } catch (ex: ExpiredJwtException) {
            ex.printStackTrace() // 보안 상 유효하지 않은 토큰 정도로 처토리
            throw InvalidTokenException("Access Token이 만료되었습니다.", OmgException.EXPIRED_ACCESS_TOKEN)
        } catch (ex: Exception) {
            ex.printStackTrace() // 보안 상 유효하지 않은 토큰 정도로 처토리
            throw InvalidTokenException("유효하지 않은 토큰입니다.", OmgException.INVALID_TOKEN)
        }
    }

    fun validateToken(token: String): Boolean {
        return try {
            jwtParser.parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun resolveToken(bearerToken: String): String {
        val token = bearerToken.substringAfter("Bearer ", "")

        require(token.isNotEmpty()) {
            throw InvalidTokenException("토큰의 헤더가 적절하지 않습니다.", OmgException.INVALID_TOKEN)
        }

        return token
    }
}