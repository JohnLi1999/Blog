package john.blog.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "blog.security")
public class BlogSecurityProperties {

    private QQProperties qqProperties = new QQProperties();
    private WeChatProperties weChatProperties = new WeChatProperties();

    public QQProperties getQqProperties() {
        return qqProperties;
    }

    public void setQqProperties(QQProperties qqProperties) {
        this.qqProperties = qqProperties;
    }

    public WeChatProperties getWeChatProperties() {
        return weChatProperties;
    }

    public void setWeChatProperties(WeChatProperties weChatProperties) {
        this.weChatProperties = weChatProperties;
    }
}
