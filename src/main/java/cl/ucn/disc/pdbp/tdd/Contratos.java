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

package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;

import java.util.List;

/**
 * The List of Contratos.
 *
 * @author Diego Urrutia-Astorga.
 */
public interface Contratos {

    /**
     * Contrato: C01-Registrar los datos de un Paciente.
     *
     * @param ficha to insert.
     * @return the {@link Ficha} in the backend.
     */
    Ficha registrarPaciente(Ficha ficha);

    /**
     * Contrato: C02-Registrar los datos de una Persona.
     *
     * @param persona to insert.
     * @return the {@link Persona} in the backend.
     */
    Persona registrarPersona(Persona persona);

    /**
     * Contrato: C03-Buscar Ficha
     *
     * @param query to search.
     * @return the {@link List} of {@link Ficha}.
     */
    List<Ficha> buscarFicha(String query);

    /**
     * Contrato: C04-Buscar Persona
     *
     * @param rut to search.
     * @return the {@link Persona}.
     */
    Persona findByRut(String rut);

    /**
     * @return the {@link List} of {@link Ficha}.
     */
    List<Ficha> getAllFichas();

}


