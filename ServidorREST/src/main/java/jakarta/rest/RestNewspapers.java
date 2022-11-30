package jakarta.rest;

import dao.modelo.Newspaper;
import domain.servicios.ServicesNewspaper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(ConstatesRest.NEWSPAPERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestNewspapers {

    private final ServicesNewspaper servicesNewspaper;

    @Inject
    public RestNewspapers(ServicesNewspaper servicesNewspaper) {
        this.servicesNewspaper = servicesNewspaper;
    }

    @GET
    @Path(ConstatesRest.ALL)
    public Response getAllNewspapers() {
        return Response.ok(servicesNewspaper.getNewspaperList()).build();
    }

    @POST
    public Response saveNewspaper(Newspaper newspaper) {
        if (servicesNewspaper.addNewspaper(newspaper)) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    public Response updateNewspaper(Newspaper newspaper) {
        if (servicesNewspaper.updateNewspaper(newspaper)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path(ConstatesRest.DELETE_READER)
    public Response deleteNewspaper(@QueryParam(ConstatesRest.ID) int id) {
        servicesNewspaper.deleteNewspaper(id);
        return Response.status(Response.Status.OK).build();
    }

}
