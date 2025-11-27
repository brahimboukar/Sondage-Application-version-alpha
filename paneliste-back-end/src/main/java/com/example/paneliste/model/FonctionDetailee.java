package com.example.paneliste.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fonction_detailes")
public class FonctionDetailee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @ManyToOne @JoinColumn(name = "fonction_id")
    private Fonction fonction;
    private String libelle;
}