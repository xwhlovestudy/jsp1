package Order;



public Order queryOrder(String orderNo) {
        Order order = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        conn = DBUtils.getConnection();
        String sql = "SELECT * FROM `order` WHERE order_no = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, orderNo);
        rs = ps.executeQuery();
        if(rs.next()) {
        order = new Order();
        order.setId(rs.getInt("id"));
        order.setOrderNo(rs.getString("order_no"));
        order.setUserId(rs.getInt("user_id"));
        order.setCreateTime(rs.getTimestamp("create_time"));
        order.setPayAmount(rs.getDouble("pay_amount"));

        List<OrderDetail> details = queryOrderDetails(order.getId());
        order.setDetails(details);
        }
        } catch (SQLException e) {
        e.printStackTrace();
        } finally {
        DBUtils.close(rs, ps, conn);
        }
        return order;
        }

public List<OrderDetail> queryOrderDetails(int orderId) {
        List<OrderDetail> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        conn = DBUtils.getConnection();
        String sql = "SELECT * FROM order_detail WHERE order_id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, orderId);
        rs = ps.executeQuery();
        while(rs.next()) {
        OrderDetail detail = new OrderDetail();
        detail.setId(rs.getInt("id"));
        detail.setOrderId(rs.getInt("order_id"));
        detail.setDishId(rs.getInt("dish_id"));
        detail.setQuantity(rs.getInt("quantity"));
        detail.setPrice(rs.getDouble("price"));
        result.add(detail);
        }
        } catch (SQLException e) {
        e.printStackTrace();
        } finally {
        DBUtils.close(rs, ps, conn);
        }
        return result;
        }
