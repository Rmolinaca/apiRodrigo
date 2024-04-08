package com.users.apirestful.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.apirestful.apirest.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
