package com.jejuTravelgo.servlet;

import java.io.IOException;
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
 * Servlet implementation class BeachUpdateInfoServlet
 */
@WebServlet("/BeachUpdateInfoServlet")
public class BeachUpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeachUpdateInfoServlet() {
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
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		// 전달 되는 값은? u_idx, name, email, username, pw ,grade
		// 전달 되는 값을 받아서 각각의 정보를 업데이트 쿼리를 날려서 수정
		BeachVO vo = new BeachVO();
		vo.setU_idx(Integer.parseInt(u_idx));
		vo.setName(name);
		vo.setAddress(address);
		
		try {
			BeachDAO.updateInfo(vo);
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
