package com.JejuTravel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JejuTravel.dao.BeachDAO;
import com.JejuTravel.dao.FoodDAO;
import com.JejuTravel.vo.BeachVO;
import com.JejuTravel.vo.FoodVO;


/**
 * Servlet implementation class JoinUsProc
 */
@WebServlet("/BeachServlet")
public class beachProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public beachProc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		//name,phone,email, pw
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		
		//확인용
//		System.out.println("이름 : " + name);
		BeachVO uvo = new BeachVO();
		uvo.setAddress(address);
		uvo.setName(name);
		
		
		
		// static 으로 만들어서 바로 사용 가능
		// 원래는 객체 생성 후 만듬.
		//response.setContentType("text/html; charset=UTF-8");
		 PrintWriter out = response.getWriter();
		try {
			BeachDAO.InsertUser(uvo);
			response.sendRedirect("dbbeach.JejuTravel");
//			out.println("OK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

				
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
