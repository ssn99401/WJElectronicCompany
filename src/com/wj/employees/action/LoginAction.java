package com.wj.employees.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientDAO;
import com.wj.employees.model.DAO.EmployeesDAO;
import com.wj.utils.CommandAction;

public class LoginAction implements CommandAction {
   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
      
      EmployeesDAO employeesDao = EmployeesDAO.getInstance();
      
      request.setCharacterEncoding("utf-8");
      
      String id = request.getParameter("id");
      
      int result  = employeesDao.getLoginResult(id, request.getParameter("pw"));
      
      request.setAttribute("eid", id);
      
      request.setAttribute("result", result);
      
      return "/employees/loginPro.jsp";
   }
}