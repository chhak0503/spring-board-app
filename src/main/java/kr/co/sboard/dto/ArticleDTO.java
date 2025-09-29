package kr.co.sboard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDTO {

    private int ano;

    @Builder.Default
    private String cate = "free";

    private String title;
    private String content;
    private int comment_cnt;
    private int file_cnt;
    private int hit_cnt;
    private String writer;
    private String reg_ip;
    private String wdate;

}
