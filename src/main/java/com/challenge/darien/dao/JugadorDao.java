package com.challenge.darien.dao;


import com.challenge.darien.dominio.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorDao extends JpaRepository<Jugador, Integer> {

    @Query(value = "select * from darien_test.dar_jugadores where jug_name like  %?1", nativeQuery = true)
    Jugador findJugadorByName(@Param("nombre") String nombre);

    Jugador findJugadorById(int id);

}
