package artgallery;

import artgallery.domain.*;
import artgallery.exceptions.NotaInvalidaException;
import artgallery.exceptions.ObraJaCadastradaException;
import artgallery.repository.RepositorioObraArquivo;
import artgallery.ui.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        RepositorioObraArquivo repositorio = new RepositorioObraArquivo();
        ArtGallery sistema = new ArtGallery(repositorio);

        if (sistema.listarobras().isEmpty()) {
            Obra obj1 = new PinturaDigital("Aurora Neon", "Alice", "4K", "Photoshop");
            Obra obj2 = new Modelagem3D("Cyber Dragon", "Bruno", 50000, "Blender");
            Obra obj3 = new ArteGenerativa("Chaos Waves", "Carla", "Perlin Noise", 98231);

            try {
                sistema.publicarObra(obj1);
                sistema.publicarObra(obj2);
                sistema.publicarObra(obj3);
            } catch (ObraJaCadastradaException e) {
                System.err.println("ERRO - duplicidade da entrada iniciai: " + e.getMessage());
            }

            try {
                sistema.avaliarObra("Aurora Neon", new Avaliacao("João", 9, "Muito bom!"));
                sistema.avaliarObra("Aurora Neon", new Avaliacao("Maria", 10, "Obra prima!"));
            } catch (NotaInvalidaException e) {
                System.err.println("ERRO - na avaliação da entrada inicial: " + e.getMessage());
            }
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame tela = new MainFrame(sistema);
            tela.atualizarAbaListagem();
            tela.setVisible(true);
        });
    }
}