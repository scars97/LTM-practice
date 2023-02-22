package list.playlisttest.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.domain.PlayList;
import list.playlisttest.repository.PlayListRepository;
import list.playlisttest.service.PlayListService;

@SpringBootTest
@Transactional
@Rollback(false)
public class PlayListServiceTest {

	@Autowired
	PlayListRepository playListRepository;
	
	@Autowired
	PlayListService playListService;
	
	@Test
	public void 리스트_만들기() {
		//given
		PlayList playList = new PlayList();
		playList.setTitle("퇴근");
		playList.setDiscription("퇴근할때 듣는 노래");
		
		//when
		PlayList saveLists = playListService.saveList(playList);
		
		//then
		assertEquals(playList.getId(), saveLists.getId());
	}
}
