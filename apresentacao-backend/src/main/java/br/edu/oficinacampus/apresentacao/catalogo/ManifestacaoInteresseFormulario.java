package br.edu.oficinacampus.apresentacao.catalogo;

/** Contrato HTTP tipado; as invariantes são impostas pelo agregado. */
public record ManifestacaoInteresseFormulario(String nome, String email, String tema, String modalidade) { }
