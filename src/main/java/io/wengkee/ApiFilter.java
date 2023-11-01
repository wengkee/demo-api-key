package io.wengkee;

import io.quarkus.logging.Log;
import io.quarkus.security.ForbiddenException;
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
        String decodedKey = decodeBase64(headerApiKey);

        LOG.info("headerApiKey: " + headerApiKey);
        Log.info("decodedKey: " + decodedKey);
        LOG.info("apikey: " + apikey);

        if ( headerApiKey == null || !headerApiKey.equals(apikey)){
            throw new UnauthorizedException(); // error 401
//            throw new ForbiddenException(); // error 403
        }
    }

    private String decodeBase64(String s){
        if(s != null){
            return new String(Base64.getDecoder().decode(s));
        }
        return null;
    }
}
