package controller.moim;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Moim;

@WebServlet("/moim/replys")
public class MoimReplysController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		
		String target = req.getParameter("target"); // 모임ID
		// 모임 ID에 해당하는 정보를 읽어온 후 (mybatis 방식으로 처리)
		Moim found = sqlSession.selectOne("replys", target);
		
		
		sqlSession.commit();
		sqlSession.close();
		
	}
}
