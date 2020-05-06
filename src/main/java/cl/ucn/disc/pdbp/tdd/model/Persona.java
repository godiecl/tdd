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

import cl.ucn.disc.pdbp.utils.Validation;

/**
 * The Persona class.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class Persona {

    /**
     * The Nombre.
     */
    private final String nombre;

    /**
     * The Apellido.
     */
    private final String apellido;

    /**
     * The Rut.
     */
    private final String rut;


    /**
     * Constructor de una persona.
     * - El nombre no puede ser null.
     * - El nombre debe tener al menos 2 letras.
     * - El apellido no puede ser null.
     * - El apellido debe tener al menos 3 letras.
     * - El rut no puede ser null.
     * - El rut debe ser valido.
     *
     * @param nombre   a utilizar.
     * @param apellido a usar.
     * @param rut      valido.
     */
    public Persona(String nombre, String apellido, String rut) {

        // Not null allowed!
        if (nombre == null || apellido == null || rut == null) {
            throw new NullPointerException("Nombre, apellido and rut cannot be null");
        }

        // Size of nombre
        if (nombre.length() < 2) {
            throw new RuntimeException("Nombre must be greater than 2 characters");
        }
        this.nombre = nombre;

        // Size of apellido
        if (apellido.length() < 3) {
            throw new RuntimeException("Apellido must be greater than 3 characters");
        }
        this.apellido = apellido;

        // RUT valid.
        if (!Validation.isRutValid(rut)) {
            throw new RuntimeException("RUT should be valid");
        }
        this.rut = rut;

    }

    /**
     * @return the nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellido.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return the rut.
     */
    public String getRut() {
        return rut;
    }

    /**
     * @return the nombre plus apellido.
     */
    public String getNombreApellido() {
        return nombre + " " + apellido;
    }
}
