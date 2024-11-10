package com.shop_master_backend.service.impl;

import com.cloudinary.Cloudinary;
import com.shop_master_backend.service.interfaces.CloudinaryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Resource
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file, String folderName) {
        try {
            // Crea un mapa de opciones para configurar el proceso de carga en Cloudinary
            HashMap<Object, Object> options = new HashMap<>();

            // Configura el nombre de la carpeta donde se almacenará la imagen en Cloudinary
            options.put("folder", folderName);

            // Realiza la carga del archivo (imagen) a Cloudinary usando el archivo en bytes y las opciones configuradas
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);

            // Obtiene el ID público del archivo cargado.
            String publicId = (String) uploadedFile.get("public_id");

            // Genera y devuelve la URL segura para acceder a la imagen cargada en Cloudinary
            return cloudinary.url().secure(true).generate(publicId);
        } catch (IOException e) {
            throw new RuntimeException("Error uploading image to Cloudinary", e);
        }
    }
    
}
