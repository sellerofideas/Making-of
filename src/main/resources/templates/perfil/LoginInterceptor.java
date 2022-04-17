package templates.perfil;

import org.springframework.web.servlet.HandlerInterceptor;

public class LogInterceptor implements HandlerInterceptor {

  private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
    throws Exception {
      logger.info("preHandle");
      return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 
    throws Exception {
      logger.info("postHandle");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
    throws Exception {
      logger.info("afterCompletion");
  }

}
