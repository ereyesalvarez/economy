package com.ereyesalvarez.application.infrastructure.security

import com.ereyesalvarez.application.infrastructure.mongo.UserEntity
import com.ereyesalvarez.application.infrastructure.config.JWTProperties
import io.quarkus.security.UnauthorizedException
import io.smallrye.jwt.build.Jwt
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.jwt.Claims
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.enterprise.context.RequestScoped

data class LoginDto(val username: String, val password: String)

data class BearerOutDto(val token: String)


@RequestScoped
class SecurityService(
    private val encoder: PBKDF2Encoder,
    private val jwtProperties: JWTProperties,
    @ConfigProperty(name = "mp.jwt.verify.issuer") private val issuer: String
) {
    fun validateUserLogin(username: String, password: String): String {
        val user = UserEntity.findByUsername(username) ?: throw UnauthorizedException("Invalid login")
        if (user.password.equals(encoder.encode(password))) {
            val upn = user.username ?: throw UnauthorizedException("Invalid login")
            val roles = user.roles ?: throw UnauthorizedException("Invalid login")
            return generateToken(upn, roles, jwtProperties.duration())
        }
        throw UnauthorizedException("Invalid login")
    }


    private fun generateToken(upn: String, roles: MutableSet<String>, duration: Long): String {
        val now = System.currentTimeMillis() / 1000
        return Jwt.issuer(issuer)
            .upn(upn)
            .groups(roles)
            .claim(Claims.iat, now)
            .claim(Claims.exp, now + duration)
            .claim(Claims.email, upn)
            .sign()
    }
}


@RequestScoped
class PBKDF2Encoder(
    private val jwtProperties: JWTProperties,
) {
    /**
     * More info (https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html)
     * @param cs password
     * @return encoded password
     */
    fun encode(cs: CharSequence): String? {
        return try {
            val result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                .generateSecret(
                    PBEKeySpec(
                        cs.toString().toCharArray(),
                        jwtProperties.password().secret().toByteArray(),
                        jwtProperties.password().iteration(),
                        jwtProperties.password().keyLength()
                    )
                )
                .encoded
            Base64.getEncoder().encodeToString(result)
        } catch (ex: NoSuchAlgorithmException) {
            throw RuntimeException(ex)
        } catch (ex: InvalidKeySpecException) {
            throw RuntimeException(ex)
        }
    }
}