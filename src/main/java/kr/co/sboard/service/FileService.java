package kr.co.sboard.service;

import kr.co.sboard.dao.ArticleDAO;
import kr.co.sboard.dao.FileDAO;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.repository.ArticleRepository;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class FileService {

    private final FileDAO dao;
    private final FileRepository repository;

    @Value("${spring.servlet.multipart.location}")
    private String fileUploadPath;

    public List<FileDTO> upload(ArticleDTO articleDTO){

        File fileUploadDir = new File(fileUploadPath);

        if(!fileUploadDir.exists()){
            fileUploadDir.mkdirs();
        }

        String path = fileUploadDir.getAbsolutePath();

        // 반환용 파일 리스트
        List<FileDTO> fileList = new ArrayList<>();

        for(MultipartFile multiFile : articleDTO.getFiles()){

            // 파일을 첨부 했으면
            if(!multiFile.isEmpty()){
                String ofname = multiFile.getOriginalFilename();
                String ext = ofname.substring(ofname.lastIndexOf("."));
                String sfname = UUID.randomUUID().toString() + ext;

                try {
                    // 파일 저장
                    multiFile.transferTo(new File(path, sfname));

                    // 반환용 파일 객체
                    FileDTO fileDTO = FileDTO.builder()
                                        .ofname(ofname)
                                        .sfname(sfname)
                                        .build();

                    // 리스트 추가
                    fileList.add(fileDTO);

                }catch (Exception e) {
                    log.error(e.getMessage());
                } // try-catch 끝
            } // if 끝
        } // for 끝

        return fileList;
    }

    public FileDTO download(FileDTO fileDTO){

        Path path = Paths.get(fileUploadPath + File.separator + fileDTO.getSfname());

        try {
            // 파일 컨텐츠타입 확인
            String contentType = Files.probeContentType(path);

            // 파일 자원 객체
            Resource resource = new InputStreamResource(Files.newInputStream(path));

            // DTO 다운로드 파일 관련 속성 초기화
            fileDTO.setContentType(contentType);
            fileDTO.setResource(resource);

        }catch (Exception e){
            log.error(e.getMessage());
        }

        return fileDTO;
    }

    public FileDTO get(int fno){
        FileDTO fileDTO = dao.select(fno);
        return fileDTO;
    }

    public List<FileDTO> getAll(){
        return null;
    }

    public void register(List<FileDTO> fileList, int ano){

        for(FileDTO fileDTO : fileList){
            fileDTO.setAno(ano);
            repository.save(fileDTO.toEntity());
        }
    }
    public void modify(FileDTO dto){

    }
    public void remove(int fno){

    }
}
