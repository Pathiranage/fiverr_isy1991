package com.enrique.server.seguridad;

//
//
//import com.javadocx.CreateDocx;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import com.enrique.server.seguridad.models.entity.*;
//
///**
// * Clase que se encarga de crear el informe.
// * @author JOviedo
// *
// */
///**
public class Informe {
    //
//    /** Proyecto generar informe. */
//    private Evaluacion evaluacion;
//    /** Datos de la macrocaracteristica. */
//    
//    /*
//    private List<Medida> caracteristica;
//    /* Datos de las subcaracteristicas. */
//   // private List<Medida> subcaracteristica;
//    /** Datos de las propiedad. */
//   // private List<ArrayList<Medida>> propiedades;*/
//
//    /**
//    * Constructor de la clase.
//    * @param proyecto .
//    * @param macrocaracteristica .
//    * @param subcaracteristica .
//    * @param propiedades .
//    */
//    public Informe(final Proyecto proyecto, final List<Medida> caracteristica,
//        final List<Medida> subcaracteristica,
//        final List<ArrayList<Medida>> propiedades) {
//
//        this.proyecto = proyecto;
//        this.caracteristica = caracteristica;
//        this.subcaracteristica = subcaracteristica;
//        this.propiedades = propiedades;
//    }
//
//    /**
//     * Genera el informe.
//     */
    public final void generarInformes() {
        System.out.println("llego");
        //crearGraficos();
        // crearInforme();
    }
//
//    /**
//     * Este metodo crea un documento con los graficos de la evaluaci�n.
//     */
//    public final void crearGraficos() {
//        CreateDocx objDocx = new CreateDocx(".docx");
//        objDocx.addGraphic(crearGrafico(encodeString("Subcaracteristicas"),
//            this.subcaracteristica));
//        for (int i = 0; i < this.subcaracteristica.size(); i++) {
//            objDocx.addGraphic(crearGrafico(encodeString("Propiedades de "
//                + this.subcaracteristica.get(i).getAtributo()),
//                this.propiedades.get(i)));
//        }
//        /*objDocx.createDocx("C:\\AQCLab\\AQCLab-web\\trunk\\Graficos",
//            new HashMap());*/
//        objDocx.createDocx("webapps/aqclabweb/aqclabweb/Graficos",
//            new HashMap());
//    }
//
//    /**
//     * Este metodo genera graficos de barras con los resultados de
//     * la evaluacion.
//     * @param titulo titulo del grafico.
//     * @param resultado Lista con las medidas.
//     * @return hashMap con la informacion grafico.
//     */
//    private HashMap<String, Object> crearGrafico(final String titulo,
//        final List<Medida> resultado) {
//
//        HashMap data = new HashMap();
//        List<Double> value;
//        for (int i = 0; i < resultado.size(); i++) {
//            value = new ArrayList<Double>();
//            value.add(resultado.get(i).getValor());
//            data.put(encodeString(resultado.get(i).getAcronimo()), value);
//        }
//        ArrayList<String> leyenda = new ArrayList<String>();
//        leyenda.add(titulo);
//        data.put("legend", leyenda);
//        HashMap<String, Object> grafico = new HashMap<String, Object>();
//        grafico.put("data", data);
//        grafico.put("type", "col3DChart");
//        grafico.put("title", titulo);
//        grafico.put("sizeX", "13");
//        grafico.put("sizeY", "10");
//        grafico.put("jc", "center");
//        grafico.put("color", "2");
//        grafico.put("cornerX", "20");
//        grafico.put("cornerY", "20");
//        return grafico;
//    }
//
//    /**
//     * Este metodo genera el informe de evaluacion.
//     */
//    public final void crearInforme() {
//        CreateDocx objDocx = new CreateDocx(".docx");
//        objDocx.setLanguage("es-ES");
//        if(caracteristica.get(0).getAtributo().equalsIgnoreCase(Constante.CARACTERISTICA[0])) {
//        	objDocx.addTemplate("webapps/aqclabweb/F014.1_InformeEvaluacion_Id_AQCLab.docx");
//        	//objDocx.addTemplate(".//F014.1_InformeEvaluacion_Id_AQCLab.docx");
//        }
//        else {
//        	objDocx.addTemplate("webapps/aqclabweb/F014.2_InformeEvaluacion_Id_AQCLab.docx");
//        	//objDocx.addTemplate(".//F014.2_InformeEvaluacion_Id_AQCLab.docx");
//        }
//        objDocx.addTemplateVariable("NOMBRE",
//            encodeString(this.proyecto.getNombre()));
//        objDocx.addTemplateVariable("VERSION",
//            encodeString(this.proyecto.getVersion()));
//        objDocx.addTemplateVariable("V_CARACTERISTICA",
//            encodeString(this.caracteristica.get(0).getValor() + ""));
//        objDocx.addTemplateVariable(crearTablaResumen(this.subcaracteristica),
//            "table");
//        objDocx.addTemplateVariable(crearSubcaract(this.subcaracteristica),
//            "table");
//        for (int i = 0; i < this.propiedades.size(); i++) {
//            objDocx.addTemplateVariable(crearPropiedad(
//                this.propiedades.get(i), i), "table");
//        }
//        //objDocx.createDocx("C:\\AQCLab\\AQCLab-web\\trunk\\Informe", new HashMap());
//        objDocx.createDocx("webapps/aqclabweb/aqclabweb/Informe", new HashMap());
//    }
//
//    /**
//     * Este metodo rellena la tabla subcaracteristicas del resumen.
//     * @param listaAtributos es la lista con las mediciones que se introducen
//     * en la tabla.
//     * @return HashMap con los valores formateados para aparecer en la tabla
//     * del informe.
//     */
//    private HashMap<String, List<String>> crearTablaResumen(
//        final List<Medida> listaAtributos) {
//        HashMap<String, List<String>> values =
//            new HashMap<String, List<String>>();
//        List<ArrayList<String>> datos = obtenerDatos(listaAtributos);
//        values.put("ATRIBUTO", datos.get(0));
//        values.put("ACRONIMO", datos.get(1));
//        values.put("VALOR", datos.get(2));
//        return values;
//    }
//
//    /**
//     * Este metodo rellena la tabla subcaracteristicas del informe.
//     * @param listaAtributos es la lista con las mediciones que se introducen
//     * en la tabla.
//     * @return HashMap con los valores formateados para aparecer en la tabla
//     * del informe.
//     */
//    private HashMap<String, List<String>> crearSubcaract(
//        final List<Medida> listaAtributos) {
//        HashMap<String, List<String>> values =
//            new HashMap<String, List<String>>();
//        List<ArrayList<String>> datos = obtenerDatos(listaAtributos);
//        values.put("SUBCARACTERISTICA", datos.get(0));
//        values.put("ACRONIMO1", datos.get(1));
//        values.put("VALOR1", datos.get(2));
//        return values;
//    }
//
//    /** Este metodo sirve para conocer el numero de Propiedades que se deben
//     * crear.
//     * @param listaAtributos es la lista con las mediciones que se introducen
//     * en la tabla.
//     * @param contador n�mero de propiedad a crear.
//     * @return HashMap con los valores formateados para aparecer en la tabla
//     * del informe
//     */
//    private HashMap<String, List<String>> crearPropiedad(
//        final List<Medida> listaAtributos, final int contador) {
//        HashMap<String, List<String>> values =
//                new HashMap<String, List<String>>();
//       switch(contador) {
//            case 0:
//                values = crearPropiedad1(listaAtributos);
//                break;
//            case 1:
//                values = crearPropiedad2(listaAtributos);
//                break;
//            case 2:
//                values = crearPropiedad3(listaAtributos);
//                break;
//            case 3:
//                values = crearPropiedad4(listaAtributos);
//                break;
//            case 4:
//                values = crearPropiedad5(listaAtributos);
//                break;
//            default:
//                break;
//        }
//        return values;
//    }
//
//    /**
//     * Este metodo crea la Propiedad 1.
//     * @param listaAtributos es la lista con las mediciones que se introducen
//     * en la tabla.
//     * @return HashMap con los valores formateados para aparecer en la tabla
//     * del informe.
//     */
//    private HashMap<String, List<String>> crearPropiedad1(
//        final List<Medida> listaAtributos) {
//        HashMap<String, List<String>> values =
//            new HashMap<String, List<String>>();
//        List<ArrayList<String>> datos = obtenerDatos(listaAtributos);
//        values.put("PROPIEDAD1", datos.get(0));
//        values.put("PROP1", datos.get(1));
//        values.put("V_PROP1", datos.get(2));
//        return values;
//    }
//
//    /**
//     * Este metodo crea la Propiedad 2.
//     * @param listaAtributos es la lista con las mediciones que se introducen
//     * en la tabla.
//     * @return HashMap con los valores formateados para aparecer en la tabla
//     * del informe.
//     */
//    private HashMap<String, List<String>> crearPropiedad2(
//        final List<Medida> listaAtributos) {
//        HashMap<String, List<String>> values =
//            new HashMap<String, List<String>>();
//        List<ArrayList<String>> datos = obtenerDatos(listaAtributos);
//        values.put("PROPIEDAD2", datos.get(0));
//        values.put("PROP2", datos.get(1));
//        values.put("V_PROP2", datos.get(2));
//        return values;
//    }
//
//    /**
//     * Este metodo crea la Propiedad 3.
//     * @param listaAtributos es la lista con las mediciones que se introducen
//     * en la tabla.
//     * @return HashMap con los valores formateados para aparecer en la tabla
//     * del informe.
//     */
//    private HashMap<String, List<String>> crearPropiedad3(
//        final List<Medida> listaAtributos) {
//        HashMap<String, List<String>> values =
//            new HashMap<String, List<String>>();
//        List<ArrayList<String>> datos = obtenerDatos(listaAtributos);
//        values.put("PROPIEDAD3", datos.get(0));
//        values.put("PROP3", datos.get(1));
//        values.put("V_PROP3", datos.get(2));
//        return values;
//    }
//
//    /**
//     * Este metodo crea la Propiedad 4.
//     * @param listaAtributos es la lista con las mediciones que se introducen
//     * en la tabla.
//     * @return HashMap con los valores formateados para aparecer en la tabla
//     * del informe.
//     */
//    private HashMap<String, List<String>> crearPropiedad4(
//        final List<Medida> listaAtributos) {
//        HashMap<String, List<String>> values =
//            new HashMap<String, List<String>>();
//        List<ArrayList<String>> datos = obtenerDatos(listaAtributos);
//        values.put("PROPIEDAD4", datos.get(0));
//        values.put("PROP4", datos.get(1));
//        values.put("V_PROP4", datos.get(2));
//        return values;
//    }
//
//    /**
//     * Este metodo crea la Propiedad 5.
//     * @param listaAtributos es la lista con las mediciones que se introducen
//     * en la tabla.
//     * @return HashMap con los valores formateados para aparecer en la tabla
//     * del informe.
//     */
//    private HashMap<String, List<String>> crearPropiedad5(
//        final List<Medida> listaAtributos) {
//        HashMap<String, List<String>> values =
//            new HashMap<String, List<String>>();
//        List<ArrayList<String>> datos = obtenerDatos(listaAtributos);
//        values.put("PROPIEDAD5", datos.get(0));
//        values.put("PROP5", datos.get(1));
//        values.put("V_PROP5", datos.get(2));
//        return values;
//    }
//
//    /** Este metodo formatea los datos para mostrarlo en las tablas del informe.
//     * @param listaAtributos es la lista de Medidas que se desean mostrar.
//     * @return List<ArrayList> cada ArrayList es una columna de la tabla.
//     */
//    private List<ArrayList<String>> obtenerDatos(
//        final List<Medida> listaAtributos) {
//        List<ArrayList<String>> resultado = new ArrayList<ArrayList<String>>();
//        ArrayList<String> atributo = new ArrayList<String>();
//        ArrayList<String> acronimo = new ArrayList<String>();
//        ArrayList<String> valor = new ArrayList<String>();
//        for (int i = 0; i < listaAtributos.size(); i++) {
//            atributo.add(encodeString(listaAtributos.get(i).getAtributo()));
//            acronimo.add(encodeString(listaAtributos.get(i).getAcronimo()));
//            valor.add(encodeString(listaAtributos.get(i).getValor() + ""));
//        }
//        resultado.add(atributo);
//        resultado.add(acronimo);
//        resultado.add(valor);
//        return resultado;
//    }
//
//    /**
//     * Transforma el formato de la cadena.
//     * @param s .
//     * @return .
//     */
//    private String encodeString(final String s) {
//        byte[] b;
//        String sEncoded = null;
//        try {
//            b = s.getBytes("UTF-8");
//            sEncoded = new String(b, "ISO-8859-1");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return sEncoded;
//    }
}
//
