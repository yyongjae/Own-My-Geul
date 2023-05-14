package omg.omgspringbootapp.member

import omg.omgspringbootapp.domain.member.entity.Member
import omg.omgspringbootapp.domain.member.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*

@DataJpaTest // 스프링 bean을 로드하지 않음
class MemberRepositoryTest(
    @Autowired private val memberRepository: MemberRepository
){
    @Test
    @DisplayName("회원 저장")
    fun saveMember() {
        /** given **/
        val member = Member(
            "symaeng98@naver.com",
            "1234",
            "맹순영"
        )

        /** when **/
        val savedMember = memberRepository.save(member)

        /** then **/
        assertThat(savedMember.id)
            .isExactlyInstanceOf(UUID::class.java)
    }

    @Test
    @DisplayName("회원 조회")
    fun getMember() {
        /** given **/
        val member = Member(
            "symaeng98@naver.com",
            "1234",
            "맹순영"
        )
        memberRepository.save(member)

        /** when **/
        val foundMember = memberRepository.findByEmail("symaeng98@naver.com")

        /** then **/
        assertThat(foundMember.get().name)
            .isEqualTo("맹순영")
    }

    @Test
    @DisplayName("없는 회원 조회")
    fun getMemberFail() {
        /** given **/
        val member = Member(
            "symaeng98@naver.com",
            "1234",
            "맹순영"
        )
        memberRepository.save(member)

        /** when **/
        val findMember = memberRepository.findByEmail("aodtns@naver.com")

        /** then **/
        assert(findMember.isEmpty)
    }
}