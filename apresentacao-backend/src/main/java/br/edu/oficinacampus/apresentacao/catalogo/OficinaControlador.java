package br.edu.oficinacampus.apresentacao.catalogo;

import br.edu.oficinacampus.dominio.catalogo.Oficina;
import br.edu.oficinacampus.aplicacao.catalogo.OficinaServico;
import br.edu.oficinacampus.dominio.compartilhado.Controle;
import br.edu.oficinacampus.apresentacao.compartilhada.ControladorCadastro;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registros/oficinas")
public class OficinaControlador extends ControladorCadastro<Oficina, OficinaFormulario> {
    public OficinaControlador(OficinaServico servico) { super("oficinas", servico); }
    protected Oficina agregado(OficinaFormulario dados, Controle controle) {
        return new Oficina(controle, dados.titulo(), dados.apresentacao(), dados.modalidade(), dados.duracao());
    }
    protected OficinaFormulario formulario(Oficina agregado) { return new OficinaFormulario(agregado.titulo(), agregado.apresentacao(), agregado.modalidade(), agregado.duracao()); }
}
