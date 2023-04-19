package com.challenge.darien.dao;


import com.challenge.darien.dominio.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoDao extends JpaRepository<Equipo, Integer> {

    @Query(value = "select * from darien_test.dar_equipos where eq_name like  %?1", nativeQuery = true)
    Equipo fibdEquipoByName(@Param("nombre") String nombre);

    @Query(value = "select * from darien_test.dar_equipos where eq_city like %?1", nativeQuery = true)
    Equipo fibdEquipoByCity(@Param("city") String city);

    Equipo findEquipoById(int id);

}
