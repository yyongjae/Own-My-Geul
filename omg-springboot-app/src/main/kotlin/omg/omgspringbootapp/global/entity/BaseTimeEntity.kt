package omg.omgspringbootapp.global.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class) // JPA Entity에서 이벤트가 발생할 때마다 특정 로직을 실행
class BaseTimeEntity {
    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP", updatable = false)
    private val createdAt: Timestamp? = null

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP")
    private val updatedAt: Timestamp? = null
}