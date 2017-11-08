package ${groupId}.${artifactId}.resources;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.jackson.Formatted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import danielfcastro.healthcheck.IHealthCheckService;
import ${groupId}.${artifactId}.entity.Book;
import ${groupId}.${artifactId}.repository.BookRepositoryImpl;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource implements IHealthCheckService {
	private static final Logger logger = LoggerFactory.getLogger(BookResource.class);
	private static final String CONTENT_TYPE = "Content-Type";
	
	@Inject
	BookRepositoryImpl repository;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Formatted
	public Response getBooks() {
		logger.info("Início");
		Response response = null;
		List<Book> entity = repository.query(null);
		if (entity.size() != 0) {
			response = Response.ok().entity(entity).build();
		} else {
			response = Response.noContent().build();
		}
		logger.info("Fim");
		return response;
	}

	@GET
	@Path("/book/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Formatted
	public Response getBook(@PathParam("id") String id) {
		logger.info("Início");
		Response response = null;
		if (id == null || id.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("id não pode ser nulo!").build();
		}
		List<Book> entity = repository.query(id);
		if (entity.size() != 0) {
			response = Response.ok().entity(entity).build();
		} else {
			response = Response.noContent().build();
		}
		logger.info("Fim");
		return response;
	}

	@PUT
	@Path("/book/{id}")
	@Formatted
	public Response addBook(@PathParam("id") String id, @QueryParam("isbn") String isbn,
			@QueryParam("titulo") String titulo) {
		logger.info("Início");
		if (id == null || id.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("id não pode ser nulo!").build();
		} else if (isbn == null || isbn.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("isbn não pode ser nulo!").build();
		} else if (titulo == null || titulo.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("titulo não pode ser nulo!").build();
		}

		Book novo = new Book(id, isbn, titulo);
		repository.add(novo);
		logger.info("Fim");
		return Response.status(Response.Status.CREATED).entity("Livro inserido com sucesso!").build();
	}

	@POST
	@Path("/book/{id}")
	@Formatted
	public Response updateBook(@PathParam("id") String id, @QueryParam("isbn") String isbn,
			@QueryParam("titulo") String titulo) {
		logger.info("Início");
		if (id == null || id.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("id não pode ser nulo!").build();
		} else if (isbn == null || isbn.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("isbn não pode ser nulo!").build();
		} else if (titulo == null || titulo.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("titulo não pode ser nulo!").build();
		}

		Book novo = new Book(id, isbn, titulo);
		repository.update(novo);
		logger.info("Fim");
		return Response.ok("Livro atualizado com sucesso!", MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path("/book/{id}")
	@Formatted
	public Response removeBook(@PathParam("id") String id) {
		logger.info("Início");
		if (id == null || id.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("id não pode ser nulo!").build();
		}
		repository.remove(id);
		logger.info("Fim");
		return Response.ok("Livro removido com sucesso!", MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("/health_check")
	@Produces(MediaType.APPLICATION_JSON)
	@Formatted
	public Response health_check(@QueryParam("timeout") int timeout) {
		logger.info("Início");
		logger.info("Fim");
		return null;
	}

	@OPTIONS
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response options() {
		logger.info("Início");
		Response response = Response.status(200).header("Allow", "POST, PUT, GET, DELETE, OPTIONS, HEAD")
				.header("Content-Type", MediaType.APPLICATION_JSON).header("Content-Length", "0").build();
		logger.info("Fim");
		return response;
	}

	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response head() {
		logger.info("Início");
		Response retorno = Response.ok().header(BookResource.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();
		logger.info("Fim");
		return retorno;
	}
	
	
}
