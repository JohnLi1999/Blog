package john.blog.social.qq.connection;

import john.blog.social.qq.api.QQ;
import john.blog.social.qq.api.QQImpl;
import john.blog.social.qq.template.QQOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    // URL that directs the user to Authorization Server
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    // URL to get accessToken
    private static final String URL_ACCESS_TOKEN= "https://graph.qq.com/oauth2.0/token";

    private String appId;

    // Step 1 ~ 6
    public QQServiceProvider(String appId, String appSecret) {
        /* Use OAuth2Template
            clientId - appId,
            clientSecret - appSecret,
            authorizeUrl - url that directs the user to Authorization Server
            accessTokenUrl - url to get accessToken
         */
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    // Step 7 ~ 8
    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
