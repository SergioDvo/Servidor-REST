package jakarta.rest;

import dao.modelo.Subscription;
import domain.servicios.ServicesReaders;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(ConstatesRest.SUBSCRIPTIONS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestSubscriptions {
    private final ServicesReaders servicesReaders;

    @Inject
    public RestSubscriptions(ServicesReaders servicesReaders) {
        this.servicesReaders = servicesReaders;
    }

    @POST
    public Response addSubscription(Subscription subscription) {
        servicesReaders.addSubscription(subscription);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response cancelSubscription(Subscription subscription) {
        servicesReaders.cancelSubscription(subscription);
        return Response.status(Response.Status.OK).build();
    }
}
