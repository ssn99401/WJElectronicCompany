package com.wj.employees.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.employees.model.DAO.AsDAO;
import com.wj.employees.model.DAO.CommentDAO;
import com.wj.employees.model.VO.AsVO;
import com.wj.employees.model.VO.CommentVO;
import com.wj.utils.CommandAction;

public class CommentWriteAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		AsDAO asDao = AsDAO.getInstance();
		CommentDAO coDao = CommentDAO.getInstance();
		CommentVO coVo = new CommentVO();
		
		int de = Integer.parseInt(request.getParameter("de"));
		int i = Integer.parseInt(request.getParameter("asno"));

		
		//코멘트 삭제
		if(de == 1) {
			int no = Integer.parseInt(request.getParameter("cono"));
			
			coDao.deleteComment(no);
			
		}
		//답글 달기
		else if(de == 2) {
			int parent = Integer.parseInt(request.getParameter("parent"));
			String comment_content = request.getParameter("content");
			
			coDao.write2(i,parent, comment_content);
		}
		
		//댓글 달기
		else {
			String comment_content = request.getParameter("content");
			
			coDao.write(i, comment_content);
		}
		
		AsVO result  = asDao.getAsOne(i);
		int cnt = asDao.getNext() - 1;
		
		ArrayList<CommentVO> arr = coDao.getAllComment(i);
		ArrayList<CommentVO> arr2 = coDao.getAllCComment(i);
		
		request.setAttribute("ccomment",arr2 );
		request.setAttribute("comment", arr);
		request.setAttribute("vo", result);
		request.setAttribute("lastPage", cnt);
		
		return "/employees/AsView.jsp";
	}

}
