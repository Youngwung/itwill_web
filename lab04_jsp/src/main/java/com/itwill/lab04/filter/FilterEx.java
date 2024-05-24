package com.itwill.lab04.filter;

import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class FilterEx
 */
// 필터 요청 주소 매핑 설정:
// (1) web.xml(deployment descriptor) 파일에서 <filter>, <filter-mapping> 태그에서 설정하거나
// (2) @Webfilter 애너테이션으로 설정.
// web.xml과 애너테이션을 중복으로 설정하면 안됨.
// 서블릿에서는 애너테이션이 편한 경우도 있지만
// 필터에서는 적용되는 순서가 중요할 수 있기 때문에
// web.xml에 설정하는 것을 권장.
// 필터는 web.xml에 나열된 순서대로 실행됨.
public class FilterEx extends HttpFilter {
	private static final long serialVersionUID = 1L;

	public FilterEx() {
		System.out.println("FilterEx 생성");
	}
	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	    // WAS가 종료될 때 생성된 필터 객체를 소멸시키기 위해서 호출하는 메서드.
		System.out.println("FilterEx::destroy() 호출");
		// 서버가 종료될 때 실행되는 메서드.
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override // 필터에서 가장 중요한 메서드
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
	    // 클라이언트로부터 온 요청을 필터 체인(-> 서블릿)으로 전달하기 전에 실행할 코드를 작성.
		System.out.println("FilterEx::chain.doFilter() 호출 전");
		
		// 요청을 필터 체인으로 전달 -> 요청 주소에 매핑된 서블릿 메서드 호출.
		chain.doFilter(request, response);
		
		// 요청 처리가 끝난 후 실행할 코드를 작성.
		System.out.println("FilterEx::chain.doFilter() 호출 후");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("FilterEx::init() 메서드 호출");
	    // 필터가 생성된 후 초기화 작업을 수행하기 위해서 호출되는 메서드.
		// 서버가 시작되기 전에 실행되는데 그 때 해야 할 일이 없다면
		// 비워두면 됨. 메서드 자체가 없으면 안됨.
	}

}
