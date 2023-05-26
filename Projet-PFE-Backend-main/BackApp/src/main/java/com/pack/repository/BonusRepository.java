package com.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Bonus;


@RestController
public interface BonusRepository extends JpaRepository<Bonus, Long> {

}
