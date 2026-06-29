package artgallery.repository;

import artgallery.domain.Obra;
import artgallery.exceptions.ObraJaCadastradaException;
import artgallery.exceptions.ObraNaoEncontradaException;
import artgallery.interfaces.IRepositorioObra;
import java.util.Vector;

public class RepositorioObraMemoria implements IRepositorioObra {
    private Vector<Obra> obras;

    public RepositorioObraMemoria() {
        this.obras = new Vector<>();
    }

    @Override
    public void cadastrar(Obra obra) throws ObraJaCadastradaException {
        for (Obra o : obras) {
            if (o.getTitulo().equals(obra.getTitulo()) && o.getAutor().equals(obra.getAutor())) {
                throw new ObraJaCadastradaException("Já existe uma obra cadastrada com este título e autor.");
            }
        }
        obras.add(obra);
    }

    @Override
    public Obra buscar(String titulo) {
        for (Obra o : obras) {
            if (o.getTitulo().equals(titulo)) {
                return o;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Obra obra) throws ObraNaoEncontradaException {
        Obra existente = buscar(obra.getTitulo());
        if (existente == null) {
            throw new ObraNaoEncontradaException("Obra não encontrada para atualização.");
        }
        
        int index = obras.indexOf(existente);
        obras.set(index, obra);
    }

    @Override
    public void remover(String titulo) {
        Obra existente = buscar(titulo);
        if (existente != null) {
            existente.setAtiva(false);
        }
    }

    @Override
    public Vector<Obra> listar() {
        return obras;
    }
}