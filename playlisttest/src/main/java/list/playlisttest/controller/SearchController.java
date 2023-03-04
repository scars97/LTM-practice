package list.playlisttest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SearchController {

	@GetMapping("/search")
	public String searchSong() {
		return "Search";
	}
	
	@PostMapping("/search")
	public String searchResults(@RequestParam("songinfo") String songInfo) {
		
		//입력된 검색어에 대한 api 데이터를 받아서 형식에 맞게 출력해줘야 함.
		//리포지토리나 서비스 클래스에서 api 호출 로직을 짜고
		//결과물만 controller에 가져올 수 있게 하기.
		//api데이터 가져오는 거 공부....
		return "SongResults";
	}
	
}
