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
 * The Control.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class Control {

    /**
     * The fecha of Control.
     */
    private final ZonedDateTime fecha;

    /**
     * The fecha of next Control.
     */
    private final ZonedDateTime fechaProximoControl;

    /**
     * The temperatura (Celsius).
     * Min: 20.
     * Max: 50.
     */
    private final float temperatura;

    /**
     * The Peso (kg).
     * Min: 0.
     * Max: 200. // TODO: Verificar el valor maximo.
     */
    private final float peso;

    /**
     * The altura (cm).
     * Min: 0
     * Max: 200 // TODO: Verificar la altura maxima de un paciente.
     */
    private final float altura;

    /**
     * The Diagnostico
     */
    private final String diagnostico;

    /**
     * The Veterinario.
     * Nota: Enlace many to one con veterinario.
     */
    private final Persona veterinario;

    /**
     * The Constructor.
     *
     * @param fecha               to use.
     * @param fechaProximoControl to use.
     * @param temperatura         to use.
     * @param peso                to use.
     * @param altura              to use.
     * @param diagnostico         to use.
     * @param veterinario         to use.
     */
    public Control(ZonedDateTime fecha, ZonedDateTime fechaProximoControl, float temperatura, float peso, float altura, String diagnostico, Persona veterinario) {
        // TODO: Add the restrictions.
        this.fecha = fecha;
        this.fechaProximoControl = fechaProximoControl;
        this.temperatura = temperatura;
        this.peso = peso;
        this.altura = altura;
        this.diagnostico = diagnostico;
        this.veterinario = veterinario;
    }

    /**
     * @return the fecha.
     */
    public ZonedDateTime getFecha() {
        return fecha;
    }

    /**
     * @return the fechaProximoControl.
     */
    public ZonedDateTime getFechaProximoControl() {
        return fechaProximoControl;
    }

    /**
     * @return the temperatura.
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     * @return the peso.
     */
    public float getPeso() {
        return peso;
    }

    /**
     * @return the altura.
     */
    public float getAltura() {
        return altura;
    }

    /**
     * @return the diagnostico.
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * @return the veterinario.
     */
    public Persona getVeterinario() {
        return veterinario;
    }
}
