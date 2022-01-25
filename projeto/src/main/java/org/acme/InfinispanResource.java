package org.acme;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.infinispan.client.hotrod.RemoteCache;

import io.quarkus.infinispan.client.Remote;
import io.quarkus.runtime.StartupEvent;


//1. Injetar o cache @Inject @Remote("myCache") RemoteCache<String, String> cache;
//2. Criar o cache no infinispan
//3. Utilizar o remoteCache
//4. Mostrar estatísticas
//5. Adicionar Listener (Create, modified, expired)
//6. Query e ContinuousQuery

@Path("/hello")
public class InfinispanResource {

    @Inject @Remote("myCache")
    RemoteCache<String, String> cache;

    void onStart(@Observes StartupEvent ev) {   
        System.out.println("GreetingResource.onStart()");            
        cache.addClientListener(new InfinispanListener());
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("infinispan")
    public String hello(@QueryParam("key")String key) {
        //Query
        // QueryFactory queryFactory = Search.getQueryFactory(cache);
        // String query = "SELECT nome FROM com.redhat.Teste ";
        // List<String> resultado = queryFactory.<String>create(query).execute().list();
        
        //ContinuousQuery
        // ContinuousQueryListener<String, String> listener = null;
        // //Notificacoes de elementos que fazem match com a query foram, Criados, Atualizados ou Removidos
        // Search.getContinuousQuery(cache).addContinuousQueryListener("SELECT nome FROM com.redhat.Teste where idade > :idadeMinima", listener);;
        
        cache.put(key, "value", 10, TimeUnit.SECONDS);

        int nextInt = new Random().nextInt();
        cache.put(key + nextInt, "value" + nextInt);
        return "Hello RESTEasy: "+cache.get(key);
    }
}