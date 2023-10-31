package io.wengkee;

import io.quarkus.security.UnauthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

class ApiFilter {

    private static final Logger LOG = Logger.getLogger(ApiFilter.class);

    @ConfigProperty(name = "my.api.key")
    private String apikey;

    @ServerRequestFilter(preMatching = true)
    public void filterApiKey(ContainerRequestContext ctx){

        String headerApiKey = ctx.getHeaderString("x-api-key");

        LOG.info("headerApiKey: " + headerApiKey);
        LOG.info("apikey: " + apikey);

        if ( headerApiKey == null || !headerApiKey.equals(apikey)){
            throw new UnauthorizedException();
//            throw new ForbiddenException();
        }
    }
}
