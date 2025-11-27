package com.example.paneliste.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "categorie_recomponses")
public class CategorieRecomponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}