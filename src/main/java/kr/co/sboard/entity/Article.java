package kr.co.sboard.entity;

import jakarta.persistence.*;
import kr.co.sboard.dto.ArticleDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.awt.image.WritableRaster;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ano;
    private String type;
    private String title;
    private String content;
    private int comment;
    private int file;
    private int hit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private User user;

    private String regip;

    @CreationTimestamp
    private LocalDateTime wdate;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ano")
    private List<File> fileList;


    public ArticleDTO toDTO(){
        return ArticleDTO.builder()
                .ano(ano)
                .type(type)
                .title(title)
                .content(content)
                .comment(comment)
                .file(file)
                .hit(hit)
                .nick(user.getNick())
                .writer(user.getUserid())
                .regip(regip)
                .wdate(wdate.toString())
                .build();
    }
}
