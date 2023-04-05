package controller.moim;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Moim;

@WebServlet("/moim/search")
public class MoimSearchController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] cates = req.getParameterValues("cate"); // 같은 이름[cate]으로 여러개의 데이터를 넘길 때

		int p;
		if (req.getParameter("page") == null) {
			p = 1;
		} else {
			p = Integer.parseInt(req.getParameter("page"));
		}

		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();

		Map<String, Object> map = new HashMap<>();
		map.put("a", (p - 1) * 6 + 1);
		map.put("b", p * 6);

		List<Moim> list = sqlSession.selectList("moims.findSomeByAtoB", map);

//		List<Moim> list = Moims.findByCate(cates);

		req.setAttribute("list", list);

		sqlSession.close();
		req.getRequestDispatcher("/WEB-INF/views/moim/search.jsp").forward(req, resp);

	}
}
