package com.wj.client.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ZipcodeDAO;
import com.wj.client.model.VO.ZipCodeVO;
import com.wj.utils.CommandAction;

public class ZipCodeAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		ZipcodeDAO zipcodeDao = ZipcodeDAO.getInstance();
		String check = request.getParameter("check");
		String dong = request.getParameter("dong");
		Vector<ZipCodeVO> zipcodeList = zipcodeDao.zipcodeRead(dong);
		int totalListSize = zipcodeList.size();
		request.setAttribute("check", check);
		request.setAttribute("dong", dong);
		request.setAttribute("zipcodeList", zipcodeList);
		request.setAttribute("totalListSize", totalListSize);
		return "/client/zipCheck.jsp";
	}

}
