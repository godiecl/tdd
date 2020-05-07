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
