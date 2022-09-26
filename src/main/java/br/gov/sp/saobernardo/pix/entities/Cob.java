package br.gov.sp.saobernardo.pix.entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import com.mifmif.common.regex.Generex;

public class Cob {
	public String geraCob(String token) {
		String payload;
		payload = "{\r\n"
				+ "  \"calendario\": {\r\n"
				+ "    \"expiracao\": 3600\r\n"
				+ "  },\r\n"
				+ "  \"devedor\": {\r\n"
				+ "    \"cpf\": \"123456789101112\",\r\n"
				+ "    \"nome\": \"Gabriel Pereira Galdino\r\n"
				+ "  },\r\n"
				+ "  \"valor\": {\r\n"
				+ "    \"original\": \"87000.00\"\r\n"
				+ "  },\r\n"
				+ "  \"chave\": \"rafael.melchioretto@tecnocomp.com.br\",\r\n" //Inserir chave da prefeitura
				+ "  \"solicitacaoPagador\": \"Informe o número ou identificador do pedido.\",\r\n"
				+ "  \"infoAdicionais\": [\r\n"
				+ "    {\r\n"
				+ "      \"nome\": \"Campo 1\",\r\n"
				+ "      \"valor\": \"Informação Adicional1 do PSP-Recebedor\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"nome\": \"Campo 2\",\r\n"
				+ "      \"valor\": \"Informação Adicional2 do PSP-Recebedor\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		

		// cobranca
		StringBuilder responseBuilder = new StringBuilder();
		HttpsURLConnection conn = null;
		
		try {
			System.setProperty("javax,net.ssl", "certs/producao-413619-pix.p12");
			String txid;
			Generex generex = new Generex("[a-zA-Z0-9]{26,35}");
			txid = generex.random();
			URL url = new URL("https://api-pix.gerencianet.com.br/v2/cob/"+txid); // Para ambiente de Desenvolvimento
			conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + token);
			
			OutputStream os = conn.getOutputStream();
			os.write(payload.getBytes());
			os.flush();

			InputStreamReader reader = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(reader);

			String response;
			System.out.println("Enviando: "+payload);
			while ((response = br.readLine()) != null) {
				responseBuilder.append(response);
			}
			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseBuilder.toString();
	}
	
	public int getIdCob(String cob) {
		int id = 0;
		try {
			JSONObject jsonObject = new JSONObject(cob);
			JSONObject loc = (JSONObject) jsonObject.getJSONObject("loc");
			id = loc.getInt("id");
		} catch (Exception e) {
			System.out.println("Erro na obtenção do id da Cobrança");
		}
		return id;
	}
	
}