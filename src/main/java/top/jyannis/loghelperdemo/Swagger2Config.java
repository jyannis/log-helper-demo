package top.jyannis.loghelperdemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


/**
 * @author Jyannis
 * @version 1.0 update on 2021/5/21
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {



    private static final String GROUP_NAME = "log-helper-demo";
    private static final String BASE_PACKAGE = "top.jyannis.loghelperdemo";
    private static final String TITLE = "log-helper-demo API Documentation";
    private static final String DESCRIPTION = "";

    @Bean
    public Docket createTestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GROUP_NAME)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                //.termsOfServiceUrl("http://localhost:8080/swagger-ui.html")//数据源
                .version("1.0")
                .build();
    }

}
