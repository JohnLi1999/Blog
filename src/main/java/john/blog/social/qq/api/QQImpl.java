package john.blog.social.qq.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.util.StringUtils;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    // oauth_consumer_key => appId
    // URL to obtain user information
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    // URL to obtain openId
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    // Obtain from QQProperties Class
    private String appId;
    // QQ User Unique Identifier
    private String openId;

    // Utility Class that convert a JSON to a Java Object
    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        /* 1. Handle AccessToken
           ACCESS_TOKEN_PARAMETER will automatically add access_token to
           the url that will obtain user information   */
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        // 2. Set value for appId
        this.appId = appId;

        /* 3. Set value for openId */
        // 3.1. Set value for URL_GET_OPENID
        String url = String.format(URL_GET_OPENID, accessToken);

        /* 3.2. Send request and get return value
            callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
         */
        String response = getRestTemplate().getForObject(url, String.class);
        // Convert the result that matches JSON format
        response = StringUtils.replace(response, "callback(", "");
        response = StringUtils.replace(response, " );", "");

        // 3.3. Convert a JSON String to an OpenId Class
        OpenId openId;
        try {
            openId = objectMapper.readValue(response, OpenId.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to Obtain OpenId!");
        }

        this.openId = openId.getOpenid();
    }

    @Override
    public QQUserInfo getUserInfo() {
        /* 1. Set value for URL_GET_USER_INFO
              (accessToken has been added through constructor, and we do not need to handle it)   */
        String url = String.format(URL_GET_USERINFO, appId, openId);

        // 2. Send request and get return value
        String result = getRestTemplate().getForObject(url, String.class);

        // 3. Convert a JSON String to an OpenId Class
        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to Obtain User Information!");
        }

        return qqUserInfo;
    }

}
