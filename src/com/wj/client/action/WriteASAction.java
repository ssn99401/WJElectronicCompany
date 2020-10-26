package com.wj.client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.AsDAO;
import com.wj.utils.CommandAction;

public class WriteASAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		AsDAO asDao = AsDAO.getInstance();
		int result = asDao.write(request.getParameter("title"), request.getParameter("id"), request.getParameter("pr_name"), request.getParameter("content"));
		
		request.setAttribute("result", result);
		request.setAttribute("id", request.getParameter("id"));
		
		return "/client/writePro.jsp";
	}
}
