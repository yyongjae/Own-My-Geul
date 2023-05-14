package omg.omgspringbootapp

import omg.omgspringbootapp.domain.member.entity.Member
import omg.omgspringbootapp.domain.member.repository.MemberRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class OmgSpringbootAppApplicationTests(
	@Autowired val memberRepository: MemberRepository
) {

	@Test
	fun saveMemberTest() {
		val member = Member(
			"symaeng98@naver.com",
			"1234",
			"맹순영"
		)

		val saveMember = memberRepository.save(member)

	}

}
