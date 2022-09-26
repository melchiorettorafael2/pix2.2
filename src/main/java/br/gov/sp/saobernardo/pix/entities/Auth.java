package br.gov.sp.saobernardo.pix.entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.json.JSONObject;

public class Auth {
	 public final String client_id = "Client_Id_9274d6b1bd5b4360dd1b21b519c7b57597b02a9f";
     public final String client_secret = "Client_Secret_17b929c0731fd7ec2172ef7a6aad4822d01d60c7";
     public final String basicAuth = Base64.getEncoder().encodeToString(((client_id+':'+client_secret).getBytes()));
     
public String geraToken() { 
	String token ="";
	try {
    //Diretório em que seu certificado em formato .p12 deve ser inserido
     System.setProperty("javax.net.ssl.keyStore", "certs/producao-413619-pix.p12"); 
     SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
     
     URL url = new URL ("https://api-pix.gerencianet.com.br/oauth/token"); //Para ambiente de Desenvolvimento              
     HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
     conn.setDoOutput(true);
     conn.setRequestMethod("POST");
     conn.setRequestProperty("Content-Type", "application/json");
     conn.setRequestProperty("Authorization", "Basic "+ basicAuth);
     conn.setSSLSocketFactory(sslsocketfactory);
     String input = "{\"grant_type\": \"client_credentials\"}";
    
     OutputStream os = conn.getOutputStream();
     os.write(input.getBytes());
     os.flush();     

     InputStreamReader reader = new InputStreamReader(conn.getInputStream());
     BufferedReader br = new BufferedReader(reader);

     String response;
     StringBuilder responseBuilder = new StringBuilder();
     while ((response = br.readLine()) != null) {
       System.out.println(response);
       responseBuilder.append(response);
     }
     try {
    	 JSONObject jsonObject = new JSONObject(responseBuilder.toString());
    	 token= jsonObject.getString("access_token");
     }catch(Exception e){
    	 e.printStackTrace();	
     }
     conn.disconnect();
	} catch(Exception e){
		e.printStackTrace();
	}
	
	return token;
}
}