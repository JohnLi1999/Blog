package john.blog.social.qq.config;

import john.blog.properties.BlogSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.*;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
@Order(1)
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BlogSecurityProperties blogSecurityProperties;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    // Add BlogConnectionSignUp into configuration
    @Autowired
    private ConnectionSignUp connectionSignUp;

    // Save QQ user information into database
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        // Set our own way of connection sign up
        repository.setConnectionSignUp(connectionSignUp);
        return repository;
    }

    @Bean
    public SpringSocialConfigurer blogSocialSecurityConfig() {
        // Change filterProcessesUrl from "/auth" to "/qqLogin"
        String filterProcessesUrl = blogSecurityProperties.getQqProperties().getFilterProcessesUrl();
        BlogSpringSocialConfigurer configurer = new BlogSpringSocialConfigurer(filterProcessesUrl);
        return configurer;
    }

    /* 1. Get information of Spring Social during registration process
     * 2. Parse the userId to Spring Social                             */
    @Bean
    public ProviderSignInUtils providerSignInUtils() {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
