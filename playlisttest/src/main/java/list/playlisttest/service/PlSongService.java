package list.playlisttest.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.domain.Member;
import list.playlisttest.domain.PlSong;
import list.playlisttest.domain.PlayList;
import list.playlisttest.domain.Song;
import list.playlisttest.repository.MemberRepository;
import list.playlisttest.repository.PlSongRepository;
import list.playlisttest.repository.PlayListRepository;
import list.playlisttest.repository.SongRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlSongService {

	private final PlayListRepository playListRepository;
	private final SongRepository songRepository; 
	private final PlSongRepository plSongRepository;
	
	@Transactional
	public Long plSong(Long plId,Long songId) {
		//엔티티 조회
		Song song = songRepository.findById(songId)
				.orElseThrow(EntityNotFoundException::new);
		PlayList playList = playListRepository.findById(plId)
				.orElseThrow(EntityNotFoundException::new);

		//플레이리스트를 연결해주는 노래 저장 공간 생성
		PlSong plSong = PlSong.createPlSong(playList,song);
		
		//저장
		plSongRepository.save(plSong);
		
		return plSong.getId();
		
	}
}
