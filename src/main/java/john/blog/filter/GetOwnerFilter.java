package john.blog.filter;

import john.blog.domain.User;
import john.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
/* Configure what requests will be filtered */
@WebFilter(urlPatterns = "/*", filterName = "ownerFilter")
public class GetOwnerFilter implements Filter {

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /* Purpose: Put owner data into the model */

        // Obtain owner
        User owner = userService.getOwner();
        // Put owner data into request field (model)
        request.setAttribute("owner", owner);

        // Let the request pass the filter
        chain.doFilter(request, response);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // Set maximum size of a single uploaded file (单个文件最大)
        factory.setMaxFileSize(DataSize.parse("30MB"));
        // Set maximum size of all files in a request (设置总上传数据总大小)
        factory.setMaxRequestSize(DataSize.parse("50MB"));
        return factory.createMultipartConfig();
    }
}
