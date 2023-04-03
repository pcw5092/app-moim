package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.Attendance;

public class Attendances {
	public static final String user = "C##MOIM";
	public static final String password = "1q2w3e4r";
	public static final String url = "jdbc:oracle:thin:@192.168.4.22:1521:xe";

	public static List<Attendance> findByMoimId(String moimId) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT * FROM ATTENDANCES WHERE MOIM_ID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, moimId);
			ResultSet rs = ps.executeQuery();
			List<Attendance> list = new ArrayList<>();

			while (rs.next()) {
				Attendance a = new Attendance();
				a.setId(rs.getInt("ID"));
				a.setMoimId(rs.getString("MOIM_ID"));
				a.setUserId(rs.getString("USER_ID"));
				a.setStatus(rs.getInt("STATUS"));

				list.add(a);
			}
			conn.close();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int findUserStatusInMoim(String moimId, String userId) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT STATUS FROM ATTENDANCES WHERE MOIM_ID=? AND USER_ID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, moimId);
			ps.setString(2, userId);

			ResultSet rs = ps.executeQuery();
			int status;

			if (rs.next()) {
				status = rs.getInt("status");
			} else {
				status = 0;
			}
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return -9;
		}
	}

	public static Attendance target(String target) {

		try {
			// 1. 연결
			Connection conn = DriverManager.getConnection(url, user, password);

			// 2. 요청객체 준비
			String sql = "INSERT INTO ATTENDANCES VALUES(ATTENDANCES_SEQ.NEXTVAL, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, target);
			ResultSet rs = ps.executeQuery();

			Attendance attendance = null;
			if (rs.next()) {
				attendance = new Attendance();
				attendance.setMoimId(rs.getString("MOIM_ID"));
				attendance.setUserId(rs.getString("USER_ID"));
				attendance.setStatus(rs.getInt("STATUS"));
			}
			conn.close();
			return attendance;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
