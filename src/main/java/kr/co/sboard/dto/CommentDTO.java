package kr.co.sboard.dto;

import kr.co.sboard.entity.Comment;
import kr.co.sboard.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    private int cno;
    private int parent;
    private String content;
    private String writer;
    private String regip;
    private String wdate;

    // 추가필드
    private String nick;

    public Comment toEntity(){

        User user = User.builder()
                .userid(writer)
                .build();

        return Comment.builder()
                .cno(cno)
                .parent(parent)
                .content(content)
                .user(user)
                .regip(regip)
                .build();
    }

}
