package com.wj.employees.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientNoticeDAO;
import com.wj.client.model.VO.ClientNoticeVO;
import com.wj.employees.model.DAO.EmployeesNoticeDAO;
import com.wj.employees.model.VO.EmployeesNoticeVO;
import com.wj.utils.CommandAction;

public class EmployeesOneAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		EmployeesNoticeDAO dao = EmployeesNoticeDAO.getInstance();
		
		int i = Integer.parseInt(request.getParameter("clno"));
		EmployeesNoticeVO result  = dao.getNoticeOne(i);
		int cnt = dao.getListCount();
		
		request.setAttribute("vo", result);
		request.setAttribute("lastPage", cnt);
		
		return "/employees/noticeOne.jsp";
	}
}
