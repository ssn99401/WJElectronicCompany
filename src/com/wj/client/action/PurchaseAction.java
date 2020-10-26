package com.wj.client.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.purchaseDAO;
import com.wj.client.model.VO.ProductVO;
import com.wj.client.model.VO.purchaseVO;
import com.wj.utils.CommandAction;

public class PurchaseAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd");
		purchaseDAO purchaseDao = purchaseDAO.getInstance();
		String buyerId = request.getParameter("buyerId");
		
		// 주문 내역 리스트 조회
		if(cmd.equals("getPurchaseList")) {
			
			int pageSize = 4;
			int count = purchaseDao.getAllProductCount(buyerId);
		
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			ArrayList<purchaseVO> purchaseList = purchaseDao.getProductListAll(buyerId, pageNum);

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("purchaseList", purchaseList);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("count", count);
			
			return "/client/purchaseListForm.jsp";
			
		} 
		
		// 주문 내역 DB 저장
		else {
			
			int index = Integer.parseInt(request.getParameter("index"));

			String productCode = request.getParameter("productCode");
			String quantity = request.getParameter("quantity");
			int price = Integer.parseInt(request.getParameter("price"));

			request.setAttribute("cmd", cmd);
			request.setAttribute("index", index);
			
			// 장바구니 상품 개별 구매
			if(cmd.equals("basketSelectedItemPurchase")) {
				
				String purchaseCode = makePurchaseCode(buyerId, productCode, "SG");
				int result = purchaseDao.insertPurchaseInfo(purchaseCode ,buyerId, productCode, quantity, price, "singleProduct");

				request.setAttribute("result", result);
				return "/client/removeProduct.jsp";
			} 
			
			// 장바구니 상품 전체 구매 
			else if(cmd.equals("totalPurchase")) {
				
				String purchaseCode = makePurchaseCode(buyerId, "MP", "ML");
				int result = purchaseDao.insertPurchaseInfo(purchaseCode ,buyerId, productCode, quantity, price, "multiProduct");

				request.setAttribute("result", result);

				return "/client/clearShoppingBasket.jsp";

			} 
			
			// 제품 목록 상품 구매
			else if(cmd.equals("SelectedItemPurchase")) {
				
				String purchaseCode = makePurchaseCode(buyerId, productCode, "SG");
				int result = purchaseDao.insertPurchaseInfo(purchaseCode ,buyerId, productCode, quantity, price, "singleProduct");

				request.setAttribute("result", result);

				return "/client/SelectedItemPurchasePro.jsp";
			} 
		}
		
		return "/client/index.jsp";
	}


	// 거래 코드 생성 메서드
	public String makePurchaseCode(String id, String pCode, String type) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		String purchaseCode = pCode.substring(0,2) + id.substring(0,2).toUpperCase() + dateFormat.format(new Date()) + type.toUpperCase();

		return purchaseCode;
	}
}
