package com.enrique.server.seguridad.controllers;

import org.junit.Test;

/**
 * @author Kalana Weerarathne on 30th 06, 2019
 */
public class EvaluacionRestControllerTest {

    @Test
    public void create() {
        String x = "{\n" +
                "   \"macrocharacteristic\":{\n" +
                "      \"name\":\"Example\",\n" +
                "      \"acronym\":\"EXM\",\n" +
                "      \"value\":3,\n" +
                "      \"charateristics\":[\n" +
                "         {\n" +
                "            \"name\":\"Cumplimiento\",\n" +
                "            \"acronym\":\"CUMP\",\n" +
                "            \"value\":5,\n" +
                "            \"properties\":[\n" +
                "               {\n" +
                "                  \"name\":\"Cumplimiento Normativo de Valor y/o Formato\",\n" +
                "                  \"acronym\":\"CUMP_VAL\",\n" +
                "                  \"value\":85\n" +
                "               },\n" +
                "               {\n" +
                "                  \"name\":\"Cumplimiento Normativo Debido a la Tecnología\",\n" +
                "                  \"acronym\":\"CUMP_TEC\",\n" +
                "                  \"value\":60\n" +
                "               }\n" +
                "            ]\n" +
                "         },\n" +
                "         {\n" +
                "            \"name\":\"Confidencialidad\",\n" +
                "            \"acronym\":\"CONF\",\n" +
                "            \"value\":4,\n" +
                "            \"properties\":[\n" +
                "               {\n" +
                "                  \"name\":\"Uso de Encriptacion\",\n" +
                "                  \"acronym\":\"ENC\",\n" +
                "                  \"value\":75\n" +
                "               },\n" +
                "               {\n" +
                "                  \"name\":\"No Vulnerabilidad\",\n" +
                "                  \"acronym\":\"NO_VUL\",\n" +
                "                  \"value\":80\n" +
                "               }\n" +
                "            ]\n" +
                "         },\n" +
                "         {\n" +
                "            \"name\":\"Trazabilidad\",\n" +
                "            \"acronym\":\"TRAZ\",\n" +
                "            \"value\":3,\n" +
                "            \"properties\":[\n" +
                "               {\n" +
                "                  \"name\":\"Trazabilidad del Acceso a los Datos\",\n" +
                "                  \"acronym\":\"TRAZ_DAT\",\n" +
                "                  \"value\":50\n" +
                "               },\n" +
                "               {\n" +
                "                  \"name\":\"Trazabilidad de los Valores de Datos\",\n" +
                "                  \"acronym\":\"TRAZ_VAL\",\n" +
                "                  \"value\":60\n" +
                "               }\n" +
                "            ]\n" +
                "         },\n" +
                "         {\n" +
                "            \"name\":\"Disponibilidad\",\n" +
                "            \"acronym\":\"DISP\",\n" +
                "            \"value\":1,\n" +
                "            \"properties\":[\n" +
                "               {\n" +
                "                  \"name\":\"Ratio de Disponibilidad de Datos\",\n" +
                "                  \"acronym\":\"RAT_DISP\",\n" +
                "                  \"value\":40\n" +
                "               },\n" +
                "               {\n" +
                "                  \"name\":\"Probabilidad de Disponibilidad de Datos\",\n" +
                "                  \"acronym\":\"PROB_DISP\",\n" +
                "                  \"value\":32\n" +
                "               },\n" +
                "               {\n" +
                "                  \"name\":\"Disponibilidad de Elementos de Arquitectura\",\n" +
                "                  \"acronym\":\"DISP_ARQ\",\n" +
                "                  \"value\":10\n" +
                "               }\n" +
                "            ]\n" +
                "         },\n" +
                "         {\n" +
                "            \"name\":\"Recuperabilidad\",\n" +
                "            \"acronym\":\"RECUP\",\n" +
                "            \"value\":2,\n" +
                "            \"properties\":[\n" +
                "               {\n" +
                "                  \"name\":\"Ratio de Recuperabilidad de Datos\",\n" +
                "                  \"acronym\":\"RAT_REC\",\n" +
                "                  \"value\":35\n" +
                "               },\n" +
                "               {\n" +
                "                  \"name\":\"Backup Periódico\",\n" +
                "                  \"acronym\":\"BACK\",\n" +
                "                  \"value\":40\n" +
                "               },\n" +
                "               {\n" +
                "                  \"name\":\"Recuperabilidad de Arquitectura\",\n" +
                "                  \"acronym\":\"REC_ARQ\",\n" +
                "                  \"value\":25\n" +
                "               }\n" +
                "            ]\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}";

        x = x.replace("{\n" +
                "   \"macrocharacteristic\":", "").replace("\r", "");
        x.substring(1, x.length() - 2);

        System.out.println(x);
    }
}