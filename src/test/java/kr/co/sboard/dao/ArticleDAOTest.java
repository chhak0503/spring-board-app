package kr.co.sboard.dao;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class ArticleDAOTest {

    @Autowired
    private ArticleDAO dao;

    @Test
    void insert() {
    }

    @Test
    void select() {

        ArticleDTO articleDTO = dao.select(18);

        log.info(articleDTO);
    }

    @Test
    void selectCountAll() {
        //int total = dao.selectCountAll();
        //log.info(total);
    }

    @Test
    void selectAll() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                                        //.searchType("writer")
                                        //.keyword("유신")
                                        .build();

        List<ArticleDTO> dtoList = dao.selectAll(pageRequestDTO);

        for(ArticleDTO dto : dtoList){
            log.info(dto);
        }

        /*
        List<ArticleDTO> dtoList = dao.selectAll(start);

        for(ArticleDTO dto : dtoList){
            log.info(dto);
        }
        */
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}