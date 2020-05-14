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

package cl.ucn.disc.pdbp.tdd.dao;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Sexo;
import cl.ucn.disc.pdbp.tdd.model.Tipo;
import cl.ucn.disc.pdbp.utils.Entity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Model test.
 *
 * @author Diego Urrutia-Astorga.
 */
@SuppressWarnings("LawOfDemeter")
public final class StorageTest {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

    /**
     * Testing the Repository of Persona.
     */
    @Test
    public void testRepositoryPersona() {

        // The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

            // Create the table in the backend
            // TODO: Include this call in the repository?
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);

            // Test: connection null
            Assertions.assertThrows(RuntimeException.class, () ->
                    new RepositoryOrmLite<>(null, Persona.class)
            );

            // Test: Class null
            Assertions.assertThrows(RuntimeException.class, () ->
                    new RepositoryOrmLite<>(connectionSource, null)
            );

            // The Persona repository.
            Repository<Persona, Long> theRepo = new RepositoryOrmLite<>(connectionSource, Persona.class);

            // Get the list of all. Size == 0.
            {
                List<Persona> personas = theRepo.findAll();
                // The size must be zero.
                Assertions.assertEquals(0, personas.size(), "Size != 0 !!");
            }

            // Test the insert v1: ok.
            {
                // New Persona
                Persona persona = new Persona("Andrea", "Contreras", "152532873", "acontreras@ucn.cl");
                if (!theRepo.create(persona)) {
                    Assertions.fail("Can't insert !");
                }
                Assertions.assertNotNull(persona.getId(), "Id was null");
            }

            // Test the insert v2: error.
            {
                // New Persona
                Persona persona = new Persona("Andrea", "Contreras", "152532873", "acontreras@ucn.cl");
                Assertions.assertThrows(RuntimeException.class, () -> theRepo.create(persona));
            }

            // Get the list of all. Size == 1.
            {
                List<Persona> personas = theRepo.findAll();
                // The size must be one.
                Assertions.assertEquals(1, personas.size(), "Size != 1 !!");
            }

            // Delete
            {
                if (!theRepo.delete(1L)) {
                    Assertions.fail("Can't delete !");
                }
                Assertions.assertEquals(0, theRepo.findAll().size(), "Size != 0");
            }

        } catch (SQLException | IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    /**
     * Testing the Repository of Ficha.
     */
    @Test
    public void testRepositoryFicha() {

        // The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

            // Create the table in the backend
            // TODO: Include this call in the repository?
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);

            // The repository.
            Repository<Ficha, Long> theRepo = new RepositoryOrmLite<>(connectionSource, Ficha.class);

            // Get the list of all. Size == 0.
            {
                List<Ficha> fichas = theRepo.findAll();
                // The size must be zero.
                Assertions.assertEquals(0, fichas.size(), "Size != 0 !!");
            }

            // The Ficha to insert.
            {
                Ficha ficha = new Ficha(
                        123,
                        "Firulais",
                        "Canino",
                        ZonedDateTime.now(),
                        "Rottweiler",
                        Sexo.MACHO,
                        "Negro con amarillo",
                        Tipo.INTERNO
                );

                if (!theRepo.create(ficha)) {
                    Assertions.fail("Can't insert!");
                }
                Assertions.assertNotNull(ficha.getId(), "Id was null");
            }

            // Get from repository
            {
                Ficha ficha = theRepo.findById(1L);
                Assertions.assertNotNull(ficha, "404 Not found!");
                log.debug("Ficha: {}.", Entity.toString(ficha));
            }

        } catch (SQLException | IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    /**
     * Testing de ORMLite + H2 (database).
     */
    @Test
    public void testDatabase() throws SQLException {

        // The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        // Connection source: autoclose with the try/catch
        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

            // Create the table from the Persona annotations
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);

            // The dao of Persona
            Dao<Persona, Long> daoPersona = DaoManager.createDao(connectionSource, Persona.class);

            // New Persona
            Persona persona = new Persona("Andrea", "Contreras", "152532873", "acontreras@ucn.cl");

            // Insert Persona into the database
            int tuples = daoPersona.create(persona);
            log.debug("Id: {}", persona.getId());
            //
            Assertions.assertEquals(1, tuples, "Save tuples != 1");

            // Get from db
            Persona personaDb = daoPersona.queryForId(persona.getId());

            Assertions.assertEquals(persona.getNombre(), personaDb.getNombre(), "Nombre not equals!");
            Assertions.assertEquals(persona.getApellido(), personaDb.getApellido(), "Apellido not equals!");
            Assertions.assertEquals(persona.getRut(), personaDb.getRut(), "Rut not equals!");

            // Search by rut: SELECT * FROM `persona` WHERE `rut` = '152532873'
            List<Persona> personaList = daoPersona.queryForEq("rut", "152532873");
            Assertions.assertEquals(1, personaList.size(), "More than one person?!");

            // Not found by rut
            Assertions.assertEquals(0, daoPersona.queryForEq("rut", "19").size(), "Found something !?");

        } catch (IOException e) {
            log.error("Error", e);
        }

    }

}
