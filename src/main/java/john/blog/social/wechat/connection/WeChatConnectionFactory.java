package john.blog.social.wechat.connection;

import john.blog.social.wechat.api.WeChat;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChat> {

    public WeChatConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeChatServiceProvider(appId, appSecret), new WeChatAdapter());
    }

    // Create connection for WeChat
    @Override
    public Connection<WeChat> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<>(
            getProviderId(),
            extractProviderUserId(accessGrant),
            accessGrant.getAccessToken(),
            accessGrant.getRefreshToken(),
            accessGrant.getExpireTime(),
            getOAuth2ServiceProvider(),
            getApiAdapter(extractProviderUserId(accessGrant))
        );
    }

    // Create connection for WeChat
    @Override
    public Connection<WeChat> createConnection(ConnectionData data) {
        return new OAuth2Connection<>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }


    /** Helper Functions */

    // Get openId (providerUserId)
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        // Check if accessGrant is an instance of WeChatAccessGrant
        if (accessGrant instanceof WeChatAccessGrant) {
            // If it is, return the openId inside
            return ((WeChatAccessGrant) accessGrant).getOpenId();
        }
        // If not, return null
        return null;
    }

    // Get ServiceProvider
    public OAuth2ServiceProvider<WeChat> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider) getServiceProvider();
    }

    // Get ApiAdapter
    public ApiAdapter<WeChat> getApiAdapter(String providerUserId) {
        // Every user has his/her own WeChatAdapter
        return new WeChatAdapter(providerUserId);
    }
}
