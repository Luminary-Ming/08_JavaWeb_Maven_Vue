package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决 CORS 跨域问题
 */
@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 通过过滤器，告诉浏览器，当前站点的数据，哪些是允许跨域访问的
        // 允许哪些域名来访问
        resp.setHeader("Access-Control-Allow-Origin", "*");
        // 允许哪些请求方式来访问
        resp.setHeader("Access-Control-Allow-Methods", "*");
        // 允许本次请求中携带哪些请求头信息
        resp.setHeader("Access-Control-Allow-Headers", "*");
        // 放行
        filterChain.doFilter(req,resp);
    }
}
