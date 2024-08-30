package com.data.collector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;

public class Main {

    public static Properties loadProperties(String path) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(path)) {
            if (input == null) {
                System.out.println("No se pudo encontrar el archivo de configuración en la ruta: " + path);
                return null;
            }
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Eror necesitas fichero properties no encontrado");
            System.exit(1);
        }

        String configPath = args[0];
        Properties config = loadProperties(configPath);
        if (config != null) {

            String tokenUrl = config.getProperty("tokenUrl");
            String apiKey = config.getProperty("apiKey");
            String apiSecret = config.getProperty("apiSecret");
            String filter = config.getProperty("filter");
            String nameFile = config.getProperty("nameFile");
            String ruta = config.getProperty("ruta");
            String paginaInicio = config.getProperty("paginaInicio");

            try {

                String tokens = OAuth2ClientCredentialsExample.getTokens(tokenUrl, apiKey, apiSecret);
                CrawlerIdealistaApi crawlerIdealistaApi = new CrawlerIdealistaApi();
                crawlerIdealistaApi.run(tokens, nameFile, filter,ruta,paginaInicio);

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}