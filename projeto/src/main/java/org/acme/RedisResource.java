package org.acme;

import java.util.Arrays;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.quarkus.redis.client.RedisClient;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;


//1. Injetar @Inject RedisClient e ReactiveRedisClient (apresentei somente o blocante)
//2. Setar um int, fazer increment, transformar em String, tentar fazer incremento e tomar erro
//3. Falar de expire
//4. Falar de Hash
//5. Falar do pub sub, mais como um message broker
//     reactiveRedisClient.subscribe(null).subscribe().with
//6. https://redis.io/commands

@Path("/redis")
public class RedisResource {

    @Inject
    RedisClient redisClient;

    @Inject
    ReactiveRedisClient reactiveRedisClient;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String set(@QueryParam("key")String key, @QueryParam("value") String value) {
        redisClient.set(Arrays.asList(key, value));
        return "Hello RESTEasy: " + redisClient.get(key) ;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("incr")
    public String incr(@QueryParam("key")String key) {
        redisClient.incr(key);
        return "Hello RESTEasy: key: "+key+" value: "+redisClient.get(key);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("lpush")
    public String lpush(@QueryParam("key")String key, @QueryParam("value") String value) {
        redisClient.lpush(Arrays.asList(key, value));
        //retorna toda a lista, se fizer get, toma erro!
        return "Hello RESTEasy: key: "+key+" value: "+redisClient.lrange(key, "0", "-1");
    }


}