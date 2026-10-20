package br.edu.oficinacampus.infraestrutura.persistencia.catalogo;

import br.edu.oficinacampus.infraestrutura.persistencia.FichaJpa;
import jakarta.persistence.*;
import java.util.*;

@Entity(name="ManifestacaoInteresseJpa") @Table(name="interesses")
public class ManifestacaoInteresseJpa extends FichaJpa {
 @Column(name="nome",nullable=false,length=100)
 public String nome;
 @Column(name="email",nullable=false,length=254)
 public String email;
 @Column(name="tema",nullable=false,length=150)
 public String tema;
 @Column(name="modalidade",nullable=false,length=100)
 public String modalidade;

    public ManifestacaoInteresseJpa() { }
}
