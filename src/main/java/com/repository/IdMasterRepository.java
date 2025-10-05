package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.IdMaster;

@Repository
public interface IdMasterRepository extends JpaRepository<IdMaster, Integer> {

	IdMaster findByName(String fieldName);
}
