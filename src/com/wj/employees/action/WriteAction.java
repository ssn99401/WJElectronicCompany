package com.wj.employees.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.employees.model.DAO.EmployeesNoticeDAO;
import com.wj.utils.CommandAction;

public class WriteAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		EmployeesNoticeDAO dao = EmployeesNoticeDAO.getInstance();
		int result = dao.write(request.getParameter("id"),request.getParameter("title"),request.getParameter("content"));
		
		request.setAttribute("result", result);
		request.setAttribute("id",request.getParameter("id"));

		
		return "/employees/writePro.jsp";
	}

}
