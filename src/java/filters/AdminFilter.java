package filters;

import dataaccess.UserDB;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;


public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String email = (String)session.getAttribute("email");

        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        
        if(user.getRole().getRoleId() != 1 || user.getRole().getRoleId()==2){
         httpResponse.sendRedirect("notes");
         return;
        }
       
        chain.doFilter(request, response);
            
        // this code will execute after the servlet
        // ...
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
