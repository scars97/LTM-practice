package list.playlisttest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.domain.PlSong;
import list.playlisttest.domain.PlayList;



public interface PlSongRepository extends JpaRepository<PlSong,Long>{

	@Transactional
	@Modifying
	@Query("select pls.song_id, s.song_title, s.singer"
			+ " from PlSong pls"
			+ " join pls.PlayList pl join Song s"
			+ " on pls.pl_id=pl.pl_id")
	public List<PlSong> findSongList(@Param("plId") Long plId);
}