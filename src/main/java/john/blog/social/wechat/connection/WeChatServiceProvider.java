package john.blog.social.wechat.connection;

import john.blog.social.wechat.api.WeChat;
import john.blog.social.wechat.api.WeChatImpl;
import john.blog.social.wechat.template.WeChatOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/* OAuth2 Processor for WeChat
 * Also used for connection purpose in Spring Social **/
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {

    // URL for WeChat to get Authorization Code
    private static final String AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/qrconnect";
    // URL for WeChat to get Access Token
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    // Step 1 ~ 6
    public WeChatServiceProvider(String appId, String appSecret) {
        super(new WeChatOAuth2Template(appId, appSecret, AUTHORIZATION_URL, ACCESS_TOKEN_URL));
    }

    // Step 7 ~ 8
    @Override
    public WeChat getApi(String accessToken) {
        return new WeChatImpl(accessToken);
    }
}
