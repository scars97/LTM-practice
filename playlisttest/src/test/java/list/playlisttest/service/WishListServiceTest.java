package list.playlisttest.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import list.playlisttest.domain.Member;
import list.playlisttest.domain.PlayList;
import list.playlisttest.domain.WishList;
import list.playlisttest.repository.WishListRepository;

@SpringBootTest
@Transactional
public class WishListServiceTest {

	@Autowired WishListRepository wishListRepository;
	@Autowired WishListService wishListService;
	@Autowired Member member;
	@Autowired PlayList playList;
	
	@Test
	public void 위시리스트_만들기() {
		//given
		WishList wishList = new WishList();
		wishList.setMember(member.getId());
		
		//when
		
		
		//then
	}
}
