package com.challenge.darien.service.Impl;


import com.challenge.darien.dao.EquipoDao;
import com.challenge.darien.dao.JugadorDao;
import com.challenge.darien.dominio.Equipo;
import com.challenge.darien.dominio.Jugador;
import com.challenge.darien.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    JugadorDao jugadorDao;

    @Autowired
    EquipoDao equipoDao;

    @Override
    @Transactional
    public Jugador crearJugador(Jugador jugador) {
        return jugadorDao.save(jugador);
    }

    @Override
    public Jugador actualizarJugador(Jugador jugador) {

        Jugador jugador1 = jugadorDao.findJugadorById(jugador.getId());
        Equipo equipo = jugador1.getEquipo();
        jugador.setEquipo(equipo);
        Jugador jugadorActualizado = jugadorDao.save(jugador);

        return jugadorActualizado;
    }

    @Override
    @Transactional
    public List<Jugador> obtenerTodosJugadores() {
        return jugadorDao.findAll();
    }

    @Override
    @Transactional
    public Jugador obtenerJugadorPorId(int id) {
        return jugadorDao.findJugadorById(id);
    }

    @Override
    @Transactional
    public Jugador obtenerJugadorPorNombre(String nombre_jugador) {
        return jugadorDao.findJugadorByName(nombre_jugador);
    }

    @Override
    public void eliminarJugadorPorId(int id) {
        jugadorDao.deleteById(id);
    }
}
