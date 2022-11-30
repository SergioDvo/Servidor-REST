package jakarta.rest;

import domain.servicios.ServicesReaders;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path(ConstatesRest.READ_ARTICLE)
public class RestReaderArticle {

    private final ServicesReaders servicesReaders;

    @Inject
    public RestReaderArticle(ServicesReaders servicesReaders) {
        this.servicesReaders = servicesReaders;
    }

    @POST
    public Response addReadArticle(@QueryParam(ConstatesRest.ID_ARTICLE) int idArticle, @QueryParam(ConstatesRest.ID_READER) int idReader, @QueryParam(ConstatesRest.RATE) int rate) {
        servicesReaders.addReaderArticle(idArticle, idReader, rate);
        return Response.status(Response.Status.CREATED).build();
    }
}
