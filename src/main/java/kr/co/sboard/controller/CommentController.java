package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.CommentDTO;
import kr.co.sboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/write")
    public ResponseEntity<CommentDTO> write(@RequestBody CommentDTO commentDTO, HttpServletRequest req){
        log.info(commentDTO);

        String regip = req.getRemoteAddr();
        commentDTO.setRegip(regip);

        // 서비스 호출
        CommentDTO savedComment = commentService.register(commentDTO);

        return ResponseEntity.ok(savedComment);
    }


}
