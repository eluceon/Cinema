package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/admin/profile", "/admin/panel", "/admin/panel/*"})
public class AuthorisationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute("admin") == null) {
//            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            httpServletResponse.sendRedirect("/admin/signin");
        } else {
            chain.doFilter(request, response);
        }
    }
}
