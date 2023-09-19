package com.codestates.main07.marketBoard.photo;

import com.codestates.main07.exception.BusinessLogicException;
import com.codestates.main07.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PhotoService {

    private final PhotoDao photoDao;
    private final String photoUploadDirectory = "uploads";

    public void insertPhoto(MultipartFile photo) throws Exception {
        // 파일이 빈 것이 들어오면 메서드 종료
        if (photo.isEmpty()) {
            return;
        }

        // save 할 DTO 띄우기
        PhotoDto photoDto = new PhotoDto();

        //저장할 파일경로 지정
        String absolutePath = new File("src/main/resources/static/photos/").getAbsolutePath();

        // jpeg, png, gif 파일들만 받아서 처리
        if (!photo.isEmpty()) {
            String contentType = photo.getContentType();
            String originalPhotoExtension;
            // 확장자 명이 없으면 종료
            if (!StringUtils.hasText(contentType)) {
                return;
            } else {
                if (contentType.contains("image/jpeg")) {
                    originalPhotoExtension = ".jpg";
                } else if (contentType.contains("image/png")) {
                    originalPhotoExtension = ".png";
                } else if (contentType.contains("image/gif")) {
                    originalPhotoExtension = ".gif";
                }
                // 기타 확장자명일 경우 메서드 종료
                else {
                    return;
                }
            }
            String newPhotoName = UUID.randomUUID().toString() + originalPhotoExtension;

            // DTO에 담기
            photoDto = PhotoDto.builder()
                    .originPhotoName(photo.getOriginalFilename())
                    .newPhotoName(newPhotoName)
                    .photoPath(absolutePath)
                    .build();

            photoDao.save(photoDto);

            // 파일을 전송하기
            File file = new File(absolutePath + "/" + newPhotoName);
            if (!file.exists()) {
                file.mkdirs();
            }
            photo.transferTo(file);
        }
    }
}
