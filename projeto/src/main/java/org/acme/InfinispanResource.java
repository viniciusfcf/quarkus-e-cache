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
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.configuration.XMLStringConfiguration;

import io.quarkus.runtime.StartupEvent;


//1. Injetar o cache @Inject @Remote("myCache") RemoteCache<String, String> cache;
//2. Criar o cache na console web do infinispan
//3. Utilizar o remoteCache: fazer um put, get
//4. Mostrar estatísticas na console web
//5. Adicionar Listener (Create, modified, expired)
//6. Falar sobre o Protobuf
//7. Falar sobre Query e ContinuousQuery

@Path("/infinispan")
public class InfinispanResource {

    // Posso injetar, mas como estou criando o cache no onStart, fica null
    // @Inject @Remote("myCache")
    // RemoteCache<String, String> cache;

    @Inject
    RemoteCacheManager cacheManager;

    private static final String CACHE_CONFIG = "<distributed-cache name=\"%s\">"
          + " <encoding media-type=\"application/x-protostream\"/>"
          + "</distributed-cache>";

    void onStart(@Observes StartupEvent ev) {   
        RemoteCache<Object, Object> cache = cacheManager.administration().getOrCreateCache("myCache",
                new XMLStringConfiguration(String.format(CACHE_CONFIG, "myCache")));    
        cache.addClientListener(new InfinispanListener());
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("key")String key) {
        //Query
        // QueryFactory queryFactory = Search.getQueryFactory(cache);
        // String query = "SELECT nome FROM com.redhat.Teste ";
        // List<String> resultado = queryFactory.<String>create(query).execute().list();
        
        //ContinuousQuery
        // ContinuousQueryListener<String, String> listener = null;
        // //Notificacoes de elementos que fazem match com a query foram, Criados, Atualizados ou Removidos
        // Search.getContinuousQuery(cache).addContinuousQueryListener("SELECT nome FROM com.redhat.Teste where idade > :idadeMinima", listener);;
        
        //Exemplo de put com expiracao
        RemoteCache<String, String> cache = cacheManager.getCache("myCache");
        cache.put(key, "value", 10, TimeUnit.SECONDS);

        int nextInt = new Random().nextInt();
        //Exemplo de put simples
        cache.put(key + nextInt, "value" + nextInt);

        //Exemplo de get simples
        return "Hello RESTEasy: "+cache.get(key);
    }
}