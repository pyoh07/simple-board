package com.pyoh07.simpleboard.controller;

import com.pyoh07.simpleboard.domain.Board;
import com.pyoh07.simpleboard.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 목록
    @GetMapping
    public String list(Model model) {
        model.addAttribute("boards", boardService.getAllBoards());
        return "board-list";
    }

    // 상세
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));
        return "board-detail";
    }

    // 등록 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("board", new Board());
        return "board-form";
    }

    // 등록 처리
    @PostMapping
    public String create(@ModelAttribute Board board) {
        boardService.createBoard(board);
        return "redirect:/boards";
    }

    // 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));
        return "board-form";
    }

    // 수정 처리
    @PostMapping("/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Board board) {
        board.setId(id);
        boardService.updateBoard(board);
        return "redirect:/boards";
    }

    // 삭제
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }
}