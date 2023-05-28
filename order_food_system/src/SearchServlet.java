import Dish.Dish;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 调用后台JavaBean中的方法获取符合条件的菜品列表，并转发回首页展示
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        Dish dao = new Dish();
        List<Dish> dishList = null;
        try {
            dishList = dao.searchDish(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("dishList", dishList);

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        ((RequestDispatcher) rd).forward(request, response);
    }
}