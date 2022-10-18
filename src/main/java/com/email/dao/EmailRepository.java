package com.email.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.email.model.Email;

public interface EmailRepository extends JpaRepository<Email, Integer> {

}
