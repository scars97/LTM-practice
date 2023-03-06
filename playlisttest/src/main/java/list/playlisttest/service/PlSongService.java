package list.playlisttest.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.DataNotFoundException;
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
	public PlSong plSong(Long plId,String songTitle,String singer) {
		//엔티티 조회
		PlayList playList = playListRepository.findById(plId)
				.orElseThrow(EntityNotFoundException::new);

		//플레이리스트를 연결해주는 노래 저장 공간 생성
		PlSong plSong = PlSong.createPlSong(playList,songTitle,singer);
		
		//저장
		playList.addPlSongs(plSong);
		
		return plSongRepository.save(plSong);
	}
	
	public List<PlSong> findPlSongs(Long plId){
		return plSongRepository.findSongList(plId);
	}
	
	public PlSong findOne(Long plSongId) {
		return plSongRepository.findById(plSongId)
				.orElseThrow(EntityNotFoundException::new);
	}
	
//	public Song getSong(Long id) {
//		Optional<Song> song = this.songRepository.findById(id);
//		if(song.isPresent()) {
//			return song.get();
//		}else {
//			throw new DataNotFoundException("song not found");
//		}
//	}
}
