package com.example.iDoctorbackend.repositories;

import com.example.iDoctorbackend.models.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findDoctorsByUsersId(Long tagId);
}
