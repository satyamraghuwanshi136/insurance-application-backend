package com.insuranceapp.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceapp.model.Nominee;

@Repository
public interface NomineeRepository extends JpaRepository<Nominee, Integer> {

}
