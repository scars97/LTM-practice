package list.playlisttest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.domain.Member;
import list.playlisttest.domain.PlayList;
import list.playlisttest.repository.MemberRepository;
import list.playlisttest.repository.PlayListRepository;
import list.playlisttest.service.PlayListService;

@SpringBootTest
@Transactional
public class UpdatePlTest {

	@Autowired MemberRepository memberRepository;
	@Autowired PlayListRepository playListRepository;
	@Autowired PlayListService playListService;
	
	@Test
	public void 업데이트() {
		//given
		Member member = createMember();
		PlayList playList = createPl(member);
		
		//when
		try {
			playListService.updatePl(playList.getId(), "퇴근2", "퇴근노래2");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//then
		assertEquals("퇴근2", playList.getTitle());
		assertEquals("퇴근노래2", playList.getDiscription());
		
	}
	
	private Member createMember() {
		Member member = new Member();
		member.setUserId("spring");
		memberRepository.save(member);
		return member;
	}

	private PlayList createPl(Member member) {
		PlayList playList = new PlayList();
		playList.setTitle("퇴근");
		playList.setDiscription("퇴근 노래");
		playList.setMember(member);
		playListRepository.save(playList);
		return playList;
	}
}
