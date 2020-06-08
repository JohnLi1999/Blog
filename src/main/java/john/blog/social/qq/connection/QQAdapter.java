package john.blog.social.qq.connection;

import john.blog.social.qq.api.QQ;
import john.blog.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    // Check if QQ is able to execute
    public boolean test(QQ api) {
        return true;
    }

    // Adaption between connection data and API data
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        // Adapter for Connection data and API data
        QQUserInfo userInfo = api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null); // QQ uses do not have a profile url
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
