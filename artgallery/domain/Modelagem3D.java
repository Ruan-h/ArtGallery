package artgallery.domain;

public class Modelagem3D extends Obra {
    private int numeroPoligonos;
    private String engine;

    public Modelagem3D(String titulo, String autor, int numeroPoligonos, String engine) {
        super(titulo, autor);
        this.numeroPoligonos = numeroPoligonos;
        this.engine = engine;
    }

    @Override
    public String exibirDetalhes() {
        return "Título: " + getTitulo() + "\n" +
               "Autor: " + getAutor() + "\n" +
               "Tipo: Modelagem 3D\n" +
               "Polígonos: " + numeroPoligonos + "\n" +
               "Engine: " + engine;
    }
}