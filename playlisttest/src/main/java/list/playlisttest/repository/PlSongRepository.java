package list.playlisttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import list.playlisttest.domain.PlSong;

public interface PlSongRepository extends JpaRepository<PlSong, Long>{

}
