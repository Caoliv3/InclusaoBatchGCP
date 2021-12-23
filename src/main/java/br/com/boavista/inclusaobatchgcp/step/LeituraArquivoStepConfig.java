package br.com.boavista.inclusaobatchgcp.step;

import br.com.boavista.inclusaobatchgcp.dominio.Titulo;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class LeituraArquivoStepConfig {
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step leituraArquivoStep(ItemReader<Titulo> leituraArquivoReader,
								   ItemWriter<Titulo> jdbcBatchItemWriter) {
		return stepBuilderFactory
				.get("leituraArquivoStep")
				.<Titulo, Titulo>chunk(500000)
				.reader(leituraArquivoReader)
				.writer(jdbcBatchItemWriter)
				.build();
	}
}
