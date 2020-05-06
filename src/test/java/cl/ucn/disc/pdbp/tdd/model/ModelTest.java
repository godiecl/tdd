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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model test.
 *
 * @author Diego Urrutia-Astorga.
 */
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

        // Test constructor and getters
        Persona persona = new Persona(nombre, apellido, rutOk);
        Assertions.assertEquals(persona.getNombre(), nombre);
        Assertions.assertEquals(persona.getApellido(), apellido);
        Assertions.assertEquals(persona.getNombreApellido(), nombreApellido);
        Assertions.assertEquals(persona.getRut(), rutOk);
        Assertions.assertNotEquals(persona.getRut(), rutError);

        // Testing nullity
        log.debug(".. nullity ..");
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, null, rutOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, rutOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, rutOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, apellido, null));

        // Testing the size of nombre
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("", apellido, rutOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("L", apellido, rutOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Lu", "", rutOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Lu", "Ch", rutOk));

        // Testing invalid rut
        log.debug(".. rut ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutError));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, null, rutOk));

        log.debug("Done.");

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
