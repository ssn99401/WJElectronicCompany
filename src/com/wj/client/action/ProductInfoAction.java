package com.wj.client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ProductDAO;
import com.wj.client.model.VO.ProductVO;
import com.wj.utils.CommandAction;

public class ProductInfoAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String productCode = request.getParameter("productCode");
		ProductDAO productDao = ProductDAO.getInstance();
		ProductVO product = productDao.getProductInfo(productCode);
		request.setAttribute("product", product);
		
		return "/client/productInfoForm.jsp";
	}
}
