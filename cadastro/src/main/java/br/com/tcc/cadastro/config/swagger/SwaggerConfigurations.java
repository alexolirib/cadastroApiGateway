package br.com.tcc.cadastro.config.swagger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations  extends WebMvcConfigurationSupport {

    @Value("${contexto.aplicacao.config}")
    private String contexAppConfig;

    @Bean
    public Docket api() {
        //tipo de documentacao
        if (contexAppConfig.equals("*")){
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    //path para documentação
                    .apis(RequestHandlerSelectors.basePackage("br.com.tcc.cadastro"))
                    //gerar com tudo do projeto - url raiz
                .paths(PathSelectors.ant("/**"))
                    .build()
                    .tags(
                            new Tag("categoria", "Endpoints relacionado com categoria"),
                            new Tag("produto", "Endpoints relacionado com produto"),
                            new Tag("fornecedor", "Endpoints relacionado com fornecedor")
                    );
        }
        else {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    //path para documentação
                    .apis(RequestHandlerSelectors.basePackage("br.com.tcc.cadastro"))
                    .paths(regex("/api/v1/"+contexAppConfig+".*"))
                    .build()
                    .tags(new Tag(contexAppConfig, "Endpoints relacionado com"+contexAppConfig)
                    );
        }
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        if (contexAppConfig.equals("*")) {
            registry.addRedirectViewController("/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
            registry.addRedirectViewController("/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
            registry.addRedirectViewController("/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
            registry.addRedirectViewController("/swagger-resources", "/swagger-resources");
        }else{
            registry.addRedirectViewController("/api/v1/"+contexAppConfig+"/docs/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
            registry.addRedirectViewController("/api/v1/"+contexAppConfig+"/docs/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
            registry.addRedirectViewController("/api/v1/"+contexAppConfig+"/docs/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
            registry.addRedirectViewController("/api/v1/"+contexAppConfig+"/docs/swagger-resources", "/swagger-resources");
        }
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (contexAppConfig.equals("*")) {
            registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
        }else{
            registry.addResourceHandler("/api/v1/"+contexAppConfig+"/docs/**").addResourceLocations("classpath:/META-INF/resources/");
        }
    }
}
