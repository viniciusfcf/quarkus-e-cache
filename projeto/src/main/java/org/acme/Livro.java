package org.acme;

import java.util.Objects;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class Livro {

    private final String titulo;
    private final String descricao;
    private final Integer anoPublicacao;
 
    @ProtoFactory
    public Livro(String titulo, String descricao, Integer anoPublicacao) {
        this.titulo = Objects.requireNonNull(titulo);
        this.descricao = Objects.requireNonNull(descricao);
        this.anoPublicacao = Objects.requireNonNull(anoPublicacao);
    }

    @ProtoField(number = 1)
    public String getTitulo() {
        return titulo;
    }

    @ProtoField(number = 2)
    public String getDescricao() {
        return descricao;
    }

    @ProtoField(number = 3)
    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + anoPublicacao;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Livro other = (Livro) obj;
        if (anoPublicacao != other.anoPublicacao)
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Livro [anoPublicacao=" + anoPublicacao + ", descricao=" + descricao + ", titulo=" + titulo + "]";
    }


 }