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

package cl.ucn.disc.pdbp.utils;

import java.util.regex.Pattern;

/**
 * The Validations.
 *
 * @author Diego Urrutia-Astorga.
 */
@SuppressWarnings({"UtilityClass", "UtilityClassCanBeEnum"})
public final class Validation {

    /**
     * Not for construction.
     */
    private Validation() {
        // Empty
    }

    /**
     * @param rut to validate.
     * @return true is rut is valid.
     */
    @SuppressWarnings("MethodWithMultipleReturnPoints")
    public static boolean isRutValid(String rut) {

        // Not null
        if (rut == null) {
            return false;
        }

        // Wrong size
        if (rut.length() < 2) {
            return false;
        }

        // Last char
        char dv = rut.charAt(rut.length() - 1);

        // Only numbers
        String numbers = rut.substring(0, rut.length() - 1);
        if (!Pattern.matches("[0-9]+", numbers)) {
            return false;
        }

        // The validation
        return validarRut(Integer.parseInt(numbers), dv);

    }

    /**
     * http://www.creations.cl/2009/01/generador-de-rut-y-validador/
     *
     * @return true is rut is valid.
     */
    @SuppressWarnings({"ValueOfIncrementOrDecrementUsed", "BooleanMethodNameMustStartWithQuestion", "OverlyComplexArithmeticExpression", "AssignmentToMethodParameter", "ForLoopWithMissingComponent", "StandardVariableNames"})
    private static boolean validarRut(int rut, char dv) {
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 75);
    }

}
