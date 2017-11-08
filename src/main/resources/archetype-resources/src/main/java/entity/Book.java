package ${groupId}.${artifactId}.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({ 
	@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
	@NamedQuery(name = "Book.findBookById", query = "SELECT b FROM Book b WHERE b.id = :id")
	})
@Table(name = "book")
@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 2272437589466457940L;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(columnDefinition="CHAR(36)")
	private String id;
	@Column(columnDefinition="CHAR(10)")
	private String isbn;
	@Column(columnDefinition="VARCHAR(80)")
	private String titulo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Book(String id, String isbn, String titulo) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.titulo = titulo;
	}

}
