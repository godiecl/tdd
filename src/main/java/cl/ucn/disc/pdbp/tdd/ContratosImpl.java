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

import cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite;
import cl.ucn.disc.pdbp.tdd.model.Control;
import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of {@link Contratos}.
 *
 * @author Diego Urrutia-Astorga.
 */
@SuppressWarnings("CollectionWithoutInitialCapacity")
public final class ContratosImpl implements Contratos {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ContratosImpl.class);

    /**
     * The connection to the backend.
     */
    private final ConnectionSource connectionSource;

    /**
     * The {@link cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite} of Ficha.
     */
    private final RepositoryOrmLite<Ficha, Long> repoFicha;

    /**
     * The {@link cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite} of Ficha.
     */
    private final RepositoryOrmLite<Persona, Long> repoPersona;

    /**
     * The {@link cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite} of Ficha.
     */
    private final RepositoryOrmLite<Control, Long> repoControl;

    /**
     * The Constructor.
     *
     * @param databaseUrl to use to connect.
     */
    public ContratosImpl(String databaseUrl) {

        log.debug("Using <{}> as databaseUrl ..", databaseUrl);
        try {

            // The connection
            connectionSource = new JdbcConnectionSource(databaseUrl);

            // Create the table
            log.debug("Creating the Tables ..");
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            // The repo
            log.debug("Creating the Repos ..");
            repoFicha = new RepositoryOrmLite<>(connectionSource, Ficha.class);
            repoPersona = new RepositoryOrmLite<>(connectionSource, Persona.class);
            repoControl = new RepositoryOrmLite<>(connectionSource, Control.class);

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     *
     */
    @Override
    public Ficha registrarPaciente(Ficha ficha) {
        throw new NotImplementedException("Not yet!");
    }

    /**
     *
     */
    @Override
    public Persona registrarPersona(Persona persona) {
        throw new NotImplementedException("Not yet!");
    }

    /**
     *
     */
    @Override
    public List<Ficha> buscarFicha(String query) {

        // The main list
        List<Ficha> fichas = new ArrayList<>();

        try {

            // Find by ficha number
            if (StringUtils.isNumeric(query)) {

                // Search by numero
                log.debug("Searching with numero ..");
                List<Ficha> fichasNumero = repoFicha.findAll("numero", query);
                if (!fichasNumero.isEmpty()) {
                    fichas.addAll(fichasNumero);
                }

                // Search by rut with foreign key
                // https://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_3.html#Join-Queries
                log.debug("Searching with rut ..");
                QueryBuilder<Persona, Long> personaQuery = repoPersona.getQuery();
                personaQuery
                        .where()
                        .like("rut", "%" + query + "%");

                List<Ficha> fichasRut = repoFicha
                        .getQuery()
                        .join(personaQuery)
                        .query();

                if (!fichasRut.isEmpty()) {
                    fichas.addAll(fichasRut);
                }
            }

            // Search by nombrePaciente with foreign key
            log.debug("Searching with nombre paciente ..");
            List<Ficha> fichaNombre = repoFicha
                    .getQuery()
                    .where()
                    .like("nombrePaciente", "%" + query + "%")
                    .query();

            if (!fichaNombre.isEmpty()) {
                fichas.addAll(fichaNombre);
            }

            log.debug("Searching with nombre duenio ..");
            QueryBuilder<Persona, Long> personaQuery = repoPersona.getQuery();
            personaQuery
                    .where()
                    .like("nombre", "%" + query + "%");

            List<Ficha> fichasNombre = repoFicha
                    .getQuery()
                    .join(personaQuery)
                    .query();

            if (!fichasNombre.isEmpty()) {
                fichas.addAll(fichasNombre);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return fichas;

    }

}
