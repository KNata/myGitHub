package Servlets.filter;


import Model.Visitor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/")
public class AutorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        Visitor.ROLE visitorRole = (Visitor.ROLE)session.getAttribute("role");
        if (visitorRole == null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("index.jsp"); dispatcher.forward(request, response);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
