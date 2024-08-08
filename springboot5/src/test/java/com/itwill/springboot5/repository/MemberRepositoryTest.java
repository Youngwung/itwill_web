package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.itwill.springboot5.domain.Member;
import com.itwill.springboot5.domain.MemberRole;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	// 비밀번호 암호화 처리를 위한 의존성 주입
	private PasswordEncoder passwordEncoder;
	
	// @Test //!성공
	public void DependencyInjection() {
		assertThat(memberRepo).isNotNull();
		log.info(memberRepo.toString());

		assertThat(passwordEncoder).isNotNull();
		log.info(passwordEncoder.toString());
	}

		// @Test //! 성공
		public void testSave() {
			// 엔터티 객체를 DB members 테이블에 저장

			Member m = Member.builder()
				.id(10L)
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				.email("admin@itwill.com")
				.build();

			m.addRole(MemberRole.ADMIN); 
			// Enum 클래스에서 정의한 상수와 메서드로 권한을 설정

			log.info("save 호출 전 = {}, {}", m, m.getRoles());
			m = memberRepo.save(m);
			log.info("save 호출 후 = {}, {}", m, m.getRoles());
		}

		// @Test @Transactional //! 성공
		public void testFindAll() {
			List<Member> list = memberRepo.findAll();
			//-> members 테이블과 member_roles 테이블에서 정보를 취합.
			list.forEach((member) -> log.info("{}, {}", member, member.getRoles()));
		}
	
		// @Test
		public void testFindByUsername() {
			Member test1 = memberRepo.findByUsername("test1").orElseThrow();
			log.info("{}, {}", test1, test1.getRoles());

			Member test2 = memberRepo.findByUsername("test2").orElseThrow();
			log.info("{}, {}", test2, test2.getRoles());
		}
}
