package com.wj.client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientDAO;
import com.wj.client.model.VO.ClientVO;
import com.wj.utils.CommandAction;

public class ModifyInfoAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		ClientDAO clientDao = ClientDAO.getInstance();
		
		ClientVO vo = new ClientVO();
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		vo.setName(request.getParameter("name"));
		vo.setTel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
		vo.setEmail(request.getParameter("email"));
		vo.setZipcode(request.getParameter("zipcode"));
		vo.setAddress1(request.getParameter("address1"));
		vo.setAddress2(request.getParameter("address2"));
		
		int result = clientDao.updateClientInfo(vo);
		request.setAttribute("result", result);
		
		return "/client/modifyInfoPro.jsp";
	}
}
