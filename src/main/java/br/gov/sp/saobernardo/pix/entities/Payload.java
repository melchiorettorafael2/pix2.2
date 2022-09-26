package br.gov.sp.saobernardo.pix.entities;

import java.util.Objects;

public class Payload {
	private String chavePix;
	private String nomeDevedor;
	private String inscImobiliaria;
	private String  valor;
	private String dataExpiracao;
	private String infoAdicional;
	private String txId;
	
	public Payload() {
		
	}

	public Payload(String chavePix, String nomeDevedor, String inscImobiliaria, String valor, String dataExpiracao,
			String infoAdicional, String txId) {
		super();
		this.chavePix = chavePix;
		this.nomeDevedor = nomeDevedor;
		this.inscImobiliaria = inscImobiliaria;
		this.valor = valor;
		this.dataExpiracao = dataExpiracao;
		this.infoAdicional = infoAdicional;
		this.txId = txId;
	}

	public String getChavePix() {
		return chavePix;
	}

	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}

	public String getNomeDevedor() {
		return nomeDevedor;
	}

	public void setNomeDevedor(String nomeDevedor) {
		this.nomeDevedor = nomeDevedor;
	}

	public String getInscImobiliaria() {
		return inscImobiliaria;
	}

	public void setInscImobiliaria(String inscImobiliaria) {
		this.inscImobiliaria = inscImobiliaria;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(String dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	
	
}
