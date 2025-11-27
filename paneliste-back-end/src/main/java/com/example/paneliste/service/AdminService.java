package com.example.paneliste.service;


import com.example.paneliste.dto.ReqRes;
import com.example.paneliste.model.User;
import com.example.paneliste.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean emailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    // Create Compte Admin
    public ReqRes createAdmin(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();

        try {
            User user = new User();
            user.setNom(registrationRequest.getNom());
            user.setPrenom(registrationRequest.getPrenom());
            user.setEmail(registrationRequest.getEmail());
            user.setRole("ADMIN");
            user.setPoints(0);
            if(emailAlreadyExists(registrationRequest.getEmail())) {
                resp.setMessage("Email Déja Existe");
                resp.setStatusCode(500);
            }
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            User userResult = userRepository.save(user);

            if(userResult.getId() > 0) {
                resp.setUser((userResult));
                resp.setMessage("ADMIN Ajouter Avec Succé");
                resp.setStatusCode(200);
            }
        }
        catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();

        try {
            List<User> result = userRepository.findAll();
            List<User> filteredUsers = result.stream()
                    .filter(user -> !"ADMIN".equalsIgnoreCase(user.getRole()))
                    .toList();
            if (!filteredUsers.isEmpty()) {
                reqRes.setUserList(filteredUsers);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }

    public ReqRes getUsersById(Long id) {
        ReqRes reqRes = new ReqRes();
        try {
            User usersById = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setUser(usersById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes deleteUser(Long userId) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                userRepository.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    /*public ReqRes updateUser(Long userId, User updatedUser) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                OurUsers existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setCity(updatedUser.getCity());
                existingUser.setRole(updatedUser.getRole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                OurUsers savedUser = usersRepo.save(existingUser);
                reqRes.setOurUsers(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }*/

    public ReqRes getMyInfo(String email){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                reqRes.setUser(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;

    }

}
