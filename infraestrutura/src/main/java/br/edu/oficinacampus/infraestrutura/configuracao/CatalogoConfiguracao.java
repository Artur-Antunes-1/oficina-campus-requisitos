package br.edu.oficinacampus.infraestrutura.configuracao;

import br.edu.oficinacampus.dominio.catalogo.*;
import br.edu.oficinacampus.aplicacao.catalogo.*;
import br.edu.oficinacampus.aplicacao.compartilhada.Eventos;
import org.springframework.context.annotation.*;

@Configuration
public class CatalogoConfiguracao {
    @Bean public OficinaServico oficinasServico(OficinaRepositorio repositorio, Eventos eventos) { return new OficinaServico(repositorio, eventos); }
    @Bean public ManifestacaoInteresseServico interessesServico(ManifestacaoInteresseRepositorio repositorio, Eventos eventos) { return new ManifestacaoInteresseServico(repositorio, eventos); }
    @Bean public InstrutorServico instrutoresServico(InstrutorRepositorio repositorio, Eventos eventos) { return new InstrutorServico(repositorio, eventos); }
    @Bean public EspacoServico espacosServico(EspacoRepositorio repositorio, Eventos eventos) { return new EspacoServico(repositorio, eventos); }
}
