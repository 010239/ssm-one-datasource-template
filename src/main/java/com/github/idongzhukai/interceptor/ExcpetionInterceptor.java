package com.github.idongzhukai.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Create 16/3/30,上午9:17
 * @Author dongzhukai
 */
public class ExcpetionInterceptor extends HandlerInterceptorAdapter {

	protected Logger logger = LoggerFactory.getLogger(ExcpetionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		System.out.println("ExcpetionInterceptor.preHandle");
		// 演示：限制仅允许从本机访问
		if (request.getLocalAddr().equals("127.0.0.1")
				|| request.getLocalAddr().equals("0.0.0.0")) {
			return true;
		}
		logger.error("非法入侵：" + request.getLocalAddr());
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("ExcpetionInterceptor.postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("ExcpetionInterceptor.afterCompletion");
		if (ex != null) {
			//logger.error(handler);
			logger.error(ex.getMessage(), ex);
		}
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out
				.println("ExcpetionInterceptor.afterConcurrentHandlingStarted");
	}

}
