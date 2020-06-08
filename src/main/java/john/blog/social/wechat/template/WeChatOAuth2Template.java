package john.blog.social.wechat.template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import john.blog.social.wechat.connection.WeChatAccessGrant;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

public class WeChatOAuth2Template extends OAuth2Template {

    // Url to get refresh token
    private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    private String clientId;
    private String clientSecret;
    private String accessTokenUrl;

    // Utility Class to convert JSON to a Java Class
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Template Constructor
     *
     * @param clientId        appId
     * @param clientSecret    appSecret
     * @param authorizeUrl    url that gets code
     * @param accessTokenUrl  url that gets access token by code
     */
    public WeChatOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }

    // Use authorizationCode to get access token
    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        // Get accessTokenUrl
        StringBuilder accessTokenRequestUrl = new StringBuilder(accessTokenUrl);

        /* Concatenate parameters */
        // https://api.weixin.qq.com/sns/oauth2/access_token
        // ?appid=APPID
        accessTokenRequestUrl.append("?appid=" + clientId);
        // &secret=SECRET
        accessTokenRequestUrl.append("&secret=" + clientSecret);
        // &code=CODE
        accessTokenRequestUrl.append("&code=" + authorizationCode);
        // &grant_type=authorization_code (Hard Coded)
        accessTokenRequestUrl.append("&grant_type=authorization_code");
        // &redirect_uri=uri
        accessTokenRequestUrl.append("&redirect_uri=" + redirectUri);

        return getAccessToken(accessTokenRequestUrl);
    }

    /* Purpose: refresh access_token
     *
     * Expiration time of access_token is only 2 hours (very shot)
     * When access_token expires, it can be refreshed by refresh_token.
     * There are two results when refreshing
     *
     * 1. access_token has expired, refresh_token will obtain a new access_token and a new expiration time
     * 2. access_token has not expired, refresh_token will not obtain a new access_token,
     *    but the expiration time will be extended
     *
     * refresh_token has a much longer expiration time (30 days)
     * When refresh_token expires, the user must authorize again
     */
    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        StringBuilder refreshTokenUrl = new StringBuilder(REFRESH_TOKEN_URL);

        // ?appid=APPID
        refreshTokenUrl.append("&appid" + clientId);
        // &grant_type=refresh_token (Hard Coded)
        refreshTokenUrl.append("&grant_type=refresh_token");
        // &refresh_token=REFRESH_TOKEN
        refreshTokenUrl.append("&refresh_token" + refreshToken);

        return getAccessToken(refreshTokenUrl);
    }

    // Send request and Get access token
    private AccessGrant getAccessToken(StringBuilder accessTokenRequestUrl) {
        // Send the request
        String response = getRestTemplate().getForObject(accessTokenRequestUrl.toString(), String.class);

        /* Sample response
            {
                "access_token":"ACCESS_TOKEN",
                "expires_in":7200,
                "refresh_token":"REFRESH_TOKEN",
                "openid":"OPENID",
                "scope":"SCOPE",
                "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
            }
        */
        // Convert the returned JSON to a Map Object
        Map<String, Object> result = null;
        try {
            result = objectMapper.readValue(response, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("ObjectMapper failed to resolve JSON to a Map");
        }

        // Get access token
        WeChatAccessGrant accessToken = new WeChatAccessGrant(
                (String) result.get("access_token"),
                (String) result.get("scope"),
                (String) result.get("refresh_token"),
                Long.valueOf((Integer) result.get("expires_in"))
        );
        accessToken.setOpenId((String) result.get("openid"));

        // Return access token
        return accessToken;
    }

    // Handle character encoding issue
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate template = super.createRestTemplate();
        template.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return template;
    }

    // Get authorization CODE
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthenticateUrl(parameters);
    }

    // Get authorization CODE
    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url =  super.buildAuthenticateUrl(parameters);

        /*https://open.weixin.qq.com/connect/qrconnect*/
        // ?appid=APPID (need to add)
        // &redirect_uri=REDIRECT_URI (handled already)
        // &response_type=code (handled by deafult)
        // &scope=SCOPE (need to add)
        // &state=STATE#wechat_redirec (optional)
        url = url + "&appid=" + clientId + "&scope=snsapi_login";

        return url;
    }
}
