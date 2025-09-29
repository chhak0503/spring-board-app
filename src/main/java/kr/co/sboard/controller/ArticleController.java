package kr.co.sboard.controller;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article/list")
    public String list(){
        return "article/list";
    }

    @GetMapping("/article/modify")
    public String modify(){
        return "article/modify";
    }

    @GetMapping("/article/searchList")
    public String searchList(){
        return "article/searchList";
    }

    @GetMapping("/article/view")
    public String view(){
        return "article/view";
    }

    @GetMapping("/article/write")
    public String write(){
        return "article/write";
    }

    @PostMapping("/article/write")
    public String write(ArticleDTO articleDTO){

        log.info("articleDTO = {}", articleDTO);


        articleService.save(articleDTO);

        return "redirect:/article/list";
    }


}
