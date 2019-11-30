package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        String uri = request.getRequestURI();
        //运用正则表达式和Filter将图片和网页正常显示
        String pattern = ".*\\.(js|jpg|png|css)";
        //endsWith()字符串以什么结尾
        if(uri.endsWith("login.html")|| uri.endsWith("login")|| Pattern.matches(pattern,uri)){
            chain.doFilter(request,response);
            return;
        }

        String userName =(String)request.getSession().getAttribute("userName");
        if(null == userName){
            response.sendRedirect("login.html");
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
