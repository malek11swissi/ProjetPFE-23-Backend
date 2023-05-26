package com.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Gouvernorat;


@RestController
public interface GouvernoratRepository extends JpaRepository<Gouvernorat, Long> {

}
