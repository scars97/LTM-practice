package list.playlisttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import list.playlisttest.domain.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long>{

}
