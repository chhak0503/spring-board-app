package kr.co.sboard.controller;

import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;

@Log4j2
@RequiredArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;

    @ResponseBody
    @GetMapping("/file/download")
    public ResponseEntity download(int fno){

        // 서비스 호출
        FileDTO fileDTO = fileService.get(fno);
        log.info(fileDTO);

        // 파일 다운로드 스트림 서비스
        FileDTO downloadedFile = fileService.download(fileDTO);

        // 파일 다운로드 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition
                        .builder("attachment")
                        .filename(fileDTO.getOfname(), StandardCharsets.UTF_8)
                        .build()
        );

        headers.add(HttpHeaders.CONTENT_TYPE, downloadedFile.getContentType());

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(downloadedFile.getResource());
    }

}
