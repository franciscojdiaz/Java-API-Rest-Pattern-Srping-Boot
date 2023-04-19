
package com.challenge.darien.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "dar_equipos", schema = "darien_test",
        indexes = {@Index(name = "nombre_idx", columnList = "eq_name", unique = true )})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Equipo extends AuditableBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "eq_name")
    private String name;

    @Column(name = "eq_city")
    private String city;


    @OneToMany(mappedBy = "equipo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Jugador> jugadores;


}
