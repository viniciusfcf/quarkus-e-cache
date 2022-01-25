package org.acme;

import java.util.Arrays;
import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.quarkus.redis.client.RedisClient;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;


//1. Injetar o cache @Inject @Remote("myCache") RemoteCache<String, String> cache;
//2. Criar o cache no infinispan
//3. Utilizar o remoteCache
//4. Mostrar estatísticas
//5. Adicionar Listener (Create, modified, expired)
//6. Query e ContinuousQuery

@Path("/hello")
public class RedisResource {

    @Inject
    RedisClient redisClient;

    @Inject
    ReactiveRedisClient reactiveRedisClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("redis")
    public String hello(@QueryParam("key")String key) {
        //https://redis.io/commands
        int nextInt = new Random().nextInt();
        String value = "value" + nextInt;
        redisClient.set(Arrays.asList(key, value.toString()));
        redisClient.set(Arrays.asList(key + nextInt, value.toString()));
        return "Hello RESTEasy: key: "+key+" value: "+redisClient.get(key);
    }
}