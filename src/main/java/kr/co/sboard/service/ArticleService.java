package kr.co.sboard.service;

import kr.co.sboard.dao.ArticleDAO;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.entity.User;
import kr.co.sboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleDAO dao;
    private final ArticleRepository repository;

    public ArticleDTO get(int ano){

        // Mybatis
        ArticleDTO articleDTO = dao.select(ano);

        // JPA


        return null;
    }

    public PageResponseDTO getAll(PageRequestDTO pageRequestDTO){
        // Mybatis
        List<ArticleDTO> dtoList = dao.selectAll(pageRequestDTO);

        int total = dao.selectCountAll(pageRequestDTO);

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }

    public PageResponseDTO findAll(PageRequestDTO pageRequestDTO){
        // Pageable은 JPA에서 페이징 처리를 위한 객체
        Pageable pageable = pageRequestDTO.getPageable("ano");

        Page<Article> pageArticle = repository.findAll(pageable);

        List<ArticleDTO> dtoList = pageArticle.getContent()
                                            .stream()
                                            .map(entity -> {
                                                // 엔티티를 DTO 변환
                                                ArticleDTO dto = entity.toDTO();

                                                // 엔티티에 관계설정한 User 엔티티 참조
                                                User user = entity.getUser();

                                                // User 엔티티의 nick 가져오기
                                                dto.setNick(user.getNick());

                                                return dto;
                                            })
                                            .toList();

        int total = (int) repository.count(); // 전체 갯수

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }

    public void register(ArticleDTO dto){
        dao.insert(dto);
    }

    public void modify(ArticleDTO dto){

    }
    public void remove(int ano){

    }
}
