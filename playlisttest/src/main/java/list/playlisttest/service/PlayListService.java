package list.playlisttest.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.domain.PlSong;
import list.playlisttest.domain.PlayList;
import list.playlisttest.repository.PlayListRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayListService {

	private final PlayListRepository playListRepository;
	
	@Transactional
	public Long savePl(PlayList playList) {
		playListRepository.save(playList);
		return playList.getId();
	}
	
	public List<PlayList> findPl(){
		return playListRepository.findAll();
	}
	
	public PlayList findOne(Long plId) {
		return playListRepository.findById(plId)
				.orElseThrow(EntityNotFoundException::new);
	}
	
	public List<PlayList> findPlList(String user, String plTitle){
		return playListRepository.findPlList(user, plTitle);
	}
}
