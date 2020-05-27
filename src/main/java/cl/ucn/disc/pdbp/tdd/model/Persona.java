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

import cl.ucn.disc.pdbp.utils.Validation;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The Persona class.
 * <p>
 * - El nombre no puede ser null.
 * - El nombre debe tener al menos 2 letras.
 * - El apellido no puede ser null.
 * - El apellido debe tener al menos 3 letras.
 * - El rut no puede ser null.
 * - El rut debe ser valido.
 *
 * @author Diego Urrutia-Astorga.
 */
@DatabaseTable(tableName = "persona")
public final class Persona {

    /**
     * The id: Primary Key (autoincrement).
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * The Rut.
     */
    @DatabaseField(canBeNull = false, unique = true, index = true)
    private String rut;

    /**
     * The Nombre.
     */
    @DatabaseField(canBeNull = false)
    private String nombre;

    /**
     * The Apellido.
     */
    @DatabaseField(canBeNull = false)
    private String apellido;

    /**
     * The Direccion.
     */
    @DatabaseField
    private String direccion;

    /**
     * The telefono fijo.
     */
    @DatabaseField
    private Long telefonoFijo;

    /**
     * The telefono movil.
     * FIXME: add the not null to telefonoMovil.
     */
    @DatabaseField
    private Long telefonoMovil;

    /**
     * The Email
     */
    @DatabaseField(canBeNull = false, unique = true)
    private String email;

    /**
     * Empty contructor: Default visibility + empty body.
     */
    Persona() {
        // nothing here.
    }

    /**
     * The Constructor.
     * FIXME: remove this contructor -> update the tests.
     */
    public Persona(String nombre, String apellido, String rut, String email) {
        this(rut, nombre, apellido, null, null, null, email);
    }

    /**
     * The Constructor.
     */
    public Persona(String rut,
                   String nombre,
                   String apellido,
                   String direccion,
                   Long telefonoFijo,
                   Long telefonoMovil,
                   String email) {

        // Not null allowed!
        if (nombre == null || apellido == null || rut == null) {
            throw new NullPointerException("Nombre, apellido and rut cannot be null");
        }

        // RUT valid.
        if (!Validation.isRutValid(rut)) {
            throw new RuntimeException("RUT should be valid");
        }
        this.rut = rut;

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

        // TODO: Add validations
        this.direccion = direccion;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;

        if (!Validation.isEmailValid(email)) {
            throw new RuntimeException("Email should be valid");
        }
        this.email = email;

    }

    /**
     * @return the id.
     */
    public Long getId() {
        return id;
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
     * @return the direccion.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return the telefonoFijo.
     */
    public Long getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * @return the telefonoMovil.
     */
    public Long getTelefonoMovil() {
        return telefonoMovil;
    }

    /**
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the nombre plus apellido.
     */
    public String getNombreApellido() {
        return nombre + " " + apellido;
    }

}
