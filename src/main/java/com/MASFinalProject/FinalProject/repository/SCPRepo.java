package com.MASFinalProject.FinalProject.repository;

import com.MASFinalProject.FinalProject.model.SCP;
import com.MASFinalProject.FinalProject.model.Scientist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SCPRepo extends CrudRepository<SCP,Long> {

    @Query("select s from SCP s left join fetch s.observedBy where s.SCPId= ?1")
    public Optional<SCP> findBySCPId(@Param("SCPId") Long id);
    @Query("select s from SCP s left join fetch s.observedBy left join fetch s.conductedExperimentsOnBy where s.SCPId= ?1")
    public Optional<SCP> findBySCPIdAll(@Param("SCPId") Long id);

    @Query("select s from SCP s left join fetch s.conductedExperimentsOnBy where s.SCPId= ?1")
    public Optional<SCP> findBySCPIdConductors(@Param("SCPId") Long id);
    @Override
    List<SCP> findAll();
}
