package com.wj.employees.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.AsDAO;
import com.wj.client.model.VO.AsVO;
import com.wj.utils.CommandAction;

public class AsListAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		AsDAO clientNoticeDao = AsDAO.getInstance();
		
		int i = Integer.parseInt(request.getParameter("pagenumber"));
		ArrayList<AsVO> result  = clientNoticeDao.getNoticeList(i);
		int cnt = clientNoticeDao.getNext() - 1;
		int pageSize = 10;//한 페이지 안에 들어가는 게시글 수
		
		request.setAttribute("pageSize", pageSize); 
		request.setAttribute("count", cnt); //총 게시글 수 
		request.setAttribute("pageNum", i);
		request.setAttribute("vo", result);
		
		return "/employees/asListForm.jsp";
	}
}
