/*

* LoginInterceptor: classe che cattura tutte le URL per verificare se l'utente 
* ha il permesso di accesso
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.progetto.fumetteria.model.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String uri = request.getRequestURI();

		if (uri.endsWith("loginForm") || uri.endsWith("doLogin") || uri.endsWith("doComment") || uri.endsWith("home")
				|| uri.endsWith(request.getContextPath()) || uri.endsWith(request.getContextPath() + "/")
				|| uri.contains("resources") || uri.contains("outemail") || uri.endsWith("home/category")
				|| uri.contains("ajaxFilterHome") || uri.contains("comic/view") || uri.contains("/doReserve")
				|| uri.contains("userfront/insert") || uri.contains("suggestion") || uri.endsWith("home/search")
				|| uri.contains("loginajax") || uri.contains("ajaxSetValue") || uri.contains("reserve/ajaxSetValue")
				|| uri.contains("forgotpassword") || uri.contains("userfront/myAccount")
				|| uri.contains("userfront/myReserves") || uri.contains("error")) {

			return true;
		}

		if (request.getSession().getAttribute("loggedUser") != null && uri.contains("admin")) {

			User user = (User) request.getSession().getAttribute("loggedUser");
			if (user.getTypeUser().equals("Admin")) {
				return true;
			} else {
				request.getSession().setAttribute("message", "You have to do login with a admin account!");
				response.sendRedirect(request.getContextPath() + "/loginForm");
				return false;
			}

		} else if (request.getSession().getAttribute("loggedUser") != null) {
			return true;
		}
		System.out.println("#$%¨&*(*&¨%$#$%¨&*()");
		request.getSession().setAttribute("returnurl", uri);
		response.sendRedirect(request.getContextPath() + "/loginForm");
		return false;
	}
}
