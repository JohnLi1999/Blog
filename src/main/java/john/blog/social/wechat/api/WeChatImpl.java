package john.blog.social.wechat.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

public class WeChatImpl extends AbstractOAuth2ApiBinding implements WeChat {

    /*  URL to get WeChat user information
        Http request type: GET
        https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
     */
    private static final String URL_GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    // Utility Class that convert a JSON to a Java Object
    private ObjectMapper objectMapper = new ObjectMapper();

    public WeChatImpl(String accessToken) {
        /* Handle AccessToken
           Automatically add access_token to the request URl that obtains user information   */
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /* Send request and get WeChat user information */
    @Override
    public WeChatUserInfo getUserInfo(String openId) {
        // Concatenate the request url
        String url = URL_GET_USERINFO + openId;

        // Send the request
        String response = getRestTemplate().getForObject(url, String.class);

        // Get the returned JSON (response), and convert JSON to the WeChatUserInfo Object
        WeChatUserInfo weChatUserInfo;
        try {
            weChatUserInfo = objectMapper.readValue(response, WeChatUserInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error with ObjectMapper!");
        }

        return weChatUserInfo;
    }

    // Handle character encoding issue
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        /* Default HttpMessageConverter uses ISO-8859-8, but WeChat uses UTF-8. So we need to avoid the conflict
           Way to fix: remove the HttpMessageConverter at index 0 in the message converter list
                       and add our self-define converter instead */
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }
}
