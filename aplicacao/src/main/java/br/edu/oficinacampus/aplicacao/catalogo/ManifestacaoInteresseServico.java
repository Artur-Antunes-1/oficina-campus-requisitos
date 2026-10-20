package br.edu.oficinacampus.aplicacao.catalogo;

import br.edu.oficinacampus.dominio.catalogo.*;
import br.edu.oficinacampus.aplicacao.compartilhada.*;
import br.edu.oficinacampus.dominio.compartilhado.PoliticaCiclo;

public final class ManifestacaoInteresseServico extends ServicoCadastro<ManifestacaoInteresse> {
    public ManifestacaoInteresseServico(ManifestacaoInteresseRepositorio repositorio, Eventos eventos) {
        super(repositorio, eventos, "interesses", new PoliticaCiclo.Padrao());
    }
    protected java.util.List<String> valoresPesquisa(ManifestacaoInteresse agregado) {
        return java.util.List.of(java.util.Objects.toString(agregado.nome(), ""), java.util.Objects.toString(agregado.email(), ""), java.util.Objects.toString(agregado.tema(), ""), java.util.Objects.toString(agregado.modalidade(), ""));
    }
}
