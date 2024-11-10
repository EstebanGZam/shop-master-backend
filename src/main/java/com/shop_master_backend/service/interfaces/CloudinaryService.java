package com.shop_master_backend.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    String uploadFile(MultipartFile file, String folderName);

}
