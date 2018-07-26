package com.JejuTravel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.JejuTravel.DB.DBConn;
import com.JejuTravel.vo.HotelVO;
import com.JejuTravel.vo.SportsVO;


public class SportsDAO {
	public static ArrayList<SportsVO> getExperience() throws Exception {
		// DB 접속
		Connection db = DBConn.getConnection();
		// 쿼리 날려서 유저 정보를 검색
		String sql  = "select * from jejuextremesports";   // 사용자 정보 전체 검색 쿼리
		PreparedStatement pstmt = db.prepareStatement(sql);  // sql 관리 객체
		ResultSet rs = pstmt.executeQuery();   // 쿼리를 DB에 날려서 rs에 값을 받음
		
		// 사용자 정보를 담을 리스트
		ArrayList<SportsVO> experienceList = new ArrayList<>();
		//u_idx / address  / name / bHours / Cafe / holiday 
		// 사용자 정보가 한줄씩 읽어서 user 테이블 정보의 마지막 까지
		// 읽어서 더이상 읽어올 정보가 없으면 while 문 종료
		while (rs.next()) {
			SportsVO vo = new SportsVO();    // 사용자 정보를 담은 객체
			vo.setU_idx(rs.getInt("u_idx"));    // 사용자의 키값(PK)
			vo.setAddress(rs.getString("address"));   // 주소
			vo.setName(rs.getString("name")); // 식당이름
			vo.setbHours(rs.getString("bHours")); // 영업시간
			vo.setExperience(rs.getString("experience"));		// 음식
			vo.setHoliday(rs.getString("holiday"));
			vo.setPrice(rs.getString("price"));
			vo.setGroupf(rs.getString("groupf"));
			vo.setWebaddress(rs.getString("webaddress"));
			experienceList.add(vo);   // 사용자 정보 객체를 리스트에 담기
		}	
		db.close();  // db 연결 정보 닫기
		return experienceList;   // 사용자 정보 리스트를 메소드 외부로 보내기
	}
	
	public static void InsertSports(SportsVO vo) throws Exception {
		// DB 접속
		Connection db = DBConn.getConnection();
		// 쿼리 날려서 유저 정보를 삽입
		// insert into user (name, phone, email, pw) values ('이름', '010-1234-1234', 'a@a.com', '1234')
		String sql  = "insert into jejuextremesports (address, name, bHours, experience, holiday,price, groupf, webaddress) values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, vo.getAddress());
		pstmt.setString(2, vo.getName());
		pstmt.setString(3, vo.getbHours());
		pstmt.setString(4, vo.getExperience());
		pstmt.setString(5, vo.getHoliday());
		pstmt.setString(6, vo.getPrice());
		pstmt.setString(7, vo.getGroupf());
		pstmt.setString(8, vo.getWebaddress());
		// 쿼리 실행
		pstmt.executeUpdate();
		db.close();
	}
	
	public static void updateInfo(SportsVO vo) throws Exception {
		Connection db = DBConn.getConnection();
		// 쿼리 날려서 유저 정보를 삽입
		// insert into user (name, email, username, pw) values ()
		String sql  = "update jejuextremesports set address =?, name= ?, bHours=?, experience=?, holiday=?,price=?, groupf=?, webaddress=? where u_idx=?";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, vo.getAddress());
		pstmt.setString(2, vo.getName());
		pstmt.setString(3, vo.getbHours());
		pstmt.setString(4, vo.getExperience());
		pstmt.setString(5, vo.getHoliday());
		pstmt.setString(6, vo.getPrice());
		pstmt.setString(7, vo.getGroupf());
		pstmt.setString(8, vo.getWebaddress());
		pstmt.setInt(9, vo.getU_idx());
		
		pstmt.executeUpdate();
		db.close();
}
	public static void delInfo(String u_idx) throws Exception {
		// DB 접속
		Connection db = DBConn.getConnection();
		
		String sql  = "delete from jejuextremesports where u_idx = ?";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, u_idx);
		
		// 쿼리 실행
		pstmt.executeUpdate();
		db.close();
	}
	
	public static SportsVO getSportsInfo(String u_idx) throws Exception {
		// DB 접속
		Connection db = DBConn.getConnection();
		// 쿼리 날려서 유저 정보를 검색
		String sql  = "select * from jejuextremesports where u_idx = ?";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, u_idx);
		
		SportsVO vo = null;   // 사용자 정보를 담는 객체
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			vo = new SportsVO();
			vo.setU_idx(rs.getInt("u_idx"));
			vo.setAddress(rs.getString("address"));
			vo.setName(rs.getString("name"));
			vo.setbHours(rs.getString("bHours"));
			vo.setExperience(rs.getString("experience"));
			vo.setHoliday(rs.getString("holiday"));
			vo.setPrice(rs.getString("price"));
			vo.setGroupf(rs.getString("groupf"));
			vo.setWebaddress(rs.getString("webaddress"));
			
			
		}	
		db.close();
		return vo;
	}
	
}
