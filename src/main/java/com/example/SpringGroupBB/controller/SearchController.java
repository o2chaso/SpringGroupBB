package com.example.SpringGroupBB.controller;

import com.example.SpringGroupBB.dto.BoardDTO;
import com.example.SpringGroupBB.dto.QnADTO;
import com.example.SpringGroupBB.service.BoardService;
import com.example.SpringGroupBB.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {
  private final BoardService boardService;
  private final QnAService qnaService;

  @GetMapping("/search")
  public String searchGet(Model model, Authentication authentication, String searchStr) {
    if(searchStr.replace(" ", "").equals("")) return "/";
    List<BoardDTO> boardList = boardService.selectSearchStr(searchStr);
    List<QnADTO> qnaList = qnaService.selectSearchStr(authentication.getName(), searchStr);

    model.addAttribute("boardList", boardList);
    model.addAttribute("qnaList", qnaList);
    model.addAttribute("searchStr", searchStr);
    return "search/searchResult";
  }
}
