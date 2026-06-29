package artgallery.ui;

import artgallery.domain.Obra;
import artgallery.interfaces.IArtGallery;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class PainelConsulta extends JPanel {
    public PainelConsulta(IArtGallery sistema, MainFrame mainFrame) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JTextField txtAutor = new JTextField(15);
        JButton btnBuscarAutor = new JButton("Buscar por Autor");
        
        btnBuscarAutor.addActionListener(e -> {
            Vector<Obra> resultados = sistema.buscarPorAutor(txtAutor.getText());
            mainFrame.exibirResultadosConsulta(resultados);
        });

        JButton btnTopObras = new JButton("Listar Ranking de Obras");
        btnTopObras.addActionListener(e -> {
            Vector<Obra> resultados = sistema.ranking();
            mainFrame.exibirResultadosConsulta(resultados);
        });

        add(new JLabel("Nome do Autor:"));
        add(txtAutor);
        add(btnBuscarAutor);
        add(btnTopObras);
    }
}