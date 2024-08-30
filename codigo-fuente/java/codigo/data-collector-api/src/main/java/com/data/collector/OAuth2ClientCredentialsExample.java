package com.data.collector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Base64;

public class OAuth2ClientCredentialsExample {

    public static String getTokens(String tokenUrl, String apiKey, String apiSecret) throws ClientProtocolException, IOException {

        // Codificar las credenciales en Base64 para Authorization header
        String credentials = apiKey + ":" + apiSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        // Configurar el cliente HTTP con autenticación básica
        HttpClient httpClient = HttpClients.custom().build();

        try {
            // Configurar la solicitud POST para obtener el token
            HttpPost httpPost = new HttpPost(tokenUrl);

            // Configurar el header de Authorization con Basic y las credenciales
            // codificadas en Base64
            httpPost.setHeader("Authorization", "Basic " + encodedCredentials);

            // Configurar el Content-Type
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            // Configurar el grant type como client_credentials
            String requestBody = "grant_type=client_credentials";
            httpPost.setEntity(new org.apache.http.entity.StringEntity(requestBody));

            // Ejecutar la solicitud POST para obtener el token
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            // Manejar la respuesta del token
            if (entity != null) {
                String jsonResponse = EntityUtils.toString(entity);
                //System.out.println("Respuesta del servidor:");
                //System.out.println(jsonResponse);

                JSONObject json = new JSONObject(jsonResponse);
                String accessToken = json.getString("access_token");
                return accessToken;
            }

            return null;
        } finally {
            // Cerrar el cliente HTTP al finalizar
            //httpClient.close();
            
        }
    }
}
