package com.guilherme.aequilibrium.transformers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.aequilibrium.transformers.model.TransformerEntity;

@Repository
public interface TransformersRepository extends JpaRepository<TransformerEntity, Long> {

}
