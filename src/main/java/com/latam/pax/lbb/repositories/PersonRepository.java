package com.latam.pax.lbb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.latam.pax.lbb.domains.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
