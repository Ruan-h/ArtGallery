package artgallery.ui;

import artgallery.domain.Obra;
import artgallery.interfaces.IArtGallery;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class PainelListagem extends JPanel {
    private IArtGallery sistema;
    private JTextArea areaListagem;

    public PainelListagem(IArtGallery sistema) {
        this.sistema = sistema;
        setLayout(new BorderLayout());
        
        areaListagem = new JTextArea();
        areaListagem.setEditable(false);
        areaListagem.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scroll = new JScrollPane(areaListagem);
        add(scroll, BorderLayout.CENTER);

        JButton btnAtualizar = new JButton("Atualizar Lista");
        btnAtualizar.addActionListener(e -> atualizarListagem());
        add(btnAtualizar, BorderLayout.SOUTH);
    }

    public void atualizarListagem() {
        atualizarListagem(sistema.listarobras());
    }

    public void atualizarListagem(Vector<Obra> obras) {
        areaListagem.setText("");
        if (obras.isEmpty()) {
            areaListagem.append("Nenhuma obra encontrada\n");
            return;
        }
        for (Obra obra : obras) {
            areaListagem.append(obra.exibirDetalhes() + "\n");
            areaListagem.append("Média de Avaliações: " + String.format("%.1f", obra.mediaAvaliacoes()) + "\n");
            areaListagem.append("\n");
        }
    }
}