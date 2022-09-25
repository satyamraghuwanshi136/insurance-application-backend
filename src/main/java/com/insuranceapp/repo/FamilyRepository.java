package com.insuranceapp.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceapp.model.FamilyMembers;


@Repository
public interface FamilyRepository extends JpaRepository<FamilyMembers,Integer> {

}

