package com.nh.controller;

import com.nh.aop.LogExecutionTime;
import com.nh.dto.BoardDto;
import com.nh.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    @Autowired /*스프링 실행 시점에 자동으로 객체를 만들어준다*/
    private BoardService boardService;

    @GetMapping("/board/new")
    public String getBoard() {
        return "board/board"; //jsp 파일명
    }


    @GetMapping(value = "/board/list")
    public String boardList(
            BoardDto boardDto,
            Model model
            ,@RequestParam(name = "currentPage" , defaultValue = "0") int currentPage) {

        int pageData = 5;//한 페이지당 보여질 데이터 개수 (이건 내가 그냥 막 정하면됩니당)
        int offset = currentPage*pageData; //전체 데이터(totalCount)에서 몇번째부터 시작점으로 잡을지 정할때 필요(예를들어 1번 페이지는 0번데이터부터  , 2번페이지는 5번데이터부터)_
        int totalBoardCount = boardService.totalBoardCount(boardDto);// 총 게시글 수
        int pageCount = (totalBoardCount/pageData) + ((totalBoardCount%pageData) > 0 ? 1:0); //총 페이지 버튼의 개수

        //------------------------------------------------------2021-10-02 추가내용
        //페이지 번호가 한번에 몇개부터 몇개씩 보일것인지 !
        int pageMaxNum = 5; // 한번에 페이지 버튼이 몇개씩 보일것인가에 대한 값이다.
        int start = Math.max(1 , currentPage/pageMaxNum*pageMaxNum+1);//페이지 번호를 몇번부터 표시할건지
        int end = Math.min(pageCount , start+pageMaxNum-1);//페이지 번호를 몇번까지 표시할건지
        //------------------------------------------------------

        boardDto.setOffset(offset);
        boardDto.setLimit(pageData);
        System.out.println("boardDto = " + boardDto);

        List<BoardDto> boardList = boardService.boardList(boardDto);

        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage",currentPage); //현재 페이지(화면에서 페이지버튼 눌린표시랑 안눌린표시 , 이전버튼 , 다음버튼 활성화 비활성화 할때 필요)
        model.addAttribute("pageCount",pageCount); //총 페이지수(화면에서 페이지 버튼 개수 만들때 필요 , 이전버튼 , 다음버튼 활성화 비활성화 할때 필요)
        model.addAttribute("start", start);//화면에서 페이지 번호 시작점을 나타낼 수 있다.
        model.addAttribute("end", end);//화면에서 페이지 번호 끝점을 나타낼 수 있다.

        return "board/board";
    }



    @GetMapping("/board/post")
    public String getWrite() {
        return "board/board_post"; //jsp 파일명
    }


    @PostMapping("/board/post")
    public String postWrite(BoardDto boardDto) {
        System.out.println("작성자 = " + boardDto.getId());
        System.out.println("제목 = " + boardDto.getTitle());
        System.out.println("내용 = " + boardDto.getContent());
        System.out.println("날짜 = " + boardDto.getBoard_date());
        boardService.insertBoard(boardDto);
        return "redirect:/board/list";
    }

    @LogExecutionTime
    @RequestMapping(value = "/board/{id}")
    public String boardWrite(@PathVariable String id, Model model) {
        BoardDto boardDto = boardService.selectBoard(id);
        System.out.println("작성자 = " + boardDto.getId());
        System.out.println("내용 = " + boardDto.getContent());
        System.out.println("제목 = " + boardDto.getTitle());
        model.addAttribute("board", boardDto);
        return "board/board";
    }

    @RequestMapping(value = "/board/read", method = RequestMethod.GET)
    public String read(@RequestParam("num") int num, Model model) {
        BoardDto boardContents = boardService.getBoardContents(num);
        model.addAttribute("boardContents", boardContents);

        return "board/read";
        /* 이러한 방식도 있지만 restfullAPI에서 자주 씀!!!!! (선택의 차이)
            @RequestMapping(value = "/board/read/{num}", method = RequestMethod.GET)
    public String read(@PathVariable("num") int num, Model model) {
        BoardDto boardContents = boardService.getBoardContents(num);
        model.addAttribute("boardContents", boardContents);

        return "board/read";
    }
        */
    }

    @RequestMapping(value = "/board/update", method = RequestMethod.GET)
    public String update(@RequestParam("num") int num, Model model) {
        BoardDto boardContent_detail = boardService.getBoardContents(num);
        model.addAttribute("boardContents", boardContent_detail);
        return "/board/update";
        /*  boardContents (jsp에서 뽑을 때 쓰는 key)  boardContent_detail(key의 해당하는 값)
        key (boardContents)를 부르면 value(boardContent_detail)가 불러와 진다.

        컨트롤러는 back과 프론트를 연결시켜주는 역할
        model은 jsp로 값을 넘겨줌(boardContents를 jsp에서 boardContent_detail을 불러옴)

        boardContent_detail는 DTO 타입으로써 boardService를 호출 함

        return은 부른곳에 돌려주겠다!
         */
    }

    @RequestMapping(value = "/board/postUpdate", method = RequestMethod.POST)
    public String postUpdate(BoardDto boardDto) {
        boardService.updateBoard(boardDto);
        return "redirect:/board/list";
    }

    @RequestMapping(value = "/board/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("num") int num, Model model) {
        BoardDto boardDelete = boardService.getBoardDelete(num);

        model.addAttribute("boardContents", boardDelete);
        return "redirect:/board/list";
        /* redirect사용시 model은 소멸됨 */
    }

}