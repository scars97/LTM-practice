package list.playlisttest.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.domain.Member;

import list.playlisttest.service.MemberService;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Test
	public void 회원가입() {
		//given
		Member member = new Member();
		member.setUserId("ezen");
		member.setNickname("김영희");
		
		//when
		Member saveMember = memberService.saveMember(member);
		
		//then
		assertEquals(member.getUserId(), saveMember.getUserId());
	}
	
	@Test
	public void 중복확인테스트() {
		//given
		Member member1 = new Member();
		member1.setUserId("ezen");
		Member member2 = new Member();
		member2.setUserId("ezen");
		
		//when
		memberService.saveMember(member1);
		try {
			memberService.saveMember(member2);
		}catch(IllegalStateException e) {
			e.getMessage();
		}
		
		//then
		fail("예외가 터져야 함");
	}
}
