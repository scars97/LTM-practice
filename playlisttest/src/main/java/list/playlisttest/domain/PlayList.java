package list.playlisttest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlayList {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pl_id")
	private Long id;
	
	@Column(name = "pl_title")
	private String title;
	
	private String discription;
//	private String image; 
	
	@OneToOne(mappedBy = "playList",fetch = FetchType.LAZY)
	private WishList wishList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "playList", cascade = CascadeType.REMOVE)
	private List<PlSong> plSongs = new ArrayList<>();
	
	
}
