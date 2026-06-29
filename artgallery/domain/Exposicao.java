package artgallery.domain;

import java.util.Vector;

public class Exposicao {
    private String nome;
    private Vector<Obra> obras;

    public Exposicao(String nome) {
        this.nome = nome;
        this.obras = new Vector<>();
    }

    public void adicionarObra(Obra obra) {
        if (obra != null) {
            this.obras.add(obra);
        }
    }

    public Vector<Obra> listarObras() {
        return obras;
    }

    public String getNome() {
        return nome;
    }
}