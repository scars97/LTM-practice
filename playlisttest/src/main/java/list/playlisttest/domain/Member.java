package list.playlisttest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_id")
	private Long id;
	
	@Column(name = "user_id")
	private String userId;
	
	private String nickname;
	
	@OneToMany(mappedBy = "member")
	private List<WishList> wishList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<PlayList> playList = new ArrayList<>();
}
