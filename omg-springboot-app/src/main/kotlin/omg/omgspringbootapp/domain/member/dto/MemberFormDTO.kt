package omg.omgspringbootapp.domain.member.dto

import omg.omgspringbootapp.domain.member.entity.Member

data class MemberFormDTO(
    val email: String,
    val password: String,
    val name: String
) {
    fun toEntity(): Member {
        return Member(email, password, name)
    }
}