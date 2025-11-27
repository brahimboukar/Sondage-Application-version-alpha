package com.example.paneliste.dto;

import com.example.paneliste.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    private String refreshToken;
    private String expirationTime;
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Sexe getId_sexe() {
        return id_sexe;
    }

    public void setId_sexe(Sexe id_sexe) {
        this.id_sexe = id_sexe;
    }

    public Region getId_region() {
        return id_region;
    }

    public void setId_region(Region id_region) {
        this.id_region = id_region;
    }

    public Fonction getId_fonction() {
        return id_fonction;
    }

    public void setId_fonction(Fonction id_fonction) {
        this.id_fonction = id_fonction;
    }

    public FonctionDetailee getId_fonction_details() {
        return id_fonction_details;
    }

    public void setId_fonction_details(FonctionDetailee id_fonction_details) {
        this.id_fonction_details = id_fonction_details;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    private String prenom;
    private String email;
    private String telephone;
    private Sexe id_sexe;
    private Region id_region;
    private Fonction id_fonction;
    private FonctionDetailee id_fonction_details;
    private LocalDate dateNaissance;
    private String password;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role ;
    private User user;
    private List<User> userList;
}
