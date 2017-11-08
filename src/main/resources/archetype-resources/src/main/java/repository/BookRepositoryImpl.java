package ${groupId}.${artifactId}.repository;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import ${groupId}.${artifactId}.entity.Book;

@Named("book-repository")
@RequestScoped
public class BookRepositoryImpl implements Repository<Book> {

	@PersistenceContext(unitName = "BookRepository")
	private EntityManager entityManager;

	public BookRepositoryImpl() {
		super();
	}
	
	public BookRepositoryImpl(EntityManager manager) {
		this.entityManager = manager;
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void add(Book item) {
		entityManager.persist(item);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void update(Book item) {
		entityManager.merge(item);
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void remove(Book item) {
		entityManager.remove(item);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void remove(String id) {
		Book toBeDelete = entityManager.find(Book.class, id);
		entityManager.remove(toBeDelete);
	}

	public List<Book> query(String id) {
		TypedQuery<Book> query = null;
		if (null != id) {
			query = entityManager.createNamedQuery("Book.findBookById", Book.class);
			query.setParameter("id", id);
		} else {
			query = entityManager.createNamedQuery("Book.findAll", Book.class);
		}
		List<Book> results = query.getResultList();
		return results;
	}

}
