package artgallery;

import artgallery.domain.Avaliacao;
import artgallery.domain.Exposicao;
import artgallery.domain.Obra;
import artgallery.exceptions.ObraJaCadastradaException;
import artgallery.interfaces.IArtGallery;
import artgallery.interfaces.IRepositorioObra;
import java.util.Vector;

public class ArtGallery implements IArtGallery {
    private IRepositorioObra repositorio;
    private Vector<Exposicao> exposicoes;

    public ArtGallery(IRepositorioObra repositorio) {
        this.repositorio = repositorio;
        this.exposicoes = new Vector<>();
    }

    public void adicionarExposicao(Exposicao exposicao) {
        if (exposicao != null) {
            this.exposicoes.add(exposicao);
        }
    }

    @Override
    public void publicarObra(Obra obra) throws ObraJaCadastradaException {
        repositorio.cadastrar(obra);
    }

    @Override
    public void removerObra(String titulo) {
        boolean existeAtiva = false;
        for (Obra o : repositorio.listar()) {
            if (o.getTitulo().equalsIgnoreCase(titulo) && o.isAtiva()) {
                existeAtiva = true;
                break;
            }
        }

        if (existeAtiva) {
            repositorio.remover(titulo);
        } else {
            throw new IllegalArgumentException("A obra não existe.");
        }
    }

    @Override
    public void avaliarObra(String titulo, Avaliacao avaliacao) {
        Obra obraAtiva = null;
        for (Obra o : repositorio.listar()) {
            if (o.getTitulo().equalsIgnoreCase(titulo) && o.isAtiva()) {
                obraAtiva = o;
                break;
            }
        }

        if (obraAtiva != null) {
            obraAtiva.adicionarAvaliacao(avaliacao);
            try {
                repositorio.atualizar(obraAtiva);
            } catch (Exception e) {
                throw new IllegalArgumentException("ERRO ao salvar avaliação no arquivo.");
            }
        } else {
            throw new IllegalArgumentException("A obra não existe.");
        }
    }

    @Override
    public Vector<Obra> listarobras() {
        Vector<Obra> todasAsObras = repositorio.listar();
        Vector<Obra> obrasAtivas = new Vector<>();
        for (Obra obra : todasAsObras) {
            if (obra.isAtiva()) {
                obrasAtivas.add(obra);
            }
        }
        return obrasAtivas;
    }

    @Override
    public Vector<Obra> buscarPorAutor(String autor) {
        Vector<Obra> todasAsObras = repositorio.listar();
        Vector<Obra> obrasDoAutor = new Vector<>();
        for (Obra obra : todasAsObras) {
            if (obra.getAutor().equalsIgnoreCase(autor)) {
                obrasDoAutor.add(obra);
            }
        }
        return obrasDoAutor;
    }

    @Override
    public Vector<Obra> ranking() {
        Vector<Obra> obrasOrdenadas = listarobras();
        obrasOrdenadas.sort((o1, o2) -> Double.compare(o2.mediaAvaliacoes(), o1.mediaAvaliacoes()));
        return obrasOrdenadas;
    }

    @Override
    public Vector<Obra> obrasExpostas(String nomeExposicao) {
        for (Exposicao exposicao : exposicoes) {
            if (exposicao.getNome().equalsIgnoreCase(nomeExposicao)) {
                return exposicao.listarObras();
            }
        }
        return new Vector<>();
    }
}