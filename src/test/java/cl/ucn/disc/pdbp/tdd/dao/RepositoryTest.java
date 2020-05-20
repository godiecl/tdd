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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
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
 * The Test of Repository.
 *
 * @author Diego Urrutia-Astorga.
 */
public class RepositoryTest {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(RepositoryTest.class);

    /**
     * The main repository.
     */
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    @Test
    public void testRepository() {

        // The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:";

        // Can't instanciate withoud connection
        Assertions.assertThrows(IllegalArgumentException.class, () -> new RepositoryOrmLite<>(null, TheModel.class));

        // The source of the data
        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

            // Can't instanciate withoud connection
            Assertions.assertThrows(IllegalArgumentException.class, () -> new RepositoryOrmLite<>(connectionSource, null));

            // Create the tables
            TableUtils.createTableIfNotExists(connectionSource, TheModel.class);

            // The repository
            Repository<TheModel, Long> theRepo = new RepositoryOrmLite<>(connectionSource, TheModel.class);

            // Can't create a empty class, but the id increment
            Assertions.assertThrows(RuntimeException.class, () -> theRepo.create(new TheModel()));

            // Insert the model
            {
                log.debug("Testing create with null ..");
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> theRepo.findById(null), "Create with null");

                log.debug("Testing findById with null ..");
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> theRepo.findById(null), "FindById with null");

                TheModel theModel = new TheModel(
                        ZonedDateTime.now(),
                        "The Name"
                );

                log.debug("Testing create Model ..");
                if (!theRepo.create(theModel)) {
                    Assertions.fail("Can't insert the model");
                }

                log.debug("Model created with id: {}.", theModel.getId());
            }

            // Request the model
            {
                log.debug("Testing findAll() ..");
                List<TheModel> list = theRepo.findAll();
                Assertions.assertNotNull(list, "List was null");
                Assertions.assertEquals(1, list.size(), "List size != 0");
            }

            // Update
            {
                log.debug("Testing with id ..");
                TheModel theModel = theRepo.findById(2L);
                Assertions.assertNotNull(theModel, "Model was null");
                theModel.setTheName("The New Name");

                log.debug("Testing the update ..");
                if (!theRepo.update(theModel)) {
                    Assertions.fail("Can't update the model");
                }
            }

            // Update-Delete
            {
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> theRepo.update(null), "Update with null");

                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> theRepo.delete(null), "Delete with null");

                log.debug("Finding with id 2 ..");
                TheModel theModel = theRepo.findById(2L);
                Assertions.assertNotNull(theModel, "Model was null");
                Assertions.assertEquals("The New Name", theModel.getTheName(), "The Name was !=");

                log.debug("Deleting id 1 ..");
                if (theRepo.delete(1L)) {
                    Assertions.fail("Delete a non-existent model");
                }

                log.debug("Deleting id 2 ..");
                if (!theRepo.delete(2L)) {
                    Assertions.fail("Can't delete a valid model");
                }
                log.debug("Finding with id 2 ..");
                Assertions.assertNull(theRepo.findById(2L), "TheModel removed exist in the database");

                log.debug("Find all ..");
                List<TheModel> list = theRepo.findAll();
                Assertions.assertEquals(0, list.size(), "Size != 0");
            }

        } catch (SQLException | IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    /**
     * The Model.
     */
    @DatabaseTable
    public static final class TheModel {

        /**
         * The id: Primary Key (autoincrement).
         */
        @DatabaseField(generatedId = true)
        private Long id;

        /**
         * The date.
         */
        @DatabaseField(persisterClass = ZonedDateTimeType.class)
        private ZonedDateTime theDate;

        /**
         * The string
         */
        @DatabaseField(canBeNull = false)
        private String theName;

        /**
         * Empty constructor.
         */
        TheModel() {
            // Nothing here
        }

        /**
         * The Constructor.
         */
        public TheModel(ZonedDateTime theDate, String theName) {
            this.theDate = theDate;
            this.theName = theName;
        }

        /**
         * @return the id.
         */
        public Long getId() {
            return id;
        }

        /**
         * @return the theDate.
         */
        public ZonedDateTime getTheDate() {
            return theDate;
        }

        /**
         * @return the theName.
         */
        public String getTheName() {
            return theName;
        }

        /**
         * @param theName to update.
         */
        public void setTheName(String theName) {
            this.theName = theName;
        }
    }


}
