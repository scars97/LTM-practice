package list.playlisttest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.domain.Member;
import list.playlisttest.domain.PlSong;
import list.playlisttest.domain.PlayList;
import list.playlisttest.domain.Song;
import list.playlisttest.repository.MemberRepository;
import list.playlisttest.repository.PlSongRepository;
import list.playlisttest.repository.PlayListRepository;
import list.playlisttest.repository.SongRepository;
import list.playlisttest.service.PlSongService;

@SpringBootTest
@Transactional
//@Rollback(false)
public class ListInputTest {// 회원,플레이리스트,노래 데이터 종합 테스트

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	SongRepository songRepository;
	@Autowired
	PlayListRepository playListRepository;
	@Autowired
	PlSongRepository plSongRepository;
	@Autowired
	PlSongService plSongService;

	// @Autowired EntityManager em;

	// @Test
	public void 플레이리스트_생성() {
		// given
		Member member = createMember();
		PlayList playList = createPl(member);
		Song song = createSong();

		// when
		//Long plSongId = plSongService.plSong(playList.getId(), song.getId());

		// then
		//PlSong getPlSong = plSongRepository.findById(plSongId).orElseThrow(EntityNotFoundException::new);

		// 결국엔 playList의 pl_id와 Song의 song_id가 pl_song과 매치되어야함.

		// song_id가 같은지
		//assertEquals(song.getId(), getPlSong.getSong().getId());
		// pl_id가 같은지.
		//assertEquals(playList.getId(), getPlSong.getPlayList().getId());

	}

	//@Test
	public void 플레이리스트_삭제() {
		// given
		Member member = createMember();
		PlayList playList1 = createPl(member);
		PlayList playList2 = createPl(member);
		Song song = createSong();

		// when
//		plSongService.plSong(playList1.getId(), song.getSongTitle(),song.getSinger());
//		plSongService.plSong(playList1.getId(), song.getSongTitle(),song.getSinger());
//		plSongService.plSong(playList2.getId(), song.getSongTitle(),song.getSinger());

		// then
//		PlSong getPlSong = plSongRepository.findById(plSongId)
//				.orElseThrow(EntityNotFoundException::new);
//		PlSong getPlSong2 = plSongRepository.findById(plSongId2)
//				.orElseThrow(EntityNotFoundException::new);

		playListRepository.delete(playList1);
//		em.remove(playList1);
//		
//		em.flush();
//		em.clear();

	}

	@Test
	public void 연결된_노래아이디_찾기() {
		// given
		Member member = createMember();
		PlayList playList = createPl(member);
		PlayList playList2 = createPl(member);
		Song song = createSong();

		// when
//		plSongService.plSong(playList.getId(), song.getSongTitle(),song.getSinger());
//		plSongService.plSong(playList.getId(), song.getSongTitle(),song.getSinger());
//		plSongService.plSong(playList2.getId(), song.getSongTitle(),song.getSinger());

		// then
		List<PlSong> plSongs = plSongRepository.findSongList(playList.getId());
		
		if(plSongs != null) {
			System.out.println("몇개: " + plSongs.size());
		}
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

	private Song createSong() {
		Song song = new Song();
		song.setSongTitle("노래제목");
		song.setSinger("가수");
		songRepository.save(song);
		return song;
	}

}
