package com.example.paneliste.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recomponses")
public class Recomponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private int points;
    private boolean status;
    private String img;
    @ManyToOne @JoinColumn(name = "id_categorie")
    private CategorieRecomponse categorie;
}
