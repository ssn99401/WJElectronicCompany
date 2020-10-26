package com.wj.employees.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientNoticeDAO;
import com.wj.client.model.VO.ClientNoticeVO;
import com.wj.utils.CommandAction;

public class CliNoticeAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		ClientNoticeDAO clientNoticeDao = ClientNoticeDAO.getInstance();
		
		int i = Integer.parseInt(request.getParameter("pagenumber"));
		ArrayList<ClientNoticeVO> result  = clientNoticeDao.getNoticeList(i);
		int cnt = clientNoticeDao.getListCount();
		int pageSize = 10;
		
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", cnt);
		request.setAttribute("pageNum", i);
		request.setAttribute("vo", result);
		
		return "/employees/noticeForm.jsp";
	}
}
