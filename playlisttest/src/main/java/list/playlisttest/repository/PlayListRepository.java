package list.playlisttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import list.playlisttest.domain.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Long>{

	
}
