package petstore.ch3.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gs.fw.common.mithra.MithraManager;
import petstore.ch3.domain.Person;
import petstore.ch3.domain.PersonFinder;
import petstore.ch3.domain.PersonList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by stanle on 11/28/16.
 */
@Path("/api/person")
public class PersonResource
{
    @POST
    public Response createPerson(
            @FormParam("personId") int personId,
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName)
    {
        Person person = new Person();
        person.setPersonId(personId);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.insert();
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonById(
            @PathParam("id") int personId) throws JsonProcessingException {
        return PersonFinder.findByPrimaryKey(personId);
    }

    @GET
    @Path("/bulk/{lastName}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonList getPersonsByLastName(@PathParam("lastName") String lastName)
    {
        return PersonFinder.findMany(PersonFinder.lastName().eq(lastName));
    }


    @PUT
    @Path("/{id}")
    public Response update(
            @PathParam("id") int personId,
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName)
    {
        MithraManager.getInstance().executeTransactionalCommand((tx) -> {
            Person person = PersonFinder.findOne(PersonFinder.personId().eq(personId));
            person.setFirstName(firstName);
            person.setLastName(lastName);
            return null;
        });
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(
            @PathParam("id") int personId) {
        Person person = PersonFinder.findOne(PersonFinder.personId().eq(personId));
        person.delete();
        return Response.ok().build();
    }
}
