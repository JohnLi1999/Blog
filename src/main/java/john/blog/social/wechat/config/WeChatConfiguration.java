package john.blog.social.wechat.config;

import john.blog.properties.BlogSecurityProperties;
import john.blog.properties.WeChatProperties;
import john.blog.social.wechat.connection.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;

@Configuration
@EnableSocial
public class WeChatConfiguration extends SocialConfigurerAdapter {

    @Autowired
    private BlogSecurityProperties blogSecurityProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        WeChatProperties weChatProperties = blogSecurityProperties.getWeChatProperties();
        WeChatConnectionFactory weChatConnectionFactory = new WeChatConnectionFactory(
            weChatProperties.getProviderId(),
            weChatProperties.getAppId(),
            weChatProperties.getAppSecret()
        );
        // Add WeChat ConnectionFactory into Configuration
        connectionFactoryConfigurer.addConnectionFactory(weChatConnectionFactory);
    }
}
