package com.nh.controller;

import com.nh.dto.BoardDto;
import com.nh.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/new") //조회
    public String getBoardForm() {
        return "boardForm";
    }

    @PostMapping("/board/new") //등록 , 수정 ,삭제 등 할때 쓰임
    public String postBoard(BoardDto boardDto) {
        System.out.println(boardDto.getTitle());
        System.out.println(boardDto.getContent());
        boardService.insertBoard(boardDto);
        return "redirect:/";
    }

    @GetMapping("/board/list")
    public String getBoardList(Model model , @RequestParam(value = "currentPage" , defaultValue = "0") int currentPage) {
        int pageData = 5;//한 페이지당 보여질 데이터 개수 (이건 내가 그냥 막 정하면됩니당)
        int offset = currentPage*pageData; //전체 데이터(totalCount)에서 몇번째부터 시작점으로 잡을지 정할때 필요(예를들어 1번 페이지는 0번데이터부터  , 2번페이지는 5번데이터부터)_
        int totalCount = boardService.totalCount();
        model.addAttribute("currentPage",currentPage); //현재 페이지(화면에서 페이지버튼 눌린표시랑 안눌린표시 , 이전버튼 , 다음버튼 활성화 비활성화 할때 필요)
        model.addAttribute("pageCount",(totalCount/pageData) + ((totalCount%pageData) >0? 1:0)); //총 페이지수(화면에서 페이지 버튼 개수 만들때 필요 , 이전버튼 , 다음버튼 활성화 비활성화 할때 필요)
        model.addAttribute("boardList",boardService.boardList(offset , pageData)); //게시판 리스트(몇번째 글부터 조회할지 offset을 , 한페이지에 몇개씩 조회할지 pageData를 파라미터로 넘겨야함)
        return "boardList";
    }
}
