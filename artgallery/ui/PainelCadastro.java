package artgallery.ui;

import artgallery.domain.*;
import artgallery.exceptions.ObraJaCadastradaException;
import artgallery.interfaces.IArtGallery;
import javax.swing.*;
import java.awt.*;

public class PainelCadastro extends JPanel {
    public PainelCadastro(IArtGallery sistema, MainFrame mainFrame) {
        setLayout(new GridLayout(9, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtTitulo = new JTextField();
        JTextField txtAutor = new JTextField();
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Pintura Digital", "Modelagem 3D", "Arte Generativa"});
        
        JTextField txtAtributo1 = new JTextField();
        JTextField txtAtributo2 = new JTextField();

        JLabel lblAtributo1 = new JLabel("Resolução:");
        JLabel lblAtributo2 = new JLabel("Software:");

        add(new JLabel("Título:"));
        add(txtTitulo);
        add(new JLabel("Autor:"));
        add(txtAutor);
        add(new JLabel("Tipo de Obra:"));
        add(comboTipo);
        add(lblAtributo1);
        add(txtAtributo1);
        add(lblAtributo2);
        add(txtAtributo2);

        comboTipo.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            if ("Pintura Digital".equals(tipo)) {
                lblAtributo1.setText("Resolução:");
                lblAtributo2.setText("Software:");
            } else if ("Modelagem 3D".equals(tipo)) {
                lblAtributo1.setText("Número de Polígonos:");
                lblAtributo2.setText("Engine:");
            } else if ("Arte Generativa".equals(tipo)) {
                lblAtributo1.setText("Algoritmo:");
                lblAtributo2.setText("Seed:");
            }
        });

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> {
            try {
                String titulo = txtTitulo.getText();
                String autor = txtAutor.getText();
                String tipo = (String) comboTipo.getSelectedItem();
                String attr1 = txtAtributo1.getText();
                String attr2 = txtAtributo2.getText();

                Obra novaObra = null;

                if ("Pintura Digital".equals(tipo)) {
                    novaObra = new PinturaDigital(titulo, autor, attr1, attr2);
                } else if ("Modelagem 3D".equals(tipo)) {
                    novaObra = new Modelagem3D(titulo, autor, Integer.parseInt(attr1), attr2);
                } else if ("Arte Generativa".equals(tipo)) {
                    novaObra = new ArteGenerativa(titulo, autor, attr1, Long.parseLong(attr2));
                }

                sistema.publicarObra(novaObra);
                JOptionPane.showMessageDialog(this, "Cadastro realizado");
                
                txtTitulo.setText("");
                txtAutor.setText("");
                txtAtributo1.setText("");
                txtAtributo2.setText("");
                
                mainFrame.atualizarAbaListagem();
            } catch (ObraJaCadastradaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO - Duplicidade", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ERRO - formatação dos atributos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ERRO -  erro inesperado ao realizar cadastro.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(new JLabel(""));
        add(btnCadastrar);
    }
}