package com.challenge.darien.service;

import com.challenge.darien.dominio.Jugador;

import java.util.List;

public interface JugadorService {

        Jugador crearJugador(Jugador jugador);
        Jugador actualizarJugador(Jugador jugador);
        List<Jugador> obtenerTodosJugadores();
        Jugador obtenerJugadorPorId(int id);
        Jugador obtenerJugadorPorNombre(String nombre_jugador);
        void  eliminarJugadorPorId(int id);


}
