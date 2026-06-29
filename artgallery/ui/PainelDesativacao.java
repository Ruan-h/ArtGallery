package artgallery.ui;

import artgallery.interfaces.IArtGallery;
import javax.swing.*;
import java.awt.*;

public class PainelDesativacao extends JPanel {
    public PainelDesativacao(IArtGallery sistema, MainFrame mainFrame) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JTextField txtTitulo = new JTextField(15);
        JButton btnRemover = new JButton("Desativar");

        btnRemover.addActionListener(e -> {
            try {
                String titulo = txtTitulo.getText();
                sistema.removerObra(titulo);
                JOptionPane.showMessageDialog(this, "Obra desativada com sucesso.");
                txtTitulo.setText("");
                mainFrame.atualizarAbaListagem();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        add(new JLabel("Título da Obra a Desativar:"));
        add(txtTitulo);
        add(btnRemover);
    }
}