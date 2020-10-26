package com.wj.employees.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.employees.model.DAO.EmProductDAO;
import com.wj.employees.model.VO.EmProductVO;
import com.wj.utils.CommandAction;

public class EmProductInfoAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String productCode = request.getParameter("productCode");
		EmProductDAO productDao = EmProductDAO.getInstance();
		EmProductVO product = productDao.getProductInfo(productCode);
		request.setAttribute("product", product);
		
		return "/employees/productInfoForm.jsp";
	}
}
