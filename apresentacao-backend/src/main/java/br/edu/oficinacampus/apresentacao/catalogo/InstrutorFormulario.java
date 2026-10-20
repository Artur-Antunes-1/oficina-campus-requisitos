package br.edu.oficinacampus.apresentacao.catalogo;

/** Contrato HTTP tipado; as invariantes são impostas pelo agregado. */
public record InstrutorFormulario(String nome, String apresentacao, String especialidade, String contato) { }
