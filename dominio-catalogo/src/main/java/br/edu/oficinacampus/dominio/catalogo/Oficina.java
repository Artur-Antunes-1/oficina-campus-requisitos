package br.edu.oficinacampus.dominio.catalogo;

import br.edu.oficinacampus.dominio.compartilhado.*;
import java.util.Objects;

/** Raiz do agregado oficina; valida seus próprios valores sem consultar outros cadastros. */
public record Oficina(Controle controle, String titulo, String apresentacao, String modalidade, Integer duracao) implements Cadastro<Oficina> {
    public Oficina {
        Objects.requireNonNull(controle);
        titulo = Validacao.texto("Título", titulo, 1, 100, true);
        apresentacao = Validacao.texto("Apresentação", apresentacao, 1, 2000, true);
        modalidade = Validacao.opcao("Modalidade", modalidade, "Presencial", "Online", "Híbrida");
        duracao = Validacao.inteiro("Duração em minutos", duracao, 1, 480, true);
    }
    public Oficina comControle(Controle novo) { return new Oficina(novo, titulo, apresentacao, modalidade, duracao); }
}
