package list.playlisttest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import list.playlisttest.domain.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {

//	@Query(value = "select m.user_id, pl.pl_title" +
//				   " from play_list pl" + " join member m" + 
//			       " where m.user_id = %:user% or pl.pl_title = %:title%", nativeQuery = true)
//	public List<PlayList> findPlList(@Param(value = "user") String userId, @Param(value = "title") String plTitle);

	// PlayList findByid(String id);
	PlayList findByTitle(String title);

	PlayList findByDiscription(String discription);

	// PlayList findByNickname (String nickname);
	PlayList findByImage(String image);

	Optional<PlayList> findById(Long id);

	Page<PlayList> findAll(Pageable pageable);
	// Pageable 객체를 입력으로 받아 Page<PlayList> 타입 객체를 리턴하는 findAll 매서드 생성

	@Query("select P from PlayList P where P.title like %:kw%")
	Page<PlayList> findByKeyword(@Param("kw") String kw, Pageable pageable);
	
	//노래 넣을때 회원 id를 가진 플레이리스트 목록 조회
	@Query(value = "select p.title from PlayList p where p.memberId = :id",nativeQuery=true)
	List<PlayList> findMemberPlayList(@Param("id") Long memberId);
}
