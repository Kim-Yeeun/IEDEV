package com.newworld.app.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newworld.app.entity.Notice;
import com.newworld.app.service.NoticeService;

public class NoticeUI {
	
	// 데이터베이스를 위한 것 
	private NoticeService service;
	private int page;
	private String searchWord;
	private String searchField;
	
	public NoticeUI() {
		service = new NoticeService();
		page = 1;
		searchWord = "";
		searchField = "TITLE";
	}

	public void printNoticeList() throws ClassNotFoundException, SQLException {
		List<Notice> list = service.getList(page, searchField, searchWord);
		
		// list를 구할 때마다 값이 달라지는 변수(지역 내에서만 사용하는 변수)
		int total = service.getTotal();
		int last = total / 10;
		last = last % 10 > 0 ? last + 1 : last;
		
		System.out.println("-------------------------------");
		System.out.printf("<공지사항> 총 %d 게시글\n", total);
		System.out.println("-------------------------------");
		
		for(Notice n : list) {
			System.out.printf("%d. %s / %s / %s\n", 
							n.getId(),
							n.getTitle(),
							n.getWriterId(),
							n.getRegDate());
		}
		
		System.out.println("-------------------------------");
		System.out.printf("	%d/%d pages\n", page, last);
	}

	public int inputNoticeMenu() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색/ 6.종료 >");
		String menu_ = scan.nextLine();
		int menu = Integer.parseInt(menu_);
		
		return menu;
	}

	public void movePrevList() {
		if (page == 1) {
			System.out.println("=================");
			System.out.println("[첫번째 페이지입니다.]");
			System.out.println("=================");
			return;
		}
		page--;
	}
	
	public void moveNextList() throws ClassNotFoundException, SQLException {
		int total = service.getTotal();
		int last = total / 10;
		last = last % 10 > 0 ? last + 1 : last;
		
		if (page == last) {
			System.out.println("===================");
			System.out.println("[다음 페이지가 없습니다.]");
			System.out.println("===================");
			return;
		}		
		page++;
		
	}

	public void inputSearchWord() {
		Scanner scan = new Scanner(System.in);
		System.out.println("검색 범주(title/content/writerId) 중에 하나를 입력하세요.");
		System.out.print(">");
		searchField = scan.nextLine();
		// inputSearchWord()에서 입력받고 printNoticeList()에서 사용해야하므로 멤버변수로 선언 
		System.out.print("검색어 > ");
		searchWord = scan.nextLine();
		
	}

}
