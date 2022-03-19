package inhatc.group.mojuk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//HandlerInterceptorAdapter 가 deprecated 되어 interface 상속으로 변경
public class LoginInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*
    	HttpSession session = request.getSession();

        if(ObjectUtils.isEmpty(session.getAttribute("state"))) {
        	response.sendRedirect(request.getContextPath() + "/");
        	return false;
        	
        }else {
            session.setMaxInactiveInterval(30*60);

        	return true;
        }
        */
        return true;
    }
}
