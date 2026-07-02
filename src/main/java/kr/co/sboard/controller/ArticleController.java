package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.service.ArticleService;
import kr.co.sboard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final FileService fileService;

    @GetMapping("/article/list")
    public String list(Model model, PageRequestDTO pageRequestDTO){
        log.info(pageRequestDTO);

        // 목록 데이터 가져오기

        // Mybatis
        //PageResponseDTO pageResponseDTO = articleService.getAll(pageRequestDTO);

        // JPA
        PageResponseDTO pageResponseDTO = articleService.findAll(pageRequestDTO);

        // 모델 참조
        model.addAttribute(pageResponseDTO);

        return "/article/list";
    }

    @GetMapping("/article/modify")
    public String modify(){
        return "/article/modify";
    }

    @GetMapping("/article/search")
    public String search(PageRequestDTO pageRequestDTO, Model model){
        log.info(pageRequestDTO);

        // 서비스 호출
        PageResponseDTO pageResponseDTO = articleService.getAll(pageRequestDTO);
        // 모델참조
        model.addAttribute(pageResponseDTO);

        return "/article/search";
    }

    @GetMapping("/article/view")
    public String view(int ano, Model model){
        log.info(ano);

        // 서비스 호출
        //ArticleDTO articleDTO = articleService.get(ano);  // Mybatis
        ArticleDTO articleDTO = articleService.find(ano);   // JPA
        log.info(articleDTO);

        model.addAttribute(articleDTO);

        return "/article/view";
    }

    @GetMapping("/article/write")
    public String write(){
        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(ArticleDTO articleDTO, HttpServletRequest req){
        log.info(articleDTO);

        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);

        // 파일 업로드
        List<FileDTO> fileList = fileService.upload(articleDTO);

        // 파일 첨부 갯수 초기화
        articleDTO.setFile(fileList.size());

        // 글등록
        articleService.register(articleDTO);

        return "redirect:/article/list";
    }
}
