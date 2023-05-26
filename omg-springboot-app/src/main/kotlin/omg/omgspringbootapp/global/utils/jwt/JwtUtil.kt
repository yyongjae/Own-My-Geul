package omg.omgspringbootapp.global.utils.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
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
        private const val ACCESS_TOKEN_VALIDATION_SECOND = 1000L * 2
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

    fun getUserIdFromToken(token: String): UUID? {
        return try {
            val claims = jwtParser
                .parseClaimsJws(token) // 서명 있는 경우엔 Jws 사용
                .body
            UUID.fromString(claims.subject.toString())
        } catch (ex: Exception) {
            null
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
}