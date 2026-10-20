package br.edu.oficinacampus.apresentacao.metadados;

import java.util.List;
import java.util.Map;
/** Metadados exclusivos da apresentação e exemplos fictícios. */
public record Formulario(String id, String title, String singular, int owner, String group, String summary, List<CampoFormulario> fields, List<Map<String,Object>> seeds, String story, String activity, String policy) { }
