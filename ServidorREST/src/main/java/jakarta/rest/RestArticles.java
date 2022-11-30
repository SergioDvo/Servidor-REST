package jakarta.rest;

import dao.modelo.Article;
import domain.servicios.ServicesArticle;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(ConstatesRest.ARTICLES)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestArticles {
    private final ServicesArticle servicesArticle;

    @Inject
    public RestArticles(ServicesArticle servicesArticle) {
        this.servicesArticle = servicesArticle;
    }

    @GET
    @Path(ConstatesRest.ALL)
    public Response getAllNewspapers() {
        return Response.ok(servicesArticle.getArticleList()).build();
    }

    @POST
    public Response saveNewspaper(Article article) {
        servicesArticle.addArticle(article);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path(ConstatesRest.BY_TYPE_ID)
    public Response getArticleListByType(@QueryParam(ConstatesRest.ID) int id) {
        return Response.ok(servicesArticle.getArticleListByType(id)).build();
    }
}
