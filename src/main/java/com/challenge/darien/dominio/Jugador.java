package com.challenge.darien.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dar_jugadores", schema = "darien_test",
        indexes = {@Index(name = "nomb_jug_idx", columnList = "jug_name", unique = true )})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jugador  extends AuditableBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "jug_name")
    private String name;

    @Column(name = "jug_goals")
    private int goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id")
    @JsonIgnore
    private Equipo equipo;

}
