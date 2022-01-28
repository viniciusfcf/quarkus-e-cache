package org.acme;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


//1. Criar uma entidade
//2. Fazer endpoint findAll
//3. Falar do @Cacheable, @Cache em um @OneToMany, e dos hints no Query e NamedQuery
//4. quarkus.hibernate-orm.second-level-caching-enabled=true, em dev ficou como false

@Path("/hibernate")
public class HibernateResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<PanacheEntityBase> hello() throws InterruptedException {
        Pessoa p = new Pessoa();
        p.nome = "Vinicius";
        p.idade = 11;
        p.persist();
        //Aqui nao pega do cache, é uma query
        return Pessoa.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Pessoa hello(@PathParam("id") Long id) throws InterruptedException {
        //Aqui pega do cache
        return Pessoa.findById(id);
    }


    
}