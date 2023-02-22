package list.playlisttest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.domain.PlayList;
import list.playlisttest.repository.PlayListRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayListService {

	private final PlayListRepository playListRepository;
	
	public PlayList saveList(PlayList playList) {
		
		return playListRepository.save(playList);
	}
}
