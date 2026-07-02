package kr.co.sboard.dto;

import kr.co.sboard.entity.File;
import lombok.*;
import org.springframework.core.io.Resource;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {

    private int fno;
    private int ano;
    private String ofname;
    private String sfname;
    private int download;
    private String rdate;

    // 추가필드
    private String contentType;
    private Resource resource;

    public File toEntity(){
        return File.builder()
                .fno(fno)
                .ano(ano)
                .ofname(ofname)
                .sfname(sfname)
                .download(download)
                .build();
    }

}
