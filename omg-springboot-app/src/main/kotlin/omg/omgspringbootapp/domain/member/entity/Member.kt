package omg.omgspringbootapp.domain.member.entity

import omg.omgspringbootapp.global.entity.BaseTimeEntity
import org.hibernate.annotations.GenericGenerator
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID? = null,

    var name: String,

    var password: String,

    val email: String,

    var refreshToken: String? = null
): BaseTimeEntity() {
    fun updateRefreshToken(refreshToken: String) {
        this.refreshToken = refreshToken
    }
}