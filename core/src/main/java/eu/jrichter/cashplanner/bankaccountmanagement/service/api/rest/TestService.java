package eu.jrichter.cashplanner.bankaccountmanagement.service.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.Bankaccountmanagement;
import eu.jrichter.cashplanner.bankaccountmanagement.logic.api.to.AccountingEntryEto;

/**
 * The service interface for REST calls in order to execute the logic of component {@link Bankaccountmanagement}.
 */
@Path("/test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public interface TestService {

  @GET
  @Path("/get/{id}/")
  public AccountingEntryEto getAccountingEntry(@PathParam("id") long id);

  @GET
  @Path("/import/")
  public void importAccountTransactionReportFile();
}
