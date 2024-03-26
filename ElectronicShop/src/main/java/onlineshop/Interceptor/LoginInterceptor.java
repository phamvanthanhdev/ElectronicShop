package onlineshop.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("account") == null) {
			session.setAttribute("back-url", request.getRequestURI());
			response.sendRedirect(request.getContextPath() + "/account/login");
			return false;
		} 
		//response.sendRedirect(request.getContextPath() + "/Lesson6/index.htm");
		return true;
	}

}
