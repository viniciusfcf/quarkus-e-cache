package org.acme;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.QueryHint;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
//Exemplo de namedQuery com cache
// @NamedQuery(name = "Pessoa.findDeMaiores",
//       query = "SELECT p FROM Pessoa p where p.idade >= 18",
//       hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class Pessoa extends PanacheEntity {
    
    public String nome;

    public Integer idade;

    // Exemplo de mapeamento com cache, por padrao nao tem cache de relacionamentos
    // @OneToMany
    // @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    // public List<Endereco> enderecos;

    // Exemplo de query programatica com cache
    // public static void queryComCache(EntityManager em) {
    //     Query query = em.createQuery("select p from Pessoa p");
    //     query.setHint("org.hibernate.cacheable", Boolean.TRUE);
    // }

}
