package br.com.boavista.inclusaobatchgcp.dominio;

import lombok.Data;


@Data
public class Titulo  {


	private String tipoDocumento;
	private String documento;
	private String dataProtesto;
	private String codCartorio;
	private String dataVencimento;
	private String valorProtestado;
	private String especie;
}

