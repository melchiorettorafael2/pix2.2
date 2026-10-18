package br.gov.sp.saobernardo.pix.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Lê as configurações do application.properties.
 * Cada chave pode ser sobrescrita por variável de ambiente
 * (ex.: pix.client-id -> PIX_CLIENT_ID).
 */
public final class PixConfig {

	private static final Properties props = new Properties();

	static {
		try (InputStream in = PixConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
			if (in != null) {
				props.load(in);
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar application.properties");
			e.printStackTrace();
		}
	}

	private PixConfig() {
	}

	public static String get(String chave) {
		String env = System.getenv(chave.toUpperCase().replace('.', '_').replace('-', '_'));
		String valor = (env != null && !env.isEmpty()) ? env : props.getProperty(chave);
		if (valor == null || valor.isEmpty()) {
			throw new IllegalStateException("Configuração ausente: " + chave);
		}
		return valor;
	}

	public static String clientId() {
		return get("pix.client-id");
	}

	public static String clientSecret() {
		return get("pix.client-secret");
	}

	public static String certificado() {
		return get("pix.certificado");
	}

	public static String apiUrl() {
		return get("pix.api-url");
	}

	public static String chave() {
		return get("pix.chave");
	}
}
