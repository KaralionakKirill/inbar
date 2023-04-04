package by.inbar.backend.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.Date

@Service
class JwtService {
    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username) && !isTokenExpired(token)
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(hashMapOf(), userDetails)
    }

    private fun generateToken(claims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts
            .builder()
            .addClaims(claims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun <T> extractClaim(token: String, claimResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimResolver(claims)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token)?.before(Date()) ?: false
    }

    private fun extractExpiration(token: String): Date? {
        return extractClaim(token, Claims::getExpiration)
    }

    fun extractUsername(token: String): String? {
        return extractClaim<String>(token, Claims::getSubject)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    companion object {
        private const val SECRET_KEY = "792F423F4528482B4D6251655468576D5A7134743777397A24432646294A404E"
    }
}
