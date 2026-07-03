package kr.co.sboard.entity;

import jakarta.persistence.*;
import kr.co.sboard.dto.CommentDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    private int parent;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private User user;

    private String regip;

    @CreationTimestamp
    private LocalDateTime wdate;

    public CommentDTO toDTO(){
        return CommentDTO.builder()
                .cno(cno)
                .parent(parent)
                .content(content)
                .writer(user.getUserid())
                .nick(user.getNick())
                .regip(regip)
                .wdate(wdate.toString())
                .build();
    }
}
