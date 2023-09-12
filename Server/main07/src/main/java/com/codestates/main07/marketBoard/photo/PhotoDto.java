package com.codestates.main07.marketBoard.photo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoDto {
    private String originPhotoName;
    private String newPhotoName;
    private String photoPath;

    @Builder
    public PhotoDto(String originPhotoName, String newPhotoName, String photoPath) {
        this.originPhotoName = originPhotoName;
        this.newPhotoName = newPhotoName;
        this.photoPath = photoPath;
    }
}
