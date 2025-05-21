package it.uniroma3.siw.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.ImageRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImageService {

	@Autowired private ImageRepository imageRepository;

	public void saveImage(MultipartFile file) throws IOException {
		Image image = new Image();
		image.setData(file.getBytes());
		imageRepository.save(image);
	}

	public Image saveImage(Image image) {
		return imageRepository.save(image);
	}

	public Optional<Image> getImage(Long id) {
		return imageRepository.findById(id);
	}

	public void deleteImage(Long id) {
		imageRepository.deleteById(id);
	}

	public Image findById(Long id) {
		return this.imageRepository.findById(id).orElse(null);
	}

	public void saveImageFromFile(File file) throws IOException {
	    Optional<Image> existingImage = imageRepository.findByName(file.getName()); // Controlla se esiste già
	    if (existingImage.isEmpty()) {  
	        Image image = new Image();
	        image.setName(file.getName());
	        image.setData(Files.readAllBytes(file.toPath()));
	        imageRepository.save(image);
	    }
	}


	public void uploadImagesFromFolder(String folderPath) throws IOException {
		Files.list(Paths.get(folderPath))
		.filter(Files::isRegularFile)
		.forEach(filePath -> {
			try {
				Image image = new Image();
				image.setName(filePath.getFileName().toString());
				image.setData(Files.readAllBytes(filePath));
				imageRepository.save(image);
			} catch (IOException e) {
				System.err.println("Errore nel caricamento dell'immagine: " + filePath);
			}
		});
	}

	public Optional<Image> findByName(String name) {
		return this.imageRepository.findByName(name);
	}

}