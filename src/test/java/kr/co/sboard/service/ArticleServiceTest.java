package kr.co.sboard.service;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService service;

    @Test
    void getAll(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .build();

        PageResponseDTO pageResponseDTO = service.getAll(pageRequestDTO);

        log.info(pageResponseDTO);
    }

    @Test
    void findAll() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .build();

        PageResponseDTO pageResponseDTO = service.findAll(pageRequestDTO);

        for(ArticleDTO dto : pageResponseDTO.getDtoList()){
            log.info(dto);
        }
    }
}