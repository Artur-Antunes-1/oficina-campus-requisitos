package br.edu.oficinacampus.apresentacao.catalogo;

import br.edu.oficinacampus.dominio.catalogo.ManifestacaoInteresse;
import br.edu.oficinacampus.aplicacao.catalogo.ManifestacaoInteresseServico;
import br.edu.oficinacampus.dominio.compartilhado.Controle;
import br.edu.oficinacampus.apresentacao.compartilhada.ControladorCadastro;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registros/interesses")
public class ManifestacaoInteresseControlador extends ControladorCadastro<ManifestacaoInteresse, ManifestacaoInteresseFormulario> {
    public ManifestacaoInteresseControlador(ManifestacaoInteresseServico servico) { super("interesses", servico); }
    protected ManifestacaoInteresse agregado(ManifestacaoInteresseFormulario dados, Controle controle) {
        return new ManifestacaoInteresse(controle, dados.nome(), dados.email(), dados.tema(), dados.modalidade());
    }
    protected ManifestacaoInteresseFormulario formulario(ManifestacaoInteresse agregado) { return new ManifestacaoInteresseFormulario(agregado.nome(), agregado.email(), agregado.tema(), agregado.modalidade()); }
}
