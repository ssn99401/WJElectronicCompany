package com.wj.client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientDAO;
import com.wj.client.model.VO.ClientVO;
import com.wj.utils.CommandAction;

public class ModifyPasswordAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		ClientDAO clientDao = ClientDAO.getInstance();
		
		if(request.getParameter("cmd").equals("modifyPasswordForm")) {
			ClientVO client = clientDao.getClient(request.getParameter("id"));
			request.setAttribute("client", client);
			
			return "/client/modifyPasswordForm.jsp";
		}
		
		if(request.getParameter("cmd").equals("modifyPasswordPro")) {
			int result = clientDao.updateClientPassword(request.getParameter("id"), request.getParameter("pw"),request.getParameter("newPw"));
			request.setAttribute("result", result);
			
			return "/client/modifyPasswordPro.jsp";
		}
		
		return "/client/index.jsp";
	}
}
