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
		map.put("a", p * 10 - 9);
		map.put("b", p * 10);
		map.put("cates", cates);

//		List<Moim> list = sqlSession.selectList("moims.findSomeByCates", cates);
//		List<Moim> list = Moims.findByCate(cates);

		List<Moim> list = sqlSession.selectList("moims.findSomeByAtoBInCates", map);

		int total = sqlSession.selectOne("moims.countDatas", cates);
		sqlSession.close();

		int lastPage = total / 10 + (total % 10 > 0 ? 1 : 0);

		req.setAttribute("list", list);

		int start = p % 5 == 0 ? p - 4 : p - (p % 5) + 1;
		int last = p % 5 == 0 ? p : p - (p % 5) + 5;

		last = last > lastPage ? lastPage : last;

		// boolean existPrev = start == 1 ? false : true;
		// boolean existNext = start == 1 ? false : true;

		boolean existPrev = p >= 6;
		boolean existNext = lastPage > last;

		req.setAttribute("start", start);
		req.setAttribute("last", last);

		req.setAttribute("existPrev", existPrev);
		req.setAttribute("existNext", existNext);

		req.getRequestDispatcher("/WEB-INF/views/moim/search.jsp").forward(req, resp);

	}
}
