package kr.co.sboard.repository.custom;


import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;

public interface ArticleRepositoryCustom {

    public Page<Tuple> selectArticleAllForList();

}
