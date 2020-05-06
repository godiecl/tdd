/*
 * Copyright 2019-2020 Diego Urrutia Astorga <durrutia@ucn.cl>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cl.ucn.disc.pdbp.tdd.model;

import java.time.ZonedDateTime;

/**
 * Ficha Veterinaria.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class Ficha {

    /**
     * Numero de ficha
     */
    private final Integer numero;

    /**
     * Nombre del paciente
     */
    private final String nombrePaciente;

    /**
     * Especie: ej. canino
     */
    private final String especie;

    /**
     * Fecha de nacimiento
     */
    private final ZonedDateTime fechaNacimiento;

    /**
     * Raza
     */
    private final String raza;

    /**
     * Sexo
     */
    private final Sexo sexo;

    /**
     * Color: rojo cobrizo
     */
    private final String color;

    /**
     * Tipo: interno/externo
     */
    private final Tipo tipo;

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
     */
    public Ficha(Integer numero, String nombrePaciente, String especie, ZonedDateTime fechaNacimiento, String raza, Sexo sexo, String color, Tipo tipo) {
        this.numero = numero;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tipo = tipo;
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
}
