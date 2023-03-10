package list.playlisttest.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import list.playlisttest.api.SongImageApi;
import list.playlisttest.domain.Member;
import list.playlisttest.domain.PlSong;
import list.playlisttest.domain.PlayList;
import list.playlisttest.domain.Song;
import list.playlisttest.dto.PlayListForm;
import list.playlisttest.repository.InputSong;
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
	private final SongImageApi songImageApi;
	
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
	
	//업데이트할 리스트 목록
	@GetMapping("/playlist/mylist")
	public String showAll(Model model) {
		
		List<Member> members = memberService.findMembers();
		List<PlayList> lists = playListService.findPl();
		
		model.addAttribute("lists",lists);
		model.addAttribute("members",members);
		
		return "MyPlayList";
	}
	//플레이리스트 업데이트
	@GetMapping("/playlist/{id}/update")
	public String updatePl(@PathVariable("id") Long plId, Model model) {
		PlayList selectOne = playListService.findOne(plId);

		model.addAttribute("listInfo",selectOne);
		return "UpdatePlayList";
	}
		
	@PostMapping("/playlist/update")
	public String updateInfo(@RequestParam("id") Long plId, 
							 @RequestParam("title") String title, 
							 @RequestParam("discription") String discription) throws Exception{
		playListService.updatePl(plId, title, discription);
	
		return "redirect:/";
	}
	
	//플레이리스트 삭제
	@PostMapping("/playlist/delete")
	public String deletePl(@RequestParam("plId") Long plId) throws Exception{
		playListService.deletePl(plId);
		return "redirect:/";
	}
	
	
	//페이징 처리된 전체 플레이리스트 소현님.ver
	@GetMapping("/playlist/list")
    public String list(Model model, @RequestParam(value="page" , defaultValue="0") int page,
    		 @RequestParam(value = "kw", defaultValue = "") String kw) {
        //List<PlayList> playList = this.playlistService.getlist(); //레퍼지토리를 바로 불러와서 쓰지않고 서비스를 통해서 사용하도록 작성
        //model.addAttribute("playList2", playList);
    	
    	System.out.println("***************************");
    	System.out.println(page);
    	System.out.println(kw);
    	System.out.println("***************************");
    	
    	if("".compareTo(kw) == 0) {
    		Page<PlayList> paging = this.playListService.getlist(page);
    		
    		model.addAttribute("paging",paging);
        	model.addAttribute("kw", kw);
            return "Pl_main";
    	}else {
    		Page<PlayList> paging = this.playListService.getlistkeyword(page,kw);
    		model.addAttribute("paging",paging);
        	model.addAttribute("kw", kw);
            return "Pl_main";
    	}
    
    }
	
	//담긴 노래 소현님.ver
	@GetMapping("/playlist/{id}/song")
    public String plDetail(Model model, @PathVariable("id") Long plId) {
    	PlayList playlist = this.playListService.findOne(plId);
    	model.addAttribute("playList22",playlist);
    	
    	List<PlSong> songs = plSongService.findPlSongs(plId);//리스트로 담는 것도 생각해보기, plsong 연결필요
    	model.addAttribute("song22",songs);
    	return "Pl_detail";
    }
	
	//플레이리스트 노래 삭제
	@PostMapping("/playlist/{plId}/song")
	public String removeSong(@RequestParam("plSongId") Long plSongId) {
		PlSong plSong = plSongService.findOne(plSongId);
		plSongRepository.delete(plSong);
		return "redirect:/playlist/{plId}/song";
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	
//	@GetMapping("/song") //API관련 서비스 로직 구현 필요
//	public String songList(Model model) {
//		
//		List<Song> songList = songService.findSongs();
//		
//		model.addAttribute("songList",songList);
//		return "SongList";
//	}
		
	//내 플레이리스트에 노래 넣기
	@GetMapping("/inputsong")
	public String inputSongDetail(@RequestParam("songTitle") String songTitle,
			                      @RequestParam("singer") String singer,
			                      Model model) {
		
		List<PlayList> PlayList = playListService.findPl();
		
		model.addAttribute("playlist",PlayList);
		model.addAttribute("Title",songTitle);
		model.addAttribute("singer",singer);
		return "InputSong";
	}
	
	@PostMapping("/inputsong")
	public String inputSong(@RequestParam("plId") Long plId,
							@RequestParam("songTitle") String songTitle,
							@RequestParam("singer") String singer) {
			
		//노래제목,가수 파라미터 받아서 그 노래에 대한 이미지 가져오는 코드..
		//plsong에 이미지 컬럼을 넣어야하나..?
		String songImage = songImageApi.getImage(songTitle, singer);
		
		plSongService.plSong(plId, songTitle, singer, songImage);//담은 노래의 id

		return "redirect:/search";
	}
	
}
