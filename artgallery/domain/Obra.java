package artgallery.domain;

import java.io.Serializable;
import java.util.Vector;

public abstract class Obra implements Serializable {
    private String titulo;
    private String autor;
    private boolean ativa;
    private Vector<Avaliacao> avaliacoes;

    public Obra(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.avaliacoes = new Vector<>();
        this.ativa = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao != null) {
            this.avaliacoes.add(avaliacao);
        }
    }

    public double mediaAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }
        
        double soma = 0;
        for (Avaliacao aval : avaliacoes) {
            soma += aval.getNota();
        }
        return soma / avaliacoes.size();
    }

    public abstract String exibirDetalhes();
}