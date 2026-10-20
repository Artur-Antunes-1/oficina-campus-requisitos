package br.edu.oficinacampus.aplicacao.catalogo;

import br.edu.oficinacampus.dominio.catalogo.*;
import br.edu.oficinacampus.aplicacao.compartilhada.*;
import br.edu.oficinacampus.dominio.compartilhado.PoliticaCiclo;

public final class EspacoServico extends ServicoCadastro<Espaco> {
    public EspacoServico(EspacoRepositorio repositorio, Eventos eventos) {
        super(repositorio, eventos, "espacos", new PoliticaCiclo.Padrao());
    }
    protected java.util.List<String> valoresPesquisa(Espaco agregado) {
        return java.util.List.of(java.util.Objects.toString(agregado.nome(), ""), java.util.Objects.toString(agregado.localizacao(), ""), java.util.Objects.toString(agregado.descricao(), ""), java.util.Objects.toString(agregado.capacidade(), ""));
    }
}
