package list.playlisttest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PlSong {

	@Id @GeneratedValue
	@Column(name = "pl_song_id")
	public Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pl_id")
	private PlayList playList;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_id")
	private Song song;
	
	
	//==생성 메서드==//
	//플레이리스트,노래 데이터 삽입
	public static PlSong createPlSong(PlayList playList, Song song) {
		PlSong plSong = new PlSong();
		plSong.setPlayList(playList);
		plSong.setSong(song);
		
		return plSong;
	}
}
