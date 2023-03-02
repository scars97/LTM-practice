package list.playlisttest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import list.playlisttest.domain.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Long>{

	@Query(value = "select m.user_id, pl.pl_title"
			+ " from play_list pl"
			+ " join member m"
			+ " where m.user_id = %:user% or pl.pl_title = %:title%" , nativeQuery =true)
	public List<PlayList> findPlList(@Param(value = "user") String userId, @Param(value = "title") String plTitle);
}
