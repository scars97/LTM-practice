package list.playlisttest.controller;

import list.playlisttest.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayListForm {

	private String title;
	
	private String discription;
	
	private Member member;
}
