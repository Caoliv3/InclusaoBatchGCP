package br.com.boavista.inclusaobatchgcp.reader;

import br.com.boavista.inclusaobatchgcp.dominio.Titulo;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoDelimitadoReaderConfig {

    @Value("${service.apiprotestos.arquivo}")
    private Resource arquivoAS400;

//    @StepScope
    @Bean
    public FlatFileItemReader<Titulo> leituraArquivoReader(){
//            @Value("#{jobParameters['arquivoAS400']}") Resource arquivoAS400) {

        return new FlatFileItemReaderBuilder<Titulo>()
                .name("leituraArquivoReader")
                .resource(arquivoAS400)
                .delimited().delimiter(";")
                .names(new String[]{"tipoDocumento", "documento", "dataProtesto", "codCartorio", "dataVencimento", "valorProtestado", "especie"})
                .targetType(Titulo.class)
                .build();
    }
}
