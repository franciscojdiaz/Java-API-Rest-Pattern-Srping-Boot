package com.challenge.darien.service;

import com.challenge.darien.dominio.Equipo;

import java.util.List;

public interface EquipoService {

    Equipo crearEquipo(Equipo equipo);
    Equipo actualizarEquipo(Equipo equipo);
    List<Equipo> obtenerTodosLosEquipos();
    Equipo obtenerEquipoPorNombre(String nombre);
    Equipo obtenerEquipoPorCiudad(String nombre);
    Equipo obtenerEquipoPorId(int id);
    void eliminarEquipoPorId(int id);

}
