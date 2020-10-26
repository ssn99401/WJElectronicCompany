package com.wj.employees.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.employees.model.DAO.PurchaseManageDAO;
import com.wj.utils.CommandAction;

public class PurchaseStateUpdateAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String purchaseCode = request.getParameter("purchaseCode");
		String status = request.getParameter("status");
		PurchaseManageDAO dao = PurchaseManageDAO.getInstance();
		dao.updateStatus(purchaseCode, status);
		
		return null;
	}
}
