package com.wj.employees.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientNoticeDAO;
import com.wj.client.model.VO.ClientNoticeVO;
import com.wj.employees.model.DAO.EmployeesDAO;
import com.wj.employees.model.DAO.EmployeesNoticeDAO;
import com.wj.employees.model.VO.EmployeesNoticeVO;
import com.wj.utils.CommandAction;

public class EmpNoticeAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		EmployeesNoticeDAO empdao = EmployeesNoticeDAO.getInstance();
		
		int i = Integer.parseInt(request.getParameter("pagenumber"));
		ArrayList<EmployeesNoticeVO> result  = empdao.getNoticeList(i);
		int cnt = empdao.getListCount();
		int pageSize = 10;
		
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", cnt);
		request.setAttribute("pageNum", i);
		request.setAttribute("vo", result);
		
		return "/employees/noticeForm.jsp";
	}
}
