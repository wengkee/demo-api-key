package io.wengkee;

import io.quarkus.logging.Log;
import io.quarkus.security.UnauthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import java.util.Base64;

class ApiFilter {

    private static final Logger LOG = Logger.getLogger(ApiFilter.class);

    @ConfigProperty(name = "my.api.key")
    private String apikey;

    @ServerRequestFilter(preMatching = true)
    public void filterApiKey(ContainerRequestContext ctx){

        String headerApiKey = ctx.getHeaderString("x-api-key");

        String decodedKey = new String(Base64.getDecoder().decode(headerApiKey));

        LOG.info("headerApiKey: " + headerApiKey);
        Log.info("decodedKey: " + decodedKey);
        LOG.info("apikey: " + apikey);

        if ( decodedKey == null || !decodedKey.equals(apikey)){
            throw new UnauthorizedException();
//            throw new ForbiddenException();
        }
    }
}
