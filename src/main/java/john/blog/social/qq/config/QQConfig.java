package john.blog.social.qq.config;

import john.blog.properties.BlogSecurityProperties;
import john.blog.properties.QQProperties;
import john.blog.social.qq.connection.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Configuration
@EnableSocial
@Order(2)
public class QQConfig extends SocialConfigurerAdapter {

    @Autowired
    private BlogSecurityProperties blogSecurityProperties;

    // Create Connection
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        QQProperties qqProperties = blogSecurityProperties.getQqProperties();

        QQConnectionFactory qqConnectionFactory = new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
        connectionFactoryConfigurer.addConnectionFactory(qqConnectionFactory);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }
}
