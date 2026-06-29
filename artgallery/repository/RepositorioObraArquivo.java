package artgallery.repository;

import artgallery.domain.Obra;
import artgallery.exceptions.ObraJaCadastradaException;
import artgallery.exceptions.ObraNaoEncontradaException;
import artgallery.interfaces.IRepositorioObra;

import java.io.*;
import java.util.Vector;

public class RepositorioObraArquivo implements IRepositorioObra {
    private Vector<Obra> obras;
    private final String ARQUIVO = "obras.dat";

    public RepositorioObraArquivo() {
        this.obras = new Vector<>();
        carregarArquivo();
    }

    private void carregarArquivo() {
        File file = new File(ARQUIVO);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                obras = (Vector<Obra>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("ERRO - leitura do arquivo obras.dat");
                obras = new Vector<>();
            }
        }
    }

    private void salvarArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(obras);
        } catch (IOException e) {
            System.err.println("ERRO - gravação do arquivo obras.dat");
        }
    }

    @Override
    public void cadastrar(Obra obra) throws ObraJaCadastradaException {
        for (Obra o : obras) {
            if (o.getTitulo().equals(obra.getTitulo()) && o.getAutor().equals(obra.getAutor())) {
                throw new ObraJaCadastradaException("Já existe uma obra cadastrada com este título e autor.");
            }
        }
        obras.add(obra);
        salvarArquivo();
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
            throw new ObraNaoEncontradaException("Obra não encontrada.");
        }
        
        int index = obras.indexOf(existente);
        obras.set(index, obra);
        salvarArquivo();
    }

    @Override
    public void remover(String titulo) {
        for (Obra o : obras) {
            if (o.getTitulo().equalsIgnoreCase(titulo) && o.isAtiva()) {
                o.setAtiva(false);
                break;
            }
        }
        salvarArquivo();
    }

    @Override
    public Vector<Obra> listar() {
        return obras;
    }
}