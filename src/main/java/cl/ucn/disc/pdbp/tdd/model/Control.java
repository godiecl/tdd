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
import com.j256.ormlite.field.DatabaseField;

import java.time.ZonedDateTime;

/**
 * The Control.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class Control {

    /**
     * The id: Primary Key (autoincrement).
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * The fecha of Control.
     */
    @DatabaseField(persisterClass = ZonedDateTimeType.class)
    private ZonedDateTime fecha;

    /**
     * The fecha of next Control.
     */
    @DatabaseField(persisterClass = ZonedDateTimeType.class)
    private ZonedDateTime fechaProximoControl;

    /**
     * The temperatura (Celsius).
     * Min: 20.
     * Max: 50.
     */
    @DatabaseField
    private float temperatura;

    /**
     * The Peso (kg).
     * Min: 0.
     * Max: 200. // TODO: Verificar el valor maximo.
     */
    @DatabaseField
    private float peso;

    /**
     * The altura (cm).
     * Min: 0
     * Max: 200 // TODO: Verificar la altura maxima de un paciente.
     */
    @DatabaseField
    private float altura;

    /**
     * The Diagnostico
     */
    @DatabaseField
    private String diagnostico;

    /**
     * The Veterinario.
     * Nota: Enlace many to one con veterinario.
     */
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Persona veterinario;

    /**
     * The Ficha-Control.
     */
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Ficha ficha;

    /**
     * Empty constructor.
     */
    Control() {
        // nothing here
    }

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
    public Control(ZonedDateTime fecha,
                   ZonedDateTime fechaProximoControl,
                   float temperatura,
                   float peso,
                   float altura,
                   String diagnostico,
                   Persona veterinario,
                   Ficha ficha) {
        // TODO: Add the restrictions.
        this.fecha = fecha;
        this.fechaProximoControl = fechaProximoControl;
        this.temperatura = temperatura;
        this.peso = peso;
        this.altura = altura;
        this.diagnostico = diagnostico;
        this.veterinario = veterinario;
        this.ficha = ficha;
    }

    /**
     * @return the id.
     */
    public Long getId() {
        return id;
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

    /**
     * @return the ficha.
     */
    public Ficha getFicha() {
        return ficha;
    }

}
