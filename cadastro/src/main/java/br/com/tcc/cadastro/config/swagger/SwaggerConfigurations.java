package br.com.tcc.cadastro.config.swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

    @Bean
    public Docket api() {
        //tipo de documentacao
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //path para documentação
                .apis(RequestHandlerSelectors.basePackage("br.com.tcc.cadastro"))
                //gerar com tudo do projeto - url raiz
                .paths(PathSelectors.ant("/**"))
                .build();
    }

}
