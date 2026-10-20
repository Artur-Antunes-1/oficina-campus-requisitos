package br.edu.oficinacampus.aplicacao.catalogo;

import br.edu.oficinacampus.dominio.catalogo.*;
import br.edu.oficinacampus.aplicacao.compartilhada.*;
import br.edu.oficinacampus.dominio.compartilhado.PoliticaCiclo;

public final class OficinaServico extends ServicoCadastro<Oficina> {
    public OficinaServico(OficinaRepositorio repositorio, Eventos eventos) {
        super(repositorio, eventos, "oficinas", new PoliticaCiclo.Padrao());
    }
    protected java.util.List<String> valoresPesquisa(Oficina agregado) {
        return java.util.List.of(java.util.Objects.toString(agregado.titulo(), ""), java.util.Objects.toString(agregado.apresentacao(), ""), java.util.Objects.toString(agregado.modalidade(), ""), java.util.Objects.toString(agregado.duracao(), ""));
    }
}
