package controller.moim;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.User;

@WebServlet("/moim/replys")
public class MoimReplysController extends HttpServlet {
	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post 요청이기 때문에 encoding
		req.setCharacterEncoding("utf-8");

		String ment = req.getParameter("ment");
		String moimId = req.getParameter("moimId");

		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();

		Map<String, Object> params = new HashMap<>();

		User user = (User) req.getSession().getAttribute("logonUser");

		params.put("writer", user.getId()); // 작성자 ID 는 현재 로그인하고 있는 ID
		params.put("ment", ment);
		params.put("moimId", moimId);
		// moimId 넘기는 법 <input type="hidden" name="moimId" value="${moimId }"/>
		
		System.out.println(params);
		
		int r = sqlSession.insert("replys.create", params);

		System.out.println(params);

		sqlSession.commit();
		sqlSession.close();

		resp.sendRedirect("/moim/detail?=id" + moimId);

	}
}
