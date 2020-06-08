package john.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /** Handler for image path matching */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /* Let SpringBoot know we have the "upload" folder */

        // Using classpath (it will automatically find /static/upload/ folder under /out/production path)
        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/static/upload/");

        // Using local path (add "file:" at the beginning)
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:/Users/zhiwenli/Idea_Projects/Blog/out/production/resources/static/upload/");
    }

    /** Enlarge Session Scope */
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}


