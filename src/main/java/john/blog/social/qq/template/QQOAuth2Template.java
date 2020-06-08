package john.blog.social.qq.template;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

// Define QQ OAuth2Template
public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    // Handle character encoding issue
    @Override
    protected RestTemplate getRestTemplate() {
        RestTemplate template = super.createRestTemplate();
        template.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return template;
    }

    // Change the format of response to follow QQ standards
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        /* Sample url:
            access_token=*****&
            expires_in=*****&
            refresh_token=*****
        */
        String response = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

        // Split by "&"
        // StringUtils.split ONLY handle the FIRST occurrence
        String[] items1 = StringUtils.split(response, "&");
        String[] items2 = StringUtils.split(items1[1], "&");

        String access_token = StringUtils.replace(items1[0], "access_token=", "");
        Long expires_in = Long.valueOf(StringUtils.replace(items2[0], "expires_in=", ""));
        String refresh_token = StringUtils.replace(items2[1], "refresh_token=", "");

        return new AccessGrant(access_token, null, refresh_token, expires_in);
    }
}
