package br.edu.oficinacampus.infraestrutura.persistencia.catalogo;

import br.edu.oficinacampus.infraestrutura.persistencia.FichaJpa;
import jakarta.persistence.*;
import java.util.*;

@Entity(name="OficinaJpa") @Table(name="oficinas")
public class OficinaJpa extends FichaJpa {
 @Column(name="titulo",nullable=false,length=100)
 public String titulo;
 @Column(name="apresentacao",nullable=false,length=2000)
 public String apresentacao;
 @Column(name="modalidade",nullable=false,length=100)
 public String modalidade;
 @Column(name="duracao",nullable=false)
 public Integer duracao;

    public OficinaJpa() { }
}
