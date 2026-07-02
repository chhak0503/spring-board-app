package kr.co.sboard.dao;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleDAO {
    public int insert(ArticleDTO dto);
    public ArticleDTO select(int ano);
    public List<ArticleDTO> selectAll(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);

    public int selectCountAll(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO);

    public void update(ArticleDTO dto);
    public void updateHit(int ano);
    public void delete(int ano);
}
