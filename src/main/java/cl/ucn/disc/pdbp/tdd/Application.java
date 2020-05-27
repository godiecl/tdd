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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.json.JavalinJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * The Main Application.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class Application {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * Private constructor.
     */
    private Application() {
        // Nothing.
    }

    /**
     * The Main entry.
     *
     * @param args from console.
     */
    public static void main(String[] args) {

        // The Contratos
        log.debug("Building Contratos ..");
        // Contratos contratos = new ContratosImpl("jdbc:h2:mem:");
        // Contratos contratos = new ContratosImpl("jdbc:sqlite::memory:");
        Contratos contratos = new ContratosImpl("jdbc:sqlite:fivet.db");

        // Populate the db
        log.debug("Populating the Database ..");
        {
            Persona persona = new Persona(
                    "130144918",
                    "Diego",
                    "Urrutia-Astorga",
                    "Angamos #0610",
                    null,
                    56983920265L,
                    "durrutia@ucn.cl"
            );

            try {
                contratos.registrarPersona(persona);
            } catch (RuntimeException ex) {
                log.warn("Can't insert persona", ex);
            }
        }

        // Gson configuration
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        JavalinJson.setFromJsonMapper(gson::fromJson);
        JavalinJson.setToJsonMapper(gson::toJson);

        // The Javalin application
        log.debug("Starting Javalin ..");
        Javalin javalin = Javalin.create(config -> {

            // enable extensive development logging for http and websocket
            config.enableDevLogging();

            // Measure the time
            config.requestLogger(((ctx, executionTimeMs) -> {
                log.info("Served {} in {} ms.", ctx.fullUrl(), executionTimeMs);
                ctx.header("Server-Timing", "total;dur=" + executionTimeMs);
            }));

            // Enable routes helper
            config.registerPlugin(new RouteOverviewPlugin("/routes"));

            // Dev logging
            // config.enableDevLogging();

        }).start(7000);

        // Shutdown hook!
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.debug("Stopping the server ..");
            javalin.stop();
            log.debug("The end.");
        }));

        // A simple route to show time
        javalin.get("/", ctx -> {

            // Show the date.
            ctx.result("The Date: " + ZonedDateTime.now());

        });

        // Get the fichas
        javalin.get("/fichas/", ctx -> {

            List<Ficha> fichas = contratos.getAllFichas();
            ctx.json(fichas);

        });

        // Get the fichas
        javalin.get("/fichas/find/:query", ctx -> {
            String query = ctx.pathParam("query");
            log.debug("Query: <{}>", query);

            List<Ficha> fichas = contratos.buscarFicha(query);
            ctx.json(fichas);

        });

        // Get the fichas
        javalin.get("/personas/rut/:rut", ctx -> {
            String rut = ctx.pathParam("rut");
            log.debug("RUT: <{}>", rut);

            Persona persona = contratos.findByRut(rut);
            if (persona == null) {
                throw new NotFoundResponse("Persona Not Found");
            } else {
                ctx.json(persona);
            }

        });

    }

}
