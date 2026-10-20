package br.edu.oficinacampus.infraestrutura.persistencia.catalogo;

import br.edu.oficinacampus.dominio.catalogo.*;
import br.edu.oficinacampus.infraestrutura.persistencia.RepositorioJpa;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class ManifestacaoInteresseRepositorioJpa extends RepositorioJpa<ManifestacaoInteresse, ManifestacaoInteresseJpa> implements ManifestacaoInteresseRepositorio {
    public ManifestacaoInteresseRepositorioJpa() { super(ManifestacaoInteresseJpa.class, ManifestacaoInteresseJpa::new); }
    protected ManifestacaoInteresse dominio(ManifestacaoInteresseJpa row) { return new ManifestacaoInteresse(row.controle(), row.nome, row.email, row.tema, row.modalidade); }
    protected void preencher(ManifestacaoInteresse agregado, ManifestacaoInteresseJpa row) {
        row.nome = agregado.nome();
        row.email = agregado.email();
        row.tema = agregado.tema();
        row.modalidade = agregado.modalidade();
    }
}
