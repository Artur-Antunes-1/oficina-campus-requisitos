package br.edu.oficinacampus.dominio.catalogo;

import br.edu.oficinacampus.dominio.compartilhado.*;
import java.util.Objects;

/** Raiz do agregado espaço; valida seus próprios valores sem consultar outros cadastros. */
public record Espaco(Controle controle, String nome, String localizacao, String descricao, Integer capacidade) implements Cadastro<Espaco> {
    public Espaco {
        Objects.requireNonNull(controle);
        nome = Validacao.texto("Nome", nome, 1, 100, true);
        localizacao = Validacao.texto("Localização", localizacao, 1, 200, true);
        descricao = Validacao.texto("Descrição", descricao, 0, 2000, false);
        capacidade = Validacao.inteiro("Capacidade informativa", capacidade, 1, 1000, false);
    }
    public Espaco comControle(Controle novo) { return new Espaco(novo, nome, localizacao, descricao, capacidade); }
}
