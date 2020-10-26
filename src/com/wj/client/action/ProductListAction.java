package com.wj.client.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ProductDAO;
import com.wj.client.model.VO.ProductVO;
import com.wj.utils.CommandAction;

public class ProductListAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");

		String category = request.getParameter("category");
		ProductDAO productDao = ProductDAO.getInstance();


		int pageSize = 5;
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int count = 0;

		if(category.equals("all")) {
			ArrayList<ProductVO> productList = productDao.getProductListAll(pageNum);
			count = productDao.getAllProductCount();

			request.setAttribute("count", count);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("productList", productList);
		} else {
			ArrayList<ProductVO> productList = productDao.getProductListToCategory(category, pageNum);
			count = productDao.getProductCount(category);

			request.setAttribute("count", count);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("productList", productList);
		}

		request.setAttribute("category", category);
		request.setAttribute("pageSize", pageSize);
		return "/client/productListForm.jsp";
	}
}
