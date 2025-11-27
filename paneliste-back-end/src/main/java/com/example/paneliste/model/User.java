package com.example.paneliste.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User  implements UserDetails {
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }




    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public void setFonctionDetailee(FonctionDetailee fonctionDetailee) {
        this.fonctionDetailee = fonctionDetailee;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getPoints() {
        return points;
    }

    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getRememberToken() {
        return rememberToken;
    }





    public Sexe getSexe() {
        return sexe;
    }

    public Region getRegion() {
        return region;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public FonctionDetailee getFonctionDetailee() {
        return fonctionDetailee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private int age;
    private String telephone;
    private int points;
    private boolean blocked;
    private String password;
    private String rememberToken;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public LocalDate  getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate  dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Column(name = "dateNaissance")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;

    @ManyToOne
    @JoinColumn(name = "id_sexe")
    private Sexe sexe;
    @ManyToOne @JoinColumn(name = "id_region")
    private Region region;
    @ManyToOne @JoinColumn(name = "id_fonction")
    private Fonction fonction;
    @ManyToOne @JoinColumn(name = "id_fonction_details")
    private FonctionDetailee fonctionDetailee;
}
