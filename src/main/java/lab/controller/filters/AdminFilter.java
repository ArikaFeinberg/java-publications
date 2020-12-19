package lab.controller.filters;

import lab.model.dao.entities.User;
import lab.model.dao.entities.enums.Role;
import lab.model.dao.entities.enums.Theme;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        if(session!=null ) {
            if(((User)(session.getAttribute("user"))).getRole() == Role.ADMIN)
            filterChain.doFilter(servletRequest,servletResponse);
            else res.sendRedirect(contextPath +"/User/Home");
        } else {
            res.sendRedirect(contextPath +"/Guest");
        }
    }

    @Override
    public void destroy() {

    }
}
