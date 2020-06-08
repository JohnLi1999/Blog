package john.blog.properties;

public class WeChatProperties {

    // Application Identifier and Secret Key
    private String appId = "wxd99431bbff8305a0";
    private String appSecret = "60f78681d063590a469f1b297feff3c4";

    // Service Provider Id
    private String providerId = "wechat";
    // Processes Url that will be blocked by the Filter
    private String filterProcessesUrl = "/qqLogin";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
