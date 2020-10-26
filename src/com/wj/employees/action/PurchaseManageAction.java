package com.wj.employees.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.ClientEndpoint;

import com.wj.employees.model.DAO.EmployeesDAO;
import com.wj.employees.model.DAO.PurchaseManageDAO;
import com.wj.employees.model.VO.EmPurchaseManageVO;
import com.wj.utils.CommandAction;

public class PurchaseManageAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		PurchaseManageDAO purchaseDao = PurchaseManageDAO.getInstance();
	
		
		/*ArrayList<String> allclient =dao1.getClientid();*///전체고객 불러오기
		
		
		
		// 二쇰Ц �궡�뿭 由ъ뒪�듃 議고쉶
		if(cmd.equals("getPurchaseList")) {
			int pageSize = 10;
			int count = purchaseDao.getAllProductCount();
			
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			ArrayList<EmPurchaseManageVO> purchaseList = purchaseDao.getProductListAll(pageNum);

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("purchaseList", purchaseList);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("count", count);
			
			return "/employees/DeliveryManagement.jsp";
		}
		
		// 二쇰Ц �궡�뿭 DB ���옣
		else {
			
			int index = Integer.parseInt(request.getParameter("index"));

			String productCode = request.getParameter("productCode");
			String quantity = request.getParameter("quantity");
			int price = Integer.parseInt(request.getParameter("price"));

			request.setAttribute("cmd", cmd);
			request.setAttribute("index", index);
			
			// �옣諛붽뎄�땲 �긽�뭹 媛쒕퀎 援щℓ
			
			
			// �젣�뭹 紐⑸줉 �긽�뭹 援щℓ
		
		}
		
		return "/employees/index.jsp";
	}
	
	// 嫄곕옒 肄붾뱶 �깮�꽦 硫붿꽌�뱶
	public String makePurchaseCode(String id, String pCode, String type) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		String purchaseCode = pCode.substring(0,2) + id.substring(0,2).toUpperCase() + dateFormat.format(new Date()) + type.toUpperCase();

		return purchaseCode;
	}
}
