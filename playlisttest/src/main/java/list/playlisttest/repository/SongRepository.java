package list.playlisttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import list.playlisttest.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long>{

	Song findByid(Long id);
	Song findBysongTitle(String songTitle);
	Song findBysinger(String singer);
	Song findBythumbnail(String thumbnail);
}
