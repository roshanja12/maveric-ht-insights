package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.extern.slf4j.Slf4j;
import org.acme.service.InsightsService;
import org.acme.utils.CommonUtil;
import org.jboss.logging.Logger;

import java.util.Map;

@Path("/insights")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class InsightsResource {

    @Inject
    CommonUtil commonUtil;

    @Inject
    InsightsService insightsService;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("/GetCustomerCountByMonth")
    public Response getCustomerCountByMonth(@QueryParam("year") int year) {
        log.info("Getting customer count by month for year: {}", year);
        Map<String, Long> response = insightsService.getCustomerCountByMonth(year);

        boolean allMonthsZero = response.values().stream().allMatch(count -> count == 0);

        if (allMonthsZero) {
            return commonUtil.buildFailedResponse("Data not found for year ", Response.Status.NOT_FOUND,null,response,uriInfo);
        }
        return commonUtil.buildSuccessResponse("Successfully retrieved customer count by month for year ", Response.Status.OK, null, response, uriInfo);
    }


}
