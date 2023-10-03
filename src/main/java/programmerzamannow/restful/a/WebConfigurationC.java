package programmerzamannow.restful.a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import programmerzamannow.restful.a.resolver.UserArgumentResolverC;
import programmerzamannow.restful.resolver.UserArgumentResolver;

import java.util.List;

@Configuration
public class WebConfigurationC implements WebMvcConfigurer {

    @Autowired
    private UserArgumentResolverC userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(userArgumentResolver);
    }
}
