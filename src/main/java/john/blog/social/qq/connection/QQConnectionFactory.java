package john.blog.social.qq.connection;

import john.blog.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    /**
     * Constructor of the ConnectionFactory
     *
     * @param providerId the provider id e.g. "QQ"
     * @param appId      appId parameter for QQServiceProvider
     * @param appSecret  appSecret parameter for QQServiceProvider
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
