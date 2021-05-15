package ex1;

import java.sql.SQLException;

import com.newworld.app.ui.NoticeUI;

public class Program5 {
 
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// NoticeUI 관리하는 UI
		NoticeUI ui = new NoticeUI();
		//int page;
		
		EXIT:
		while(true) {
			ui.printNoticeList();
			int menu = ui.inputNoticeMenu();
			
			switch(menu) {
			case 1: // 상세조회 
				break;
			case 2: // 이전 
				//page--;
				ui.movePrevList();
				break;
			case 3: // 다음 
				//page++;
				ui.moveNextList();
				break;
			case 4: // 글쓰기 
				break;
			case 5: // 검색 
				ui.inputSearchWord();
				break;
			case 6: // 종료 
				System.out.println("Bye~");
				break EXIT;
			default:
				System.out.println("<<메뉴는 1~4까지만 입력할 수 있습니다.");
				break;
			}
		}
	}

}
