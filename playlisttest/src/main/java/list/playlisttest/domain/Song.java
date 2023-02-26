package list.playlisttest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Song {
	
	@Id @GeneratedValue
	@Column(name = "song_id")
	private Long id;
	
	private String songTitle;
	private String singer;
	private String thumbnail;

}
