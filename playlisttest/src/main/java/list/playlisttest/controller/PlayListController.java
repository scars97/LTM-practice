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
		
		PlayList playList = new PlayList();
		playList.setTitle(form.getTitle());
		playList.setDiscription(form.getDiscription());
		playList.setMember(form.getMember());
			
		playListService.savePl(playList);
		return "redirect:/";
		
	}
	
	@GetMapping("/playlist/list")
	public String showAll(Model model) {
		
		List<Member> members = memberService.findMembers();
		List<PlayList> lists = playListService.findPl();
		
		model.addAttribute("lists",lists);
		model.addAttribute("members",members);
		
		return "AllPlayList";
	}
	
	@GetMapping("/playlist/{id}/song")
	public String plSongForm(@PathVariable("id") Long plId, Model model) {
		
		List<PlSong> songs = plSongService.findPlSongs(plId);
		
		model.addAttribute("songs",songs);
		return "PlayListSongs";
	}
	
	@PostMapping("/song/{id}/remove")//수정필요
	public String removeSong(@PathVariable("id") Long plSongId) {
		PlSong plSong = plSongService.findOne(plSongId);
		plSongRepository.delete(plSong);
		return "redirect:/PlayListSongs";
	}
}
