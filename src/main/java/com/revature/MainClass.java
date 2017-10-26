import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.domain.BoardUser;
import com.revature.service.BoardService;

@Controller
public class MainClass {
	
	@RequestMapping(value="/main")
	public void main(BoardService boardService) {
		
		BoardUser bu = new BoardUser();
		bu.setId(1);
		System.out.println(boardService);
		System.out.println(bu);
		bu = boardService.getBoardUserById(bu);
		
		System.out.println(bu);
	}	
}
