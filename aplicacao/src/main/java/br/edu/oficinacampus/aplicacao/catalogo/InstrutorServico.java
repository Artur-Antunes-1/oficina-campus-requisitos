package br.edu.oficinacampus.aplicacao.catalogo;

import br.edu.oficinacampus.dominio.catalogo.*;
import br.edu.oficinacampus.aplicacao.compartilhada.*;
import br.edu.oficinacampus.dominio.compartilhado.PoliticaCiclo;

public final class InstrutorServico extends ServicoCadastro<Instrutor> {
    public InstrutorServico(InstrutorRepositorio repositorio, Eventos eventos) {
        super(repositorio, eventos, "instrutores", new PoliticaCiclo.Padrao());
    }
    protected java.util.List<String> valoresPesquisa(Instrutor agregado) {
        return java.util.List.of(java.util.Objects.toString(agregado.nome(), ""), java.util.Objects.toString(agregado.apresentacao(), ""), java.util.Objects.toString(agregado.especialidade(), ""), java.util.Objects.toString(agregado.contato(), ""));
    }
}
