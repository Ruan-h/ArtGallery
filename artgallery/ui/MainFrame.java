package artgallery.ui;

import artgallery.domain.Obra;
import artgallery.interfaces.IArtGallery;
import javax.swing.*;
import java.util.Vector;

public class MainFrame extends JFrame {
    private PainelListagem painelListagem;

    public MainFrame(IArtGallery sistema) {
        setTitle("ArtGallery - Gestor de Obras");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        
        painelListagem = new PainelListagem(sistema);

        abas.addTab("Listar Obras", painelListagem);
        abas.addTab("Cadastro", new PainelCadastro(sistema, this));
        abas.addTab("Consulta", new PainelConsulta(sistema, this));
        abas.addTab("Avaliações", new PainelAvaliacao(sistema, this));
        abas.addTab("Desativações", new PainelDesativacao(sistema, this));

        add(abas);
    }

    public void atualizarAbaListagem() {
        painelListagem.atualizarListagem();
    }
    
    public void exibirResultadosConsulta(Vector<Obra> obras) {
        painelListagem.atualizarListagem(obras);
        ((JTabbedPane) getContentPane().getComponent(0)).setSelectedIndex(0);
    }
}