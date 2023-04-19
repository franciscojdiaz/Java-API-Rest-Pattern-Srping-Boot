package com.challenge.darien.service.Impl;


import com.challenge.darien.dao.EquipoDao;
import com.challenge.darien.dominio.Equipo;
import com.challenge.darien.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {


    @Autowired
    EquipoDao equipoDao;

    @Override
    public Equipo crearEquipo(Equipo equipo) {
        return equipoDao.save(equipo);
    }

    @Override
    public Equipo actualizarEquipo(Equipo equipo) {
        Equipo equipoActualizado = equipoDao.save(equipo);
        return equipoActualizado;
    }

    @Override

    public List<Equipo> obtenerTodosLosEquipos() {
        return equipoDao.findAll();
    }

    @Override
    public Equipo obtenerEquipoPorNombre(String nombre) {
        return equipoDao.fibdEquipoByName(nombre);
    }

    @Override
    public Equipo obtenerEquipoPorCiudad(String city) {
        return equipoDao.fibdEquipoByCity(city);
    }

    @Override
    public Equipo obtenerEquipoPorId(int id) {
        return equipoDao.findEquipoById(id);
    }

    @Override
    public void eliminarEquipoPorId(int id) {
        equipoDao.deleteById(id);
    }
}
