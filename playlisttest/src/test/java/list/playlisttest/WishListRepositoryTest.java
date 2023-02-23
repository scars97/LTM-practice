package list.playlisttest;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import list.playlisttest.domain.Member;
import list.playlisttest.domain.PlayList;
import list.playlisttest.domain.WishList;
import list.playlisttest.repository.MemberRepository;
import list.playlisttest.repository.PlayListRepository;
import list.playlisttest.repository.WishListRepository;

@SpringBootTest
@Transactional
@Rollback(false)
public class WishListRepositoryTest {

	@Autowired MemberRepository memberRepository;
	@Autowired PlayListRepository playListRepository;
	@Autowired WishListRepository wishListRepository;
	
	@Test
	public void 위시리스트_테스트() {
		
		Member member = new Member();//회원 데이터 넣기
		member.setUserId("spring");
		memberRepository.save(member);
		
		PlayList playList = new PlayList();//회원 데이터 넣기
		playList.setTitle("출근");
		playListRepository.save(playList);
		
		WishList wishList = new WishList();//위시리스트 데이터 넣기
		wishList.setMember(member);
		wishList.setPlayList(playList);
		wishListRepository.save(wishList);
		
		
		WishList saveWl = wishListRepository.findById(wishList.getId())
				.orElseThrow(EntityNotFoundException::new);
				//id를 찾지 못하면 예외터지게.
		assertEquals(saveWl.getMember().getId(),member.getId());
		assertEquals(saveWl.getPlayList().getId(),playList.getId());
		
	}
}
