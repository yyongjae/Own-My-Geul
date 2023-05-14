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
    email: String,
    password: String,
    name: String
): BaseTimeEntity() {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID? = null

    @Column(name = "name")
    var name: String = name
        protected set

    @Column(name = "password")
    var password: String = password
        protected set

    @Column(updatable = false)
    val email: String = email
}