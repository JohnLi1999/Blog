package john.blog.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
// Enable the Configuration Properties
@EnableConfigurationProperties(BlogSecurityProperties.class)
public class BlogSecurityConfig {

}
