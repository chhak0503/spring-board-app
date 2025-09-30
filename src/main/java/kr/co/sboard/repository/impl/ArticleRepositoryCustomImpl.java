package kr.co.sboard.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.sboard.entity.QArticle;
import kr.co.sboard.entity.QUser;
import kr.co.sboard.repository.custom.ArticleRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private QArticle qArticle = QArticle.article;
    private QUser qUser = QUser.user;

    @Override
    public Page<Tuple> selectArticleAllForList() {

        List<Tuple> tupleList = jpaQueryFactory.select(qArticle, qUser)
                                                .from(qArticle)
                                                .join(qUser)
                                                .on(qArticle.writer.eq(qUser.usid))
                                                .offset(0)
                                                .limit(10)
                                                .orderBy(qArticle.ano.desc())
                                                .fetch();

        return new PageImpl<Tuple>(tupleList, PageRequest.of(0, 10), tupleList.size());
    }
}
