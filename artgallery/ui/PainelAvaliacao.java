package artgallery.ui;

import artgallery.domain.Avaliacao;
import artgallery.exceptions.NotaInvalidaException;
import artgallery.interfaces.IArtGallery;
import javax.swing.*;
import java.awt.*;

public class PainelAvaliacao extends JPanel {
    public PainelAvaliacao(IArtGallery sistema, MainFrame mainFrame) {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtTitulo = new JTextField();
        JTextField txtUsuario = new JTextField();
        JTextField txtNota = new JTextField();
        JTextField txtComentario = new JTextField();

        add(new JLabel("Título da Obra:"));
        add(txtTitulo);
        add(new JLabel("Nome do Usuário:"));
        add(txtUsuario);
        add(new JLabel("Nota (0 a 10):"));
        add(txtNota);
        add(new JLabel("Comentário:"));
        add(txtComentario);

        JButton btnAvaliar = new JButton("Enviar Avaliação");
        btnAvaliar.addActionListener(e -> {
            try {
                String titulo = txtTitulo.getText();
                String usuario = txtUsuario.getText();
                int nota = Integer.parseInt(txtNota.getText());
                String comentario = txtComentario.getText();

                Avaliacao avaliacao = new Avaliacao(usuario, nota, comentario);
                sistema.avaliarObra(titulo, avaliacao);
                JOptionPane.showMessageDialog(this, "Avaliação registrada e salva!");
                
                txtTitulo.setText("");
                txtUsuario.setText("");
                txtNota.setText("");
                txtComentario.setText("");
                
                mainFrame.atualizarAbaListagem();
            } catch (NotaInvalidaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "insira um número inteiro para a nota.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        add(new JLabel(""));
        add(btnAvaliar);
    }
}