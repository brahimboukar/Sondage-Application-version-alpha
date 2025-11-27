package com.example.paneliste.repository;

import com.example.paneliste.model.FonctionDetailee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FonctionDetaillsRepository extends JpaRepository<FonctionDetailee , Long> {
    List <FonctionDetailee> findByFonctionId(Long fonctionId);
}
