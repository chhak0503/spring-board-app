package kr.co.sboard.dao;

import kr.co.sboard.dto.ArticleDTO;
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
    }

    @Test
    void selectCountAll() {
        int total = dao.selectCountAll();
        log.info(total);
    }

    @Test
    void selectAll() {
        int start = 10;

        List<ArticleDTO> dtoList = dao.selectAll(start);

        for(ArticleDTO dto : dtoList){
            log.info(dto);
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}