package list.playlisttest.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import list.playlisttest.domain.Member;


public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByUserId(String userId);
	
}
