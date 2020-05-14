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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model test.
 *
 * @author Diego Urrutia-Astorga.
 */
@SuppressWarnings({"MessageMissingOnJUnitAssertion", "ClassIndependentOfModule", "ClassOnlyUsedInOneModule", "ClassNamePrefixedWithPackageName"})
public final class ModelTest {

    /**
     * The Logger (console)
     */
    private static final Logger log = LoggerFactory.getLogger(ModelTest.class);

    /**
     * Test the Persona.
     * - El nombre no puede ser null.
     * - El nombre debe tener al menos 2 letras.
     * - El apellido no puede ser null.
     * - El apellido debe tener al menos 3 letras.
     * - El rut no puede ser null.
     * - El rut debe ser valido.
     */
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    @Test
    public void testPersona() {

        log.debug("Testing Persona ..");

        // The data!
        log.debug(".. valid ..");
        String nombre = "Andrea";
        String apellido = "Contreras";
        String nombreApellido = nombre + " " + apellido;
        String rutOk = "152532873";
        String rutError = "15253287K";
        String email = "acontreras@ucn.cl";

        // Test constructor and getters
        Persona persona = new Persona(nombre, apellido, rutOk, email);
        Assertions.assertEquals(nombre, persona.getNombre());
        Assertions.assertEquals(apellido, persona.getApellido());
        Assertions.assertEquals(persona.getNombreApellido(), nombreApellido);
        Assertions.assertEquals(rutOk, persona.getRut());
        Assertions.assertEquals(null, persona.getDireccion());
        Assertions.assertEquals(null, persona.getTelefonoFijo());
        Assertions.assertEquals(null, persona.getTelefonoMovil());
        Assertions.assertEquals(email, persona.getEmail());
        Assertions.assertNotEquals(rutError, persona.getRut());

        // Testing nullity
        log.debug(".. nullity ..");
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, null, null, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, null, rutOk, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, null, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, rutOk, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, null, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, rutOk, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, apellido, null, email));

        // Testing the size of nombre
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("", apellido, rutOk, email));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("L", apellido, rutOk, email));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Lu", "", rutOk, email));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Lu", "Ch", rutOk, email));

        // Testing invalid rut
        log.debug(".. rut ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutError, email));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, null, rutOk, email));

        // Testing invalid email
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutOk, null));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutOk, "null"));

        log.debug("Done.");

    }

    /**
     * Test the email.
     */
    @Test
    public void testEmail() {

        // TODO: Agregar mas casos de prueba.

        Assertions.assertTrue(Validation.isEmailValid("durrutia@ucn.cl"));
        Assertions.assertTrue(Validation.isEmailValid("durru_tia@ucn.cl"));
        Assertions.assertTrue(Validation.isEmailValid("123@ucn.cl"));

        Assertions.assertFalse(Validation.isEmailValid(null));
        Assertions.assertFalse(Validation.isEmailValid("19"));
        Assertions.assertFalse(Validation.isEmailValid("durrutia@u cn.cl"));
        Assertions.assertFalse(Validation.isEmailValid("@ucn.cl"));
        Assertions.assertFalse(Validation.isEmailValid("durrutia"));
        Assertions.assertFalse(Validation.isEmailValid("durrutia@"));
        Assertions.assertFalse(Validation.isEmailValid("durr@utia@ucn.cl"));

    }

    /**
     * Test the digito verificador.
     */
    @Test
    public void testDigitoVerificador() {

        Assertions.assertTrue(Validation.isRutValid("152532873"));
        Assertions.assertTrue(Validation.isRutValid("21195194K"));
        Assertions.assertTrue(Validation.isRutValid("121244071"));
        Assertions.assertTrue(Validation.isRutValid("198127949"));
        Assertions.assertTrue(Validation.isRutValid("202294316"));
        Assertions.assertTrue(Validation.isRutValid("19"));

        Assertions.assertFalse(Validation.isRutValid(null));
        Assertions.assertFalse(Validation.isRutValid(""));
        Assertions.assertFalse(Validation.isRutValid("-"));
        Assertions.assertFalse(Validation.isRutValid("..."));
        Assertions.assertFalse(Validation.isRutValid("1"));
        Assertions.assertFalse(Validation.isRutValid("1525A2873"));
        Assertions.assertFalse(Validation.isRutValid("15253287K"));
        Assertions.assertFalse(Validation.isRutValid("15253287-"));

    }

}
