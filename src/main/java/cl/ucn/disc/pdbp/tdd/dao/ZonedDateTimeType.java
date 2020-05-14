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

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * ZonedDateTime to String.
 *
 * @author Diego Urrutia-Astorga.
 */
@SuppressWarnings({"StaticVariableOfConcreteClass", "Singleton"})
public final class ZonedDateTimeType extends BaseDataType {

    /**
     * The formatter (2011-12-03T10:15:30+01:00)
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    /**
     * The Singleton.
     */
    public static final ZonedDateTimeType SINGLETON = new ZonedDateTimeType();

    /**
     * @return the singleton.
     */
    public static ZonedDateTimeType getSingleton() {
        return SINGLETON;
    }

    /**
     * The Constructor.
     */
    private ZonedDateTimeType() {
        // ZonedDateTime <-> String
        super(SqlType.STRING, new Class<?>[]{ZonedDateTime.class});
    }

    /**
     * @see BaseDataType#getDefaultWidth()
     */
    @Override
    public int getDefaultWidth() {
        // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html
        // Size of string: VARCHAR(64)
        return 64;
    }

    /**
     * @see BaseDataType#sqlArgToJava(FieldType, Object, int)
     */
    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) {

        // Nullity test
        if (sqlArg == null) {
            return null;
        }

        // String to ZonedDateTime
        return ZonedDateTime.parse((CharSequence) sqlArg, FORMATTER);
    }

    /**
     * @see BaseDataType#parseDefaultString(FieldType, String)
     */
    @Override
    public Object parseDefaultString(FieldType fieldType, String defaultStr) {
        return this.sqlArgToJava(fieldType, defaultStr, 0);
    }

    /**
     * @see BaseDataType#javaToSqlArg(FieldType, Object)
     */
    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) {

        // Nullity test
        if (javaObject == null) {
            return null;
        }

        // ZonedDateTime to String
        return FORMATTER.format((TemporalAccessor) javaObject);
    }

    /**
     * @see BaseDataType#resultToSqlArg(FieldType, DatabaseResults, int)
     */
    @Override
    public Object resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
        return results.getString(columnPos);
    }

}
