package it.uniroma3.siw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.AuthorRepository;
import it.uniroma3.siw.service.ImageService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ImageLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            String folderPath = "src/main/resources/authorImages";
            assignImagesToAuthors(folderPath);  
        } catch (IOException e) {
            System.err.println("Errore nel caricamento delle immagini.");
        }
    }

    private void assignImagesToAuthors(String folderPath) throws IOException {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        List<File> imageFiles = Files.list(Paths.get(folderPath))
                                    .filter(Files::isRegularFile)
                                    .map(Path::toFile)
                                    .collect(Collectors.toList());

        for (int i = 0; i < authors.size() && i < imageFiles.size(); i++) {
            Author author = authors.get(i);
            File imageFile = imageFiles.get(i);

            Optional<Image> existingImage = imageService.findByName(imageFile.getName());
            Image image = existingImage.orElseGet(() -> {
                Image newImage = new Image();
                newImage.setName(imageFile.getName());
                try {
                    newImage.setData(Files.readAllBytes(imageFile.toPath()));
                } catch (IOException e) {
                    System.err.println("Errore nella lettura del file: " + imageFile.getName());
                }
                return imageService.saveImage(newImage);
            });

            author.setPhoto(image);
            authorRepository.save(author);
        }

        System.out.println("Immagini associate automaticamente agli autori!");
    }
}
