package br.edu.oficinacampus.apresentacao.metadados;

import br.edu.oficinacampus.aplicacao.compartilhada.Historico;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api")
public class MetadadosControlador {
    private final CatalogoFormularios catalogo;
    private final Historico historico;
    public MetadadosControlador(CatalogoFormularios catalogo, Historico historico) { this.catalogo = catalogo; this.historico = historico; }
    @GetMapping("/meta") public Object meta() { return Map.of("modulos", catalogo.todos(), "persistencia", "relacional", "versao", "2.0.0"); }
    @GetMapping("/historico") public Object historico() { return historico.listar(); }
}
