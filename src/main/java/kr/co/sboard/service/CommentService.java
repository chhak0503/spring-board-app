package kr.co.sboard.service;

import kr.co.sboard.dao.ArticleDAO;
import kr.co.sboard.dao.CommentDAO;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.CommentDTO;
import kr.co.sboard.entity.Comment;
import kr.co.sboard.repository.ArticleRepository;
import kr.co.sboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentDAO dao;
    private final CommentRepository repository;

    public CommentDTO get(int cno){
        return null;
    }

    public List<CommentDTO> getAll(int parent){

        List<Comment> entityList = repository.findAllByParent(parent);

        return entityList.stream()
                .map(entity -> entity.toDTO())
                .toList();
    }

    public CommentDTO register(CommentDTO dto){

        Comment entity = dto.toEntity();

        Comment savedComment = repository.save(entity);
        log.info("savedComment = {}", savedComment);


        return savedComment.toDTO();
    }


    public void modify(CommentDTO dto){

    }
    public void remove(int cno){
        repository.deleteById(cno);
    }
}
