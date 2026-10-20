package br.edu.oficinacampus.dominio.catalogo;

import br.edu.oficinacampus.dominio.compartilhado.*;
import java.util.Objects;

/** Raiz do agregado manifestação de interesse; valida seus próprios valores sem consultar outros cadastros. */
public record ManifestacaoInteresse(Controle controle, String nome, String email, String tema, String modalidade) implements Cadastro<ManifestacaoInteresse> {
    public ManifestacaoInteresse {
        Objects.requireNonNull(controle);
        nome = Validacao.texto("Nome declarado", nome, 1, 100, true);
        email = Validacao.email("E-mail", email);
        tema = Validacao.texto("Tema desejado", tema, 1, 150, true);
        modalidade = Validacao.opcao("Preferência de modalidade", modalidade, "Presencial", "Online", "Híbrida", "Sem preferência");
    }
    public ManifestacaoInteresse comControle(Controle novo) { return new ManifestacaoInteresse(novo, nome, email, tema, modalidade); }
}
