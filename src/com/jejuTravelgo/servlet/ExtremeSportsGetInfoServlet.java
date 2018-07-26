package com.jejuTravelgo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JejuTravel.dao.FoodDAO;
import com.JejuTravel.dao.SportsDAO;
import com.JejuTravel.vo.FoodVO;
import com.JejuTravel.vo.SportsVO;

/**
 * Servlet implementation class ExtremeSportsGetInfoServlet
 */
@WebServlet("/ExtremeSportsGetInfoServlet")
public class ExtremeSportsGetInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExtremeSportsGetInfoServlet() {
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
			SportsVO vo = SportsDAO.getSportsInfo(u_idx);
			String address = "/" + vo.getAddress();
			String name = "/" + vo.getName();
			String bHours = "/" + vo.getbHours();
			String experience= "/" + vo.getExperience();
			String holiday = "/" + vo.getHoliday();
			String price = "/" + vo.getPrice();
			String groupf = "/" + vo.getGroupf();
			String webAddress= "/" + vo.getWebaddress();
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().println("OK/" + u_idx + address+ name+ bHours+ experience + holiday+ price+ groupf + webAddress);
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
