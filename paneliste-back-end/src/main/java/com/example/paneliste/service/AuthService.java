package com.example.paneliste.service;

import com.example.paneliste.dto.ReqRes;
import com.example.paneliste.model.User;
import com.example.paneliste.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Locale;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean emailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public ReqRes register(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();

        try {
            User user = new User();
            user.setNom(registrationRequest.getNom());
            user.setPrenom(registrationRequest.getPrenom());
            user.setEmail(registrationRequest.getEmail());
            user.setTelephone(registrationRequest.getTelephone());
            user.setSexe(registrationRequest.getId_sexe());
            user.setRegion(registrationRequest.getId_region());
            user.setFonction(registrationRequest.getId_fonction());
            user.setFonctionDetailee(registrationRequest.getId_fonction_details());
            user.setDateNaissance(registrationRequest.getDateNaissance());
            user.setRole("USER");
            user.setPoints(0);
            if(emailAlreadyExists(registrationRequest.getEmail())) {
                resp.setMessage("Email Déja Existe");
                resp.setStatusCode(400);
                return resp;
            }


            //calcule Age Automatiqument à partir Date Naissance
            if(user.getDateNaissance() != null) {
                int age = Period.between(user.getDateNaissance(), LocalDate.now()).getYears();
                user.setAge(age);
            }
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            User userResult = userRepository.save(user);

            if(userResult.getId() > 0) {
                resp.setUser((userResult));
                resp.setMessage("Utilisateur Ajouter Avec Succé");
                resp.setStatusCode(200);
            }
        }
        catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }



    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(500);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Connecter Avec Succé");

        } catch (Exception e) {
            response.setStatusCode(401);
            response.setMessage("Email ou mot de passe incorrect");
        }
        return response;
    }


    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        try{
            String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            User users = userRepository.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
