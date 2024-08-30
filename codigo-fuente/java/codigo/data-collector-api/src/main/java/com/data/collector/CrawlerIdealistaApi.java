package com.data.collector;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlerIdealistaApi {

    private static final String IDEALISTA_API_URL = "https://api.idealista.com/3.5/es/search";
    private static final String FORMAT_FILE = "_%s.json";


    public static void run(String accessToken, String nameFile, String filter,String ruta, String paginaInicio) {
        System.out.println(" Inicio de la extracion de los datos de la Api");
        realizarSolicitudHTTP(accessToken, nameFile, filter,ruta,paginaInicio);
        System.out.println("Fin de la extracion de los datos de la Api");
    }

    private static void realizarSolicitudHTTP(String accessToken, String nameFile, String filter, String ruta,String paginaInicio) {

        String finalFileName = nameFile + FORMAT_FILE;

        int page = 1;
        if(paginaInicio != null && StringUtils.isNumeric(paginaInicio)){
            page = Integer.valueOf(paginaInicio);
        }
        boolean moreResults = true;
        while (moreResults) {

            System.out.println("Extracion de registros de la pagina: " + page);

            HttpClient httpClient = HttpClients.createDefault();

            String apiUrl = IDEALISTA_API_URL
                    + filter
                    + page;

            HttpPost httpPost = new HttpPost(apiUrl);

            httpPost.setHeader("Authorization", "Bearer " + accessToken);

            String jsonResponse = getResponse(httpClient, httpPost);

            System.out.println("Fin de la extracion de registros de la pagina: " + page);

            if (jsonResponse != null && !jsonResponse.isEmpty()) {

                saveData(jsonResponse, page, finalFileName,ruta);
                page++;

            } else {
                System.out.println("No hay mas datos");
                moreResults = false;
            }

        }
    }

    private static String getResponse(HttpClient httpClient, HttpPost httpPost) {
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {

                return EntityUtils.toString(entity);

            } else {
                throw new IOException("No se recibió una respuesta válida de la API");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al hacer la solicitud HTTP: " + e.getMessage());
        }
    }

    private static void saveData(String response, int page, String finalFileName, String ruta) {

        String currentDir = System.getProperty("user.dir");
        if(ruta != null){
            currentDir = ruta;
        }

       

        // Ruta del archivo donde se guardará el JSON
        String outputFile = String.format(currentDir +"/"+ finalFileName, page);

        // Escribir el JSON en el archivo
        try (FileWriter file = new FileWriter(outputFile, StandardCharsets.UTF_8)) {
            file.write(response);
            System.out.println("Objeto JSON guardado en fichero '" + outputFile + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
