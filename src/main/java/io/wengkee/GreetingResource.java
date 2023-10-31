package io.wengkee;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Weng Kee";
    }

    @GET
    @Path("/chinese")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloChinese() {
        return "你好，我是 Weng Kee!";
    }
}
