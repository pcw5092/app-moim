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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String moimId = req.getParameter("moimId");
		String userId = req.getParameter("userId");
		String writer = req.getParameter("writer");
		
		User logonUser = (User)req.getSession().getAttribute("logonUser");
		if(logonUser == null ) {
			resp.sendRedirect("/user/login");
		}
		
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		
		Map<String, Object> params = new HashMap<>();
		params.put("moimId",moimId );
		params.put("userId",userId );
		params.put("writer",writer);
		
		sqlSession.commit();
		sqlSession.close();
		
	}
}
