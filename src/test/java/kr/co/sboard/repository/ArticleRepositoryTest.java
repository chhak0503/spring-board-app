package kr.co.sboard.repository;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.entity.File;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;



@Log4j2
@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    @Test
    @Transactional
    public void findById(){

        Optional<Article> optArticle = repository.findById(18);

        if(optArticle.isPresent()){
            Article entity = optArticle.get();

            List<File> entityFileList = entity.getFileList();

            List<FileDTO> dtoFileList = entityFileList.stream()
                                                        .map(fileEntity -> fileEntity.toDTO())
                                                        .toList();

            ArticleDTO articleDTO = entity.toDTO();
            articleDTO.setFileList(dtoFileList);

            log.info(articleDTO);
        }
    }

}