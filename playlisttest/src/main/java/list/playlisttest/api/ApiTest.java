package list.playlisttest.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiTest {

	public static void main(String[] args) throws IOException{
		
			String testUrl = "https://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=e63d38bc9de5863a4bbdfa74a087ea38&format=json";
			
			URL url = new URL(testUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
			int resCode = conn.getResponseCode();
			
			System.out.println(resCode);
			System.out.println(conn.getResponseMessage());
			System.out.println(conn.getContent().toString());
			
			Object result = conn.getContent();
			int ch;
			while((ch=((InputStream)result).read()) != -1) {
				System.out.println((char)ch);
			}
		}

	}
