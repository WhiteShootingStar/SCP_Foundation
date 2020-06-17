package com.MASFinalProject.FinalProject.repository;

import com.MASFinalProject.FinalProject.model.Scientist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScientistRepo extends CrudRepository<Scientist,Long> {
    @Query("select s from Scientist s join fetch s.observedSCP where s.PersonId= ?1")
    public Optional<Scientist> findByPersonId(@Param("personId") Long id);
    @Query("select s from Scientist s  join fetch s.conductedSCP where s.PersonId= ?1")
    public Optional<Scientist> findByPersonIdConducting(@Param("personId") Long id);
    @Query("select s from Scientist s join fetch s.observedSCP left join  fetch s.conductedSCP where s.PersonId= ?1 ")//leftJoin is required as subset mau be null
    public Optional<Scientist> findByPersonIdAll(@Param("personId") Long id);


    @Override
    List<Scientist> findAll();
}
