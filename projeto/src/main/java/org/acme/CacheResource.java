package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheResult;


//1. Mostrar anotações: @CacheResult, @CacheInvalidate, @CacheInvalidateAll e @CacheKey
//2. Exemplos de propriedades no application.properties:
//quarkus.cache.caffeine."meuCache".initial-capacity=10 
// quarkus.cache.caffeine."meuCache".maximum-size=20
// quarkus.cache.caffeine."meuCache".expire-after-write=60S
// quarkus.cache.caffeine."meuCache".maximum-size=1000 

@Path("/cache")
public class CacheResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @CacheResult(cacheName = "meuCache")
    public String hello(@QueryParam("key")String key) throws InterruptedException {
        Thread.sleep(5000);
        return "Hello RESTEasy";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @CacheInvalidate(cacheName = "meuCache")
    //@CacheInvalidateAll
    @Path("invalidate")
    public String invalidate(@QueryParam("key")String key) throws InterruptedException {
        return "Hello RESTEasy";
    }

    
}