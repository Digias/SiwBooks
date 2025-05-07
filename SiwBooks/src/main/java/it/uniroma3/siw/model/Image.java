package it.uniroma3.siw.model;

import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Image {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fileName; 		// Nome del file originale, utile per info

    private String contentType; 	// Tipo MIME, es. "image/png", "image/jpeg"

    @Lob
    @Column(nullable = false)
    private byte[] data; 		// Contenuto dell'immagine
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@OneToOne
	private Author author;
	
	@ManyToOne
	private Book book;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + Objects.hash(author, book, contentType, fileName, id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(author, other.author) && Objects.equals(book, other.book)
				&& Objects.equals(contentType, other.contentType) && Arrays.equals(data, other.data)
				&& Objects.equals(fileName, other.fileName) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", fileName=" + fileName + ", contentType=" + contentType + ", data="
				+ Arrays.toString(data) + ", author=" + author + ", book=" + book + "]";
	}
	
	
}
