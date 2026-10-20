package br.edu.oficinacampus.apresentacao.metadados;

import java.util.List;
/** Descrição visual; não valida nem substitui o modelo de domínio. */
public record CampoFormulario(String key, String label, String kind, int min, int max, boolean required, List<String> options, String hint) { }
