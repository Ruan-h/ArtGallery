package artgallery.domain;

public class ArteGenerativa extends Obra {
    private String algoritmo;
    private long seed;

    public ArteGenerativa(String titulo, String autor, String algoritmo, long seed) {
        super(titulo, autor);
        this.algoritmo = algoritmo;
        this.seed = seed;
    }

    @Override
    public String exibirDetalhes() {
        return "Título: " + getTitulo() + "\n" +
               "Autor: " + getAutor() + "\n" +
               "Tipo: Arte Generativa\n" +
               "Algoritmo: " + algoritmo + "\n" +
               "Seed: " + seed;
    }
}