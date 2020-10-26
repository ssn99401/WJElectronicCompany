package com.wj.client.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientNoticeDAO;
import com.wj.client.model.VO.ClientNoticeVO;
import com.wj.utils.CommandAction;

public class CliNoticeOneAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		ClientNoticeDAO clientNoticeDao = ClientNoticeDAO.getInstance();
		
		int i = Integer.parseInt(request.getParameter("clno"));
		ClientNoticeVO result  = clientNoticeDao.getNoticeOne(i);
		int cnt = clientNoticeDao.getListCount();
		
		request.setAttribute("vo", result);
		request.setAttribute("lastPage", cnt);
		
		return "/client/noticeOne.jsp";
	}
}
