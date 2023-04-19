package com.challenge.darien.controller;


import com.challenge.darien.dominio.Equipo;
import com.challenge.darien.dominio.Jugador;
import com.challenge.darien.service.EquipoService;
import com.challenge.darien.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    EquipoService equipoService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Equipo> crearEquipo(@RequestParam("nombre_equipo") String nombre_equipo,
                                              @RequestParam("ciudad") String ciudad){

        Equipo equipoCreado = new Equipo();
        equipoCreado.setName(nombre_equipo);
        equipoCreado.setCity(ciudad);
        log.info("El equipo " + nombre_equipo +" fue creado satisfactoriamente!");
        return new ResponseEntity<>(equipoService.crearEquipo(equipoCreado), HttpStatus.OK);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<Equipo>> obtenerTodasLosEquipos(){
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @GetMapping(value = "equipoPorId/{id}")
    public  ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable("id") int id){
        Equipo equipoObtenido = equipoService.obtenerEquipoPorId(id);
        if(Objects.isNull(equipoObtenido)){
            log.info("El equipo con id : " + id + " no exite");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(equipoObtenido, HttpStatus.OK);
    }


    @GetMapping(value = "equipoPorNombre/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Equipo> obtenerEquipoPorNombre(@PathVariable("nombre") String nombre){
        Equipo equipoObtenido = equipoService.obtenerEquipoPorNombre(nombre);
        if(Objects.isNull(equipoObtenido)){
            log.info("El equipo con el nombre : " + nombre + " no exite");
        }
        return new ResponseEntity<>(equipoObtenido, HttpStatus.OK);
    }

    @GetMapping(value = "equipoPorCiudad/{ciudad}")
    public  ResponseEntity<Equipo> obtenerEquipoPorCiudad(@PathVariable("ciudad") String ciudad){
        Equipo equipoObtenido = equipoService.obtenerEquipoPorCiudad(ciudad);
        if(Objects.isNull(equipoObtenido)){
            log.info("No hay equipo en la ciudad : " + ciudad );
        }
        return new ResponseEntity<>(equipoObtenido, HttpStatus.OK);
    }

    @DeleteMapping(value = "eliminarPorId/{id}")
    @ResponseStatus(HttpStatus.OK)
    private String eliminarEquipoPorId(@PathVariable("id") int id){
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        if(Objects.isNull(equipo)) {
            return "El equipo con id  " +id + " no existe " ;
        }
        equipoService.eliminarEquipoPorId(id);
        log.info("Se ha eliminado el equipo : " + equipo.getName() + " correctamente");
        return "Se ha eliminado el equipo : " + equipo.getName() + " correctamente";
    }


    @GetMapping(value = "golesEquipoPorId/{id}")
    public  String  obtenerGolesEquipoPorId(@PathVariable("id") int id){
        Equipo equipo = equipoService.obtenerEquipoPorId(id);

        try{
        List<Jugador> jugadorList = equipo.getJugadores();

        if(jugadorList.size() == 0){
            return "No hay jugadoes en este equipo " ;
        }
        int totalGoles = jugadorList.stream().mapToInt( jug -> jug.getGoal()).sum();
        if(totalGoles == 0){
            return "No hay goles acumulados en este equipo " ;
        }
        }catch (Exception ex ){
            return "El equipo no existe ";
        }
        return "La totalidad de goles del equipo " + equipo.getName() + " es de : "  ;
    }

    @PutMapping(value = "actualizar", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Equipo> actualizarEquipoPorId(@RequestBody String jsonData) throws IOException {
        //aqui se recibe la categoria ya actualizada desde el font end
        Equipo equipoObtenida = JSONUtils.covertFromJsonToObject(jsonData, Equipo.class);
        if (Objects.isNull(equipoObtenida)){
            log.info(" el equipo : " + equipoObtenida.getName() + " no existe");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        log.info("Se ha actualizado el equipo : " + equipoObtenida.getName() + " correctamente");
        return new ResponseEntity<>(equipoService.actualizarEquipo(equipoObtenida), HttpStatus.OK);
    }

}
