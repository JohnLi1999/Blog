package john.blog.social.qq.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

// Set DEFAULT_FILTER_PROCESSES_URL in SocialAuthenticationFilter to our self-defined one
public class BlogSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public BlogSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        // Get Filter
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        // Set filterProcessesUrl to our defined value ("qqLogin")
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
