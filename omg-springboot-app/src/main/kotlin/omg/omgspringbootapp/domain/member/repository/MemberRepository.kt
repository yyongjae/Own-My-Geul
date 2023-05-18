package omg.omgspringbootapp.domain.member.repository

import omg.omgspringbootapp.domain.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface MemberRepository: JpaRepository<Member, UUID> {
    fun findByEmail(email: String): Optional<Member>
}