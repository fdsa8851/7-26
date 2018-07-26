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
 * Servlet implementation class ExtremeSportsUpdateInfoServlet
 */
@WebServlet("/ExtremeSportsUpdateInfoServlet")
public class ExtremeSportsUpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExtremeSportsUpdateInfoServlet() {
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
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String bHours= request.getParameter("bHours");
		String experience = request.getParameter("experience");
		String holiday = request.getParameter("holiday");
		String price = request.getParameter("price");
		String groupf= request.getParameter("groupf");
		String webAddress = request.getParameter("webaddress"); 
		// 전달 되는 값은? u_idx, name, email, username, pw ,grade
		// 전달 되는 값을 받아서 각각의 정보를 업데이트 쿼리를 날려서 수정
		SportsVO vo = new SportsVO();
		vo.setU_idx(Integer.parseInt(u_idx));
		vo.setAddress(address);
		vo.setName(name);
		vo.setbHours(bHours);
		vo.setExperience(experience);
		vo.setHoliday(holiday);
		vo.setPrice(price);
		vo.setGroupf(groupf);
		vo.setWebaddress(webAddress);
		
		System.out.println(u_idx + address + name + bHours + experience + holiday + price + groupf);
		
		try {
			SportsDAO.updateInfo(vo);
			response.getWriter().println("OK");
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
