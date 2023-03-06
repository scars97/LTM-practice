package list.playlisttest.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.DataNotFoundException;
import list.playlisttest.domain.Song;
import list.playlisttest.repository.SongRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SongService {

	private final SongRepository songRepository;
	
	@Transactional
	public Long saveSong(Song song) {
		songRepository.save(song);
		return song.getId();
	}
	
	public List<Song> findSongs(){
		return songRepository.findAll();
	}
	
	public Song findOne(Long songId) {
		return songRepository.findById(songId)
				.orElseThrow(EntityNotFoundException::new);
	}
	
	public Song getSong(Long id) {
		Optional<Song> song = this.songRepository.findById(id);
		if(song.isPresent()) {
			return song.get();
		}else {
			throw new DataNotFoundException("song not found");
		}
	}
}
