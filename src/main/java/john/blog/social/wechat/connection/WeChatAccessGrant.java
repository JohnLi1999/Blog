package john.blog.social.wechat.connection;

import org.springframework.social.oauth2.AccessGrant;

/* Special Access Token Class for WeChat. It has "openId" inside
 * Normal Access Token do not have "openId", so we need to create a special one for WeChat **/
public class WeChatAccessGrant extends AccessGrant {

    private String openId;

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    public WeChatAccessGrant() {
        super("");
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
