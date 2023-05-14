package omg.omgspringbootapp.member

import omg.omgspringbootapp.domain.member.dto.MemberFormDTO
import omg.omgspringbootapp.domain.member.entity.Member
import omg.omgspringbootapp.domain.member.repository.MemberRepository
import omg.omgspringbootapp.domain.member.service.MemberService
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestPropertySource(properties = ["AI_SERVER_URL=http://localhost:8000"])
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
){
    @BeforeEach
    fun setUp(){
        val member = Member(
            "symaeng98@naver.com",
            "1234",
            "맹순영"
        )
        memberRepository.save(member)
    }

    @Test
    @DisplayName("회원 가입")
    fun createMember(){
        /** given **/
        val memberFormDTO = MemberFormDTO(
            "symaeng98@naver.com",
            "1234",
            "맹순영"
        )

        /** when **/
        val id = memberService.join(memberFormDTO)

        /** then **/
        assertThat(id)
            .isInstanceOf(UUID::class.java)
    }

    @Test
    @DisplayName("아이디로 회원 조회")
    fun getMemberById(){
        /** given **/
        val member = Member(
            "qwer55252@naver.com",
            "1234",
            "정상헌"
        )
        val savedMember = memberRepository.save(member)
        val id = savedMember.id

        /** when **/
        val foundMemberById = id?.let { memberService.findById(it) }

        /** then **/
        if (foundMemberById != null) {
            assertThat(foundMemberById.name)
                .isEqualTo("정상헌")
        }
    }

    @Test
    @DisplayName("이메일로 회원 조회")
    fun getMemberByEmail(){
        /** given **/
        val email = "symaeng98@naver.com"

        /** when **/
        val foundMemberByEmail = memberService.findByEmail(email)

        /** then **/
        assertThat(foundMemberByEmail.name)
            .isEqualTo("맹순영")
    }
}