package john.blog.social.wechat.connection;

import john.blog.social.wechat.api.WeChat;
import john.blog.social.wechat.api.WeChatUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/* WeChat Adapter (Prototype - Every user has his/her own WeChatAdapter)
 * Purpose: set values in WeChatUserInfo into the connection in Spring Social **/
public class WeChatAdapter implements ApiAdapter<WeChat> {

    // User's unique identifier
    private String openId;

    public WeChatAdapter() {}

    public WeChatAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeChat api) {
        return true;
    }

    @Override
    public void setConnectionValues(WeChat api, ConnectionValues values) {
        // Set contents in WeChatUserInfo into "values" parameter
        WeChatUserInfo userInfo  = api.getUserInfo(openId);
        values.setDisplayName(userInfo.getNickname());
        values.setProviderUserId(userInfo.getOpenid());
        values.setImageUrl(userInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(WeChat api) {
        return null;
    }

    @Override
    public void updateStatus(WeChat api, String message) {

    }
}
