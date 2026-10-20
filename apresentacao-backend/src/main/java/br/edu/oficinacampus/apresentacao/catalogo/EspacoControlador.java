package br.edu.oficinacampus.apresentacao.catalogo;

import br.edu.oficinacampus.dominio.catalogo.Espaco;
import br.edu.oficinacampus.aplicacao.catalogo.EspacoServico;
import br.edu.oficinacampus.dominio.compartilhado.Controle;
import br.edu.oficinacampus.apresentacao.compartilhada.ControladorCadastro;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registros/espacos")
public class EspacoControlador extends ControladorCadastro<Espaco, EspacoFormulario> {
    public EspacoControlador(EspacoServico servico) { super("espacos", servico); }
    protected Espaco agregado(EspacoFormulario dados, Controle controle) {
        return new Espaco(controle, dados.nome(), dados.localizacao(), dados.descricao(), dados.capacidade());
    }
    protected EspacoFormulario formulario(Espaco agregado) { return new EspacoFormulario(agregado.nome(), agregado.localizacao(), agregado.descricao(), agregado.capacidade()); }
}
