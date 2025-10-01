package kr.co.sboard.controller;

import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;

    @GetMapping("/file/download")
    public String download(int fno){
        log.info("fno = {}", fno);

        FileDTO fileDTO = fileService.getFile(fno);

        fileService.download(fileDTO);

        return "";
    }

}
