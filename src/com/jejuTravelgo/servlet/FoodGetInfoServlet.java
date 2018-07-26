package com.jejuTravelgo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JejuTravel.dao.FoodDAO;
import com.JejuTravel.dao.UserDAO;
import com.JejuTravel.vo.FoodVO;
import com.JejuTravel.vo.UserVO;

/**
 * Servlet implementation class FoodGetInfoServlet
 */
@WebServlet("/FoodGetInfoServlet")
public class FoodGetInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodGetInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String u_idx = request.getParameter("u_idx");
		System.out.println(u_idx);
		try {
			FoodVO vo = FoodDAO.getFoodInfo(u_idx);
			String address = "/" + vo.getAddress();
			String name = "/" + vo.getName();
			String bHours = "/" + vo.getbHours();
			String food = "/" + vo.getFood();
			String holiday = "/" + vo.getHoliday();
			String groupf = "/" + vo.getGroupf();
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().println("OK/" + u_idx + address+ name+ bHours+ food + holiday + groupf);
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
