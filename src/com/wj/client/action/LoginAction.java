package com.wj.client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientDAO;
import com.wj.utils.CommandAction;

public class LoginAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		ClientDAO clientDao = ClientDAO.getInstance();
		int result  = clientDao.getLoginResult(request.getParameter("id"), request.getParameter("pw"));
		request.setAttribute("result", result);
		return "/client/loginPro.jsp";
	}
}
