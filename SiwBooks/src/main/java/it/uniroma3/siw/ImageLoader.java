package it.uniroma3.siw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.AuthorRepository;
import it.uniroma3.siw.repository.BookRepository;
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
    private BookRepository bookRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            String authorFolderPath = "src/main/resources/databaseImage/authorImages";
            String bookFolderPath = "src/main/resources/databaseImage/bookImages";
            
            assignImagesToAuthors(authorFolderPath);
            assignImagesToBooks(bookFolderPath);
            
        } catch (IOException e) {
            System.err.println("Errore nel caricamento delle immagini.");
        }
    }

    private void assignImagesToAuthors(String folderPath) throws IOException {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        List<File> imageFiles = getImageFilesFromFolder(folderPath);

        for (int i = 0; i < authors.size() && i < imageFiles.size(); i++) {
            Author author = authors.get(i);
            File imageFile = imageFiles.get(i);
            processImage(author::setPhoto, imageFile);
            authorRepository.save(author);
        }
        System.out.println("Immagini autori associate con successo!");
    }

    private void assignImagesToBooks(String folderPath) throws IOException {
        List<Book> books = (List<Book>) bookRepository.findAll();
        List<File> imageFiles = getImageFilesFromFolder(folderPath);

        for (int i = 0; i < books.size() && i < imageFiles.size(); i++) {
            Book book = books.get(i);
            File imageFile = imageFiles.get(i);
            processImage(book::setCover, imageFile);
            bookRepository.save(book);
        }
        System.out.println("Immagini libri associate con successo!");
    }

    private List<File> getImageFilesFromFolder(String folderPath) throws IOException {
        return Files.list(Paths.get(folderPath))
                   .filter(Files::isRegularFile)
                   .map(Path::toFile)
                   .collect(Collectors.toList());
    }

    private void processImage(java.util.function.Consumer<Image> setter, File imageFile) {
        Optional<Image> existingImage = imageService.findByName(imageFile.getName());
        Image image = existingImage.orElseGet(() -> createNewImage(imageFile));
        setter.accept(image);
    }

    private Image createNewImage(File imageFile) {
        Image newImage = new Image();
        newImage.setName(imageFile.getName());
        try {
            newImage.setData(Files.readAllBytes(imageFile.toPath()));
        } catch (IOException e) {
            System.err.println("Errore nella lettura del file: " + imageFile.getName());
        }
        return imageService.saveImage(newImage);
    }
}