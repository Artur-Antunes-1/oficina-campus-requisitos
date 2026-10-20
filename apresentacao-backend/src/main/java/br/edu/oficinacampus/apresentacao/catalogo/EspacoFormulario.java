package br.edu.oficinacampus.apresentacao.catalogo;

/** Contrato HTTP tipado; as invariantes são impostas pelo agregado. */
public record EspacoFormulario(String nome, String localizacao, String descricao, Integer capacidade) { }
