package br.edu.oficinacampus.aplicacao.compartilhada;

import br.edu.oficinacampus.dominio.compartilhado.*;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/** Coordenação comum de casos de uso sobre agregados tipados. */
public abstract class ServicoCadastro<T extends Cadastro<T>> {
    private final Repositorio<T> repositorio;
    private final PoliticaCiclo ciclo;
    protected ServicoCadastro(Repositorio<T> repositorio, Eventos eventos, String funcionalidade, PoliticaCiclo ciclo) {
        this.repositorio = new RepositorioObservavel<>(repositorio, eventos, funcionalidade);
        this.ciclo = ciclo;
    }
    public T criar(Function<Controle,T> fabrica) { return repositorio.salvar(fabrica.apply(Controle.novo(ciclo.inicial())), true); }
    public T consultar(String id) { return repositorio.buscar(id).orElseThrow(ErroDominio::ausente); }
    public List<T> listar() { return repositorio.listar(); }
    public T editar(String id, Long versao, Function<Controle,T> fabrica) {
        return new MutacaoCadastro<T>(repositorio) {
            protected T transformar(T atual) {
                if (!ciclo.podeEditar(atual.controle().estado())) throw ErroDominio.conflito("Restaure a ficha antes de editar.");
                return fabrica.apply(atual.controle().atualizar(atual.controle().estado()));
            }
        }.executar(id, versao);
    }
    public T transitar(String id, Long versao, String acao) {
        return new MutacaoCadastro<T>(repositorio) {
            protected T transformar(T atual) {
                return atual.comControle(atual.controle().atualizar(ciclo.transitar(atual.controle().estado(), acao)));
            }
        }.executar(id, versao);
    }
    public Pagina<T> pesquisar(String q, String estado, int offset, int limite) {
        if (offset < 0 || limite < 1 || limite > 100) throw ErroDominio.validacao("Paginação inválida.");
        if (!"TODOS".equals(estado)) {
            try { Estado.valueOf(estado); } catch (IllegalArgumentException e) { throw ErroDominio.validacao("Estado inválido."); }
        }
        String consulta = q.trim().toLowerCase(Locale.ROOT);
        List<T> filtrados = listar().stream()
            .filter(t -> "TODOS".equals(estado) || t.controle().estado().name().equals(estado))
            .filter(t -> valoresPesquisa(t).stream().anyMatch(v -> v.toLowerCase(Locale.ROOT).contains(consulta))).toList();
        return new Pagina<>(filtrados.stream().skip(offset).limit(limite).toList(), filtrados.size(), offset, limite);
    }
    protected abstract List<String> valoresPesquisa(T agregado);
}
