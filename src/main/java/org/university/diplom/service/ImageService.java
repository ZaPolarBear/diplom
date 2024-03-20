package org.university.diplom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.model.Image;
import org.university.diplom.repository.ImageRepository;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public boolean checkImageExists(String function, FunctionType functionType) {
        return imageRepository.existsByFunction(function, functionType);
    }

    public void saveImage(String imageUrl, FunctionType functionType) {
        Image image = new Image (imageUrl, functionType);
        imageRepository.save(image);
    }

    public Image getImageByFunction(String function, FunctionType functionType) {
        return imageRepository.findByFunction(function, functionType);
    }
}