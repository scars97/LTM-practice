package list.playlisttest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.domain.PlSong;
import list.playlisttest.domain.PlayList;


public interface PlSongRepository extends JpaRepository<PlSong, PlayList>{

	@Transactional
	@Modifying
	@Query("delete from PlSong ps where ps.playList.id = :fkPlId")
	void deleteAllByPlId(@Param("fkPlId") Long fkPlId);
}
