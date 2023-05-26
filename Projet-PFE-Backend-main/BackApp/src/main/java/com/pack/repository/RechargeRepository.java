package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.pack.models.Recharge;
import com.pack.models.User;

@RestController
public interface RechargeRepository extends JpaRepository<Recharge, Long> {
}
