package list.playlisttest.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import list.playlisttest.domain.WishList;
import list.playlisttest.repository.WishListRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WishListService {

	private final WishListRepository wishListRepository;
	
	public WishList saveWishList(WishList wishList) {
		return wishListRepository.save(wishList);
	}
}
