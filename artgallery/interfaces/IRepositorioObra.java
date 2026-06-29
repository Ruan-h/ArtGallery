package artgallery.interfaces;

import artgallery.domain.Obra;
import artgallery.exceptions.ObraJaCadastradaException;
import artgallery.exceptions.ObraNaoEncontradaException;
import java.util.Vector;

public interface IRepositorioObra {
    void cadastrar(Obra obra) throws ObraJaCadastradaException;
    Obra buscar(String titulo);
    void atualizar(Obra obra) throws ObraNaoEncontradaException;
    void remover(String titulo);
    Vector<Obra> listar();
}