<%@ page import="Dish.Dish" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2023/5/13
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
    <h1>欢迎光临！！！</h1>
    <h2>这里可以搜索想要的菜品。</h2>
    <form action="search.do" method="post">
      <input type="text" name="keyword" placeholder="请输入菜品名称">
      <input type="submit" value="搜索">
    </form>

    <hr>

    <%
      // 从后台JavaBean中获取菜品列表
      List<Dish> dishList = (List<Dish>)request.getAttribute("dishList");
      if(dishList != null && dishList.size() > 0) {
        // 在页面上展示菜品列表
        for(Dish dish : dishList) {
          out.println("<p>" + (dish.getName() != null ? dish.getName() : "") + " - ￥" + (dish.getPrice() != null ? dish.getPrice() : "") + "</p>");
          out.println("<p>" + dish.getName() + " - ￥" + dish.getPrice() + "</p>");
        }
      } else {
        out.println("暂无菜品");
      }
    %>
  </body>
</html>
