package john.blog.config;

import john.blog.handler.LoginFailureHandler;
import john.blog.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

/* Security Configuration Class */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Password Encoder
    @Bean()
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Add BlogSpringSocialConfigurer into our security configuration
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorization Requests
        http
            .formLogin()
                // Path of the self-defined login page
                .loginPage("/login")
                // Path of the login form when submitting
                .loginProcessingUrl("/visitorLogin")
                .successForwardUrl("/")
                // Handler for login success
                .successHandler(loginSuccessHandler)
                // Handler for login failure
                .failureHandler(loginFailureHandler)
                .and()
            // Permit all following paths without authorization
            .authorizeRequests()
                .antMatchers(
                "/", "/index",
                            "/login", "/visitorLogin", "/signup", "/visitorSignUp", "/visitorSignUp", "/sendSMS",
                            "/moods", "/blogs", "/albums", "/links", "/archives", "/search",  "/searchTopic",
                            "/css/**", "/images/**", "/js/**", "/layer/**", "/social/**", "/statics/**", "/upload/**"
                )
                .permitAll()
                // For all the requests, we need them to authenticate
                .anyRequest().authenticated()
                .and()
                // Configure Spring Security so that iframe is allow to have an embedded page
            .headers().frameOptions().disable()
                .and()
            // Disable CSRF (Cross Site Request Forgery) prevention (跨站请求伪造的防护)
            .csrf().disable()
            .apply(springSocialConfigurer);
    }

}
