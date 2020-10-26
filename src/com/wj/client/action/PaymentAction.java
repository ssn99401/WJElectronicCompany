package com.wj.client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientDAO;
import com.wj.client.model.VO.ClientVO;
import com.wj.utils.CommandAction;

public class PaymentAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		ClientDAO clientDao = ClientDAO.getInstance();
		ClientVO client = clientDao.getClient(request.getParameter("id"));
		
		String name = client.getName();
	    String email = client.getEmail();
	    String phone = client.getTel();
	    String address = client.getAddress1() + client.getAddress2();
		
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("cmd", request.getParameter("cmd"));
		request.setAttribute("price", request.getParameter("price"));
		request.setAttribute("quantity", request.getParameter("quantity"));
		request.setAttribute("index", request.getParameter("index"));
		
		return "/client/doPaymentAPI.jsp";
		
	}
}
