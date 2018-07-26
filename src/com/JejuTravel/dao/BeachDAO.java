package com.JejuTravel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.JejuTravel.DB.DBConn;
import com.JejuTravel.vo.BeachVO;

/*
 * 수정 쿼리
 * update user set name = '일' where u_idx = 3;
 * 
 * 삭제 쿼리
 * delete from user where u_idx = 16
 * 
 * 추가 쿼리
 * insert into user(name, phone, email, pw)
	values ('빅데이터', '010-1111-1111', 'aa@aa.com', '123')
 */

public class BeachDAO {
	public static ArrayList<BeachVO> getBeach() throws Exception {
		// DB 접속
		Connection db = DBConn.getConnection();
		// 쿼리 날려서 유저 정보를 검색
		String sql  = "select * from jejubeach";   // 사용자 정보 전체 검색 쿼리
		PreparedStatement pstmt = db.prepareStatement(sql);  // sql 관리 객체
		ResultSet rs = pstmt.executeQuery();   // 쿼리를 DB에 날려서 rs에 값을 받음
		
		ArrayList<BeachVO> beachList = new ArrayList<>();
		//u_idx / name / address 
		while (rs.next()) {
			BeachVO vo = new BeachVO();    // 사용자 정보를 담은 객체
			vo.setU_idx(rs.getInt("u_idx"));    // 사용자의 키값(PK)
			vo.setName(rs.getString("name")); // 해수욕장 이름
			vo.setAddress(rs.getString("address")); // 인터넷 주소
			beachList.add(vo);   // 정보 객체를 리스트에 담기
			
		}	
		db.close();  // db 연결 정보 닫기
		return beachList;   // 사용자 정보 리스트를 메소드 외부로 보내기
	}
	public static void InsertUser(BeachVO vo) throws Exception {
		// DB 접속
		Connection db = DBConn.getConnection();
		// 쿼리 날려서 유저 정보를 삽입
		// insert into user (name, phone, email, pw) values ('이름', '010-1234-1234', 'a@a.com', '1234')
		String sql  = "insert into jejubeach(name, address) values (?, ?)";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getAddress());
		
		// 쿼리 실행
		pstmt.executeUpdate();
		db.close();
	}
	
	public static void updateInfo(BeachVO vo) throws Exception {
		Connection db = DBConn.getConnection();
		// 쿼리 날려서 유저 정보를 삽입
		// insert into user (name, email, username, pw) values ()
		String sql  = "update jejubeach set name=?, address=? where u_idx=?";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getAddress());
		pstmt.setInt(3, vo.getU_idx());

		pstmt.executeUpdate();
		db.close();
}
	public static void delInfo(String u_idx) throws Exception {
		// DB 접속
		Connection db = DBConn.getConnection();
		
		String sql  = "delete from jejubeach where u_idx = ?";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, u_idx);
		
		// 쿼리 실행
		pstmt.executeUpdate();
		db.close();
	}
	
	public static BeachVO getBeachInfo(String u_idx) throws Exception {
		// DB 접속
		Connection db = DBConn.getConnection();
		// 쿼리 날려서 유저 정보를 검색
		String sql  = "select * from jejubeach where u_idx = ?";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, u_idx);
		
		BeachVO vo = null;   // 사용자 정보를 담는 객체
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			vo = new BeachVO();
			vo.setU_idx(rs.getInt("u_idx"));
			vo.setName(rs.getString("name"));
			vo.setAddress(rs.getString("address"));
			
		}	
		db.close();
		return vo;
	}
}
