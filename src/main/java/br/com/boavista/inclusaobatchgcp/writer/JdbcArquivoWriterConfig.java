package br.com.boavista.inclusaobatchgcp.writer;

import br.com.boavista.inclusaobatchgcp.dominio.Titulo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Configuration
public class JdbcArquivoWriterConfig {

//	@Bean
//	public JdbcBatchItemWriter<Titulo> jdbcBatchItemWriter(DataSource dataSource) {
//		return new JdbcBatchItemWriterBuilder<Titulo>()
//				.dataSource(dataSource)
//				.sql("insert into protesto(id_auditoria, id_cartorio," +
//						"documento, tipo_documento, data_inclusao, hora_inclusao, data_inativacao, hora_inativacao, nome_devedor," +
//						" endereco_devedor, cep_devedor, bairro_devedor, cidade_devedor, uf_devedor, livro_protesto, folha_protesto, protocolo," +
//						" data_protocolo, especie, numero_titulo, data_emissao, data_vencimento, data_protesto, valor_original," +
//						" valor_protestado)" +
//						" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
//				.itemPreparedStatementSetter(itemPreparedStatementsetter())
//				.build();
//	}

	public JdbcBatchItemWriter<Titulo> jdbcBatchItemWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Titulo>()
				.dataSource(dataSource)
				.sql("update protesto set uf_devedor = 'SP' " +
					" where documento = ? " +
					"   and tipo_documento = ? " +
					"   and  uf_devedor is null ")
				.itemPreparedStatementSetter(itemPreparedStatementsetter())
				.build();
	}

	private ItemPreparedStatementSetter<Titulo> itemPreparedStatementsetter() {

		return new ItemPreparedStatementSetter<Titulo>() {
			@Override
			public void setValues(Titulo titulo, PreparedStatement ps) throws SQLException {

				ps.setString(1, titulo.getDocumento());
				ps.setInt(2, Integer.valueOf(titulo.getTipoDocumento()));

			}
		};
	}

//	private ItemPreparedStatementSetter<Titulo> itemPreparedStatementsetter() {
//
//		return new ItemPreparedStatementSetter<Titulo>() {
//			@Override
//			public void setValues(Titulo titulo, PreparedStatement ps) throws SQLException {
//
//				ps.setLong(1, 126866);
//				ps.setString(2,  titulo.getCodCartorio());
//				ps.setString(3, titulo.getDocumento());
//				ps.setInt(4, Integer.valueOf(titulo.getTipoDocumento()));
//				ps.setString(5, dataCorrente());
//				ps.setString(6, horaCorrente());
//				ps.setString(7, null);
//				ps.setString(8, null);
//				ps.setString(9, null);
//				ps.setString(10, null);
//				ps.setString(11, null);
//				ps.setString(12, null);
//				ps.setString(13, null);
//				ps.setString(14, null);
//				ps.setString(15, null);
//				ps.setString(16, null);
//				ps.setString(17, null);
//				ps.setString(18, null);
//				ps.setString(19, titulo.getEspecie());
//				ps.setString(20, null);
//				ps.setString(21, null);
//				ps.setString(22, formataData(titulo.getDataVencimento()));
//				ps.setString(23, formataData(titulo.getDataProtesto()));
//				ps.setString(24, titulo.getValorProtestado());
//				ps.setString(25, titulo.getValorProtestado());
//			}
//		};
//	}

	public String getId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public  String formataData(String d) {

		SimpleDateFormat formatoOrigem = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = formatoOrigem.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat formatoDestino = new SimpleDateFormat("yyyy-MM-dd");
		String formatoAnoMesDia = formatoDestino.format(data);

		return formatoAnoMesDia;
	}

	public String dataCorrente() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new Date();
		String formatoAnoMesDia = sdf.format(data);
		return formatoAnoMesDia;
	}

	public String horaCorrente() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String hora = sdf.format(new Date());
		return hora;
	}


}
