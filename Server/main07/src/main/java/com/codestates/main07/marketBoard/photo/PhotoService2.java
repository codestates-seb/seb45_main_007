//package com.codestates.main07.marketBoard.photo;
//
//import com.codestates.main07.exception.BusinessLogicException;
//import com.codestates.main07.exception.ExceptionCode;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//@RequiredArgsConstructor
//@Service
//public class PhotoService2 {
//    private final PhotoDao photoDao;
//    private final String photoUploadDirectory = "uploads";
//
//    public void insertPhoto(MultipartFile photo) {
//        // 파일이 빈 것이 들어오면 메서드 종료
//        try {
//            if (photo.isEmpty()) {
//                return;
//            }
//
//            // 확장자를 추출하는 함수 사용
//            String originalFilename = photo.getOriginalFilename();
//            String fileExtension = StringUtils.getFilenameExtension(originalFilename);
//
//            // 허용되는 이미지 확장자 목록 설정
//            String[] allowedExtensions = {"jpg", "jpeg", "png", "gif"};
//            boolean isAllowedExtension = false;
//
//            for (String extension : allowedExtensions) {
//                if (extension.equalsIgnoreCase(fileExtension)) {
//                    isAllowedExtension = true;
//                    break;
//                }
//            }
//
//            if (!isAllowedExtension) {
//                // 허용되지 않는 확장자의 경우
//                throw new BusinessLogicException(ExceptionCode.UNKNOWN_ERROR);
//            }
//
//            // 새로운 파일 이름을 UUID로 생성
//            String newPhotoName = UUID.randomUUID().toString() + "." + fileExtension;
//
//            // 업로드 디렉토리 설정
//            Path uploadPath = Paths.get(photoUploadDirectory);
//
//            // 업로드 디렉토리가 존재하지 않으면 생성
//            if (!uploadPath.toFile().exists()) {
//                uploadPath.toFile().mkdirs();
//            }
//
//            // 파일을 저장할 경로 설정
//            Path filePath = uploadPath.resolve(newPhotoName);
//
//            // 파일 저장
//            photo.transferTo(filePath.toFile());
//
//            // 데이터베이스에 사진 정보 저장
//            PhotoDto photoDto = new PhotoDto(originalFilename, newPhotoName, filePath.toString());
//            photoDao.save(photoDto);
//        } catch (Exception e) {
//            // 에러 처리
//            throw new BusinessLogicException(ExceptionCode.UNKNOWN_ERROR);
//        }
//    }
//}
//
