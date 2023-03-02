package list.playlisttest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import list.playlisttest.domain.Member;
import list.playlisttest.domain.PlSong;
import list.playlisttest.domain.PlayList;
import list.playlisttest.repository.PlSongRepository;
import list.playlisttest.service.MemberService;
import list.playlisttest.service.PlSongService;
import list.playlisttest.service.PlayListService;
import list.playlisttest.service.SongService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PlayListController {

	private final PlayListService playListService;
	private final PlSongService plSongService;
	private final MemberService memberService;
	private final PlSongRepository plSongRepository;
	//private final SongService songService;
	
	//플레이리스트 만들기
	@GetMapping("/playlist/new")
	public String createForm(Model model) {
		//로그인된 회원 정보 가져오기.
		//@RequestParam("memberId") Long memberId
		//Member member = memberService.findOne(memberId);
		//model.addAttribute("member",member);
		
		model.addAttribute("playListForm", new PlayListForm());
		return "createPlForm";
	}
	
	@PostMapping("playlist/new")
	public String create(@Valid PlayListForm form, BindingResult result) {
		
		if(result.hasErrors()) {//에러가 있으면 다시 회원가입폼으로 이동
			return "createPlForm";
		}
		
		//Dto 생성 필요
		PlayList playList = new PlayList();
		playList.setTitle(form.getTitle());
		playList.setDiscription(form.getDiscription());
		playList.setMember(form.getMember());
			
		playListService.savePl(playList);
		return "redirect:/";
		
	}
	
	//전체 플레이리스트 조회
	@GetMapping("/playlist/list")
	public String showAll(Model model) {
		
		List<Member> members = memberService.findMembers();
		List<PlayList> lists = playListService.findPl();
		
		model.addAttribute("lists",lists);
		model.addAttribute("members",members);
		
		return "AllPlayList";
	}
	
	//플레이리스트 담긴 노래 조회
	@GetMapping("/playlist/{id}/song")
	public String plSongForm(@PathVariable("id") Long plId, Model model) {
		PlayList playList = playListService.findOne(plId);
		List<PlSong> songs = plSongService.findPlSongs(plId);
		
		model.addAttribute("playlist",playList);
		model.addAttribute("songs",songs);
		return "PlayListSongs";
	}
	
	//플레이리스트 노래 데이터 삭제
	@PostMapping("/playlist/{plId}/song")
	public String removeSong(@PathVariable("plId") Long plId,@RequestParam("plSongId") Long plSongId) {
		PlSong plSong = plSongService.findOne(plSongId);
		plSongRepository.delete(plSong);
		return "redirect:/playlist/{plId}/song";
	}
}
