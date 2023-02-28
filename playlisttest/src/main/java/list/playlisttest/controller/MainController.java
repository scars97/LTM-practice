package list.playlisttest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	
	@RequestMapping("/")
	public String main() {
		log.info("main controller");
		return "main";
	}
}
