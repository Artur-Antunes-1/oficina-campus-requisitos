package br.edu.oficinacampus.apresentacao.catalogo;

/** Contrato HTTP tipado; as invariantes são impostas pelo agregado. */
public record OficinaFormulario(String titulo, String apresentacao, String modalidade, Integer duracao) { }
