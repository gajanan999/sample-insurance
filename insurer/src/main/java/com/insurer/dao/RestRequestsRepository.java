package com.insurer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurer.entities.RestRequestEntity;

@Repository
public interface RestRequestsRepository  extends JpaRepository<RestRequestEntity, Long>{

}
