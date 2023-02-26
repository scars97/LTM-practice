package list.playlisttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import list.playlisttest.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long>{

}
