package com.example.paneliste.controller;

import com.example.paneliste.dto.ReqRes;
import com.example.paneliste.model.Fonction;
import com.example.paneliste.model.FonctionDetailee;
import com.example.paneliste.model.Region;
import com.example.paneliste.model.Sexe;
import com.example.paneliste.repository.FonctionDetaillsRepository;
import com.example.paneliste.repository.FonctionRepository;
import com.example.paneliste.repository.RegionRepository;
import com.example.paneliste.repository.SexeRepository;
import com.example.paneliste.service.AdminService;
import com.example.paneliste.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Auth {
    @Autowired
    AuthService authService;

    @Autowired
    SexeRepository sexeRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    FonctionRepository fonctionRepository;

    @Autowired
    FonctionDetaillsRepository fonctionDetaillsRepository;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg) {
        return ResponseEntity.ok(authService.register(reg));
    }


    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req) {
        return ResponseEntity.ok(authService.refreshToken(req));
    }

    //Get All Sexe

    @GetMapping("/api/get-all-sexe")
    public List<Sexe> getAllSexe() {
        return sexeRepository.findAll();
    }

    @GetMapping("/api/get-all-region")
    public List<Region> getAllRegion() {
        return regionRepository.findAll();
    }
    @GetMapping("/api/get-all-fonction")
    public List<Fonction> getAllFonction() {
        return fonctionRepository.findAll();
    }

    @GetMapping("/api/get-fonction-detaills/{fonction_id}")
    public List<FonctionDetailee> getFonctionDetaills(@PathVariable Long fonction_id){
        return fonctionDetaillsRepository.findByFonctionId(fonction_id);
    }
}
