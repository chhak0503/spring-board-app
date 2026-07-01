package kr.co.sboard.service;

import kr.co.sboard.dao.ArticleDAO;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleDAO dao;
    private final ArticleRepository repository;

    public ArticleDTO get(int ano){
        return null;
    }

    public int getLastPageNum() {
        // 전체 글 갯수
        int total = getTotal();

        // 마지막 페이지 번호
        int lastPageNum = 0;

        if(total % 10 == 0){
            lastPageNum = total / 10;
        }else {
            lastPageNum = total / 10 + 1;
        }

        return lastPageNum;
    }

    public int getStart(int page){
        // 목록 LIMIT 시작값
        int start = (page - 1) * 10;
        return start;
    }

    public int getTotal(){
        return dao.selectCountAll();
    }

    public List<ArticleDTO> getAll(int start){

        // Mybatis
        List<ArticleDTO> dtoList = dao.selectAll(start);



        return dtoList;
    }

    public List<ArticleDTO> findAll(){

        // JPA
        List<Article> entityList = repository.findAll();

        List<ArticleDTO> dtoList = entityList
                .stream()
                .map(entity -> entity.toDTO())
                .toList();
        return dtoList;
    }

    public void register(ArticleDTO dto){
        dao.insert(dto);
    }

    public void modify(ArticleDTO dto){

    }
    public void remove(int ano){

    }
}
