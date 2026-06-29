package artgallery.interfaces;

import artgallery.domain.Avaliacao;
import artgallery.domain.Obra;
import artgallery.exceptions.ObraJaCadastradaException;
import java.util.Vector;

public interface IArtGallery {
    void publicarObra(Obra obra) throws ObraJaCadastradaException;
    void removerObra(String titulo);
    void avaliarObra(String titulo, Avaliacao avaliacao);
    Vector<Obra> listarobras();
    Vector<Obra> buscarPorAutor(String autor);
    Vector<Obra> ranking();
    Vector<Obra> obrasExpostas(String nomeExposicao);
}