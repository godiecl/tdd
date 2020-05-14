/*
 * MIT License
 *
 * Copyright (c) 2020 Diego Urrutia-Astorga <durrutia@ucn.cl>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cl.ucn.disc.pdbp.tdd.model;

import cl.ucn.disc.pdbp.tdd.dao.ZonedDateTimeType;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ficha Veterinaria.
 *
 * @author Diego Urrutia-Astorga.
 */
@SuppressWarnings("ClassWithTooManyFields")
@DatabaseTable(tableName = "ficha")
public final class Ficha {

    /**
     * The id: Primary Key (autoincrement).
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * Numero de ficha
     */
    @DatabaseField
    private Integer numero;

    /**
     * Nombre del paciente
     */
    @DatabaseField(canBeNull = false)
    private String nombrePaciente;

    /**
     * Especie: ej. canino
     */
    @DatabaseField(canBeNull = false)
    private String especie;

    /**
     * Fecha de nacimiento
     */
    @DatabaseField(persisterClass = ZonedDateTimeType.class)
    private ZonedDateTime fechaNacimiento;

    /**
     * Raza
     */
    @DatabaseField
    private String raza;

    /**
     * Sexo
     */
    @DatabaseField(canBeNull = false)
    private Sexo sexo;

    /**
     * Color: rojo cobrizo
     */
    @DatabaseField
    private String color;

    /**
     * Tipo: interno/externo
     */
    @DatabaseField(canBeNull = false)
    private Tipo tipo;

    /**
     * The Owner.
     */
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Persona duenio;

    /**
     * The List of Control.
     */
    @ForeignCollectionField(eager = true)
    private ForeignCollection<Control> controles;

    /**
     * Empty constructor.
     */
    Ficha() {
        // nothing here.
    }

    /**
     * The Constructor.
     *
     * @param numero          de la ficha.
     * @param nombrePaciente  de la mascota.
     * @param especie         del paciente.
     * @param fechaNacimiento de la mascota.
     * @param raza            de la mascota.
     * @param sexo            de la mascota.
     * @param color           de la mascota.
     * @param tipo            de la mascota.
     * @param duenio          de la mascota.
     */
    public Ficha(Integer numero,
                 String nombrePaciente,
                 String especie,
                 ZonedDateTime fechaNacimiento,
                 String raza,
                 Sexo sexo,
                 String color,
                 Tipo tipo,
                 Persona duenio) {
        // TODO: add validations !!
        this.numero = numero;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tipo = tipo;
        this.duenio = duenio;
    }

    /**
     * @return the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the numero.
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @return the nombrePaciente.
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     * @return the especie.
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * @return the fechaNacimiento.
     */
    public ZonedDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @return the raza.
     */
    public String getRaza() {
        return raza;
    }

    /**
     * @return the sexo.
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     * @return the color.
     */
    public String getColor() {
        return color;
    }

    /**
     * @return the tipo.
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @return the duenio.
     */
    public Persona getDuenio() {
        return duenio;
    }

    /**
     * @return the List of Controles.
     */
    public List<Control> getControles() {
        return Collections.unmodifiableList(new ArrayList<>(controles));
    }

}
