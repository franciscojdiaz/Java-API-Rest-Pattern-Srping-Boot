package com.challenge.darien.controller;


import com.challenge.darien.dominio.Equipo;
import com.challenge.darien.dominio.Jugador;
import com.challenge.darien.service.EquipoService;
import com.challenge.darien.service.JugadorService;
import com.challenge.darien.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    JugadorService jugadorService;

    @Autowired
    EquipoService equipoService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Jugador> crearJugador(@RequestParam("nombre_jugador") String nombre_jugador,
                                                @RequestParam("goles") int goles,
                                                @RequestParam("idEquipo") int idEquipo)
    {
        Equipo equipo = equipoService.obtenerEquipoPorId(idEquipo);
        if(Objects.isNull(equipo)){
            log.info("El equipo con id  : " +idEquipo );
        }
        Jugador jugador = new Jugador();
        jugador.setName(nombre_jugador);
        jugador.setGoal(goles);
        jugador.setEquipo(equipo);
        return new ResponseEntity<>(jugadorService.crearJugador(jugador), HttpStatus.OK);
    }


    @GetMapping(value = "jugadorPorNombre/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Jugador> obtenerJugadorPorNombre(@PathVariable("nombre") String nombre){

        Jugador jugadorObtenido = jugadorService.obtenerJugadorPorNombre(nombre);
        if(Objects.isNull(jugadorObtenido)){
            log.info("El equipo con el nombre : " + nombre + " no exite");
        }
        return new ResponseEntity<>(jugadorObtenido, HttpStatus.OK);
    }


    @GetMapping(value = "jugadorPorId/{id}")
    public  ResponseEntity<Jugador> obtenerJugadorPorId(@PathVariable("id") int id){
        Jugador jugadorObtenido = jugadorService.obtenerJugadorPorId(id);
        if(Objects.isNull(jugadorObtenido)){
            log.info("El jugador con id : " + id + " no exite");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jugadorObtenido, HttpStatus.OK);
    }



    @DeleteMapping(value = "eliminarPorId/{id}")
    @ResponseStatus(HttpStatus.OK)
    private String eliminarJugadorPorId(@PathVariable("id") int id){
        Jugador jugador =jugadorService.obtenerJugadorPorId(id);
        if(Objects.isNull(jugador)) {
            return "El jugador con id  " +id + " no existe " ;
        }
        jugadorService.eliminarJugadorPorId(id);
        log.info("Se ha eliminado el jugador : " + jugador.getName() + " correctamente");
        return "Se ha eliminado el jugador : " + jugador.getName() + " correctamente";
    }

    @PutMapping(value = "actualizar", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Jugador> actualizarJugadorPorId(@RequestBody String jsonData) throws IOException {
        //aqui se recibe la categoria ya actualizada desde el font end
        Jugador jugadorObtenido = JSONUtils.covertFromJsonToObject(jsonData, Jugador.class);
        if (Objects.isNull(jugadorObtenido)){
            log.info(" el jugador : " + jugadorObtenido.getName() + " no existe");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        log.info("Se ha actualizado el equipo : " + jugadorObtenido.getName() + " correctamente");
        return new ResponseEntity<>(jugadorService.actualizarJugador(jugadorObtenido), HttpStatus.OK);
    }

}
