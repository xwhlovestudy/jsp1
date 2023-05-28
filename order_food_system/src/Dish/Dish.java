package Dish;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dish implements Serializable {
    private DriverManager DBUtils;



    public List<Dish> searchDish(String keyword) throws SQLException {
        List<Dish> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.; //需要mysql-connector-java.jar包
            String sql = "SELECT * FROM dish WHERE name LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            // 处理结果集
        } catch (SQLException e) {
            e.printStackTrace();
            while(rs.next()) {
                Dish dish = new Dish();
                dish.setId(rs.getInt("id"));
                dish.setName(rs.getString("name"));
                dish.setPrice(rs.getDouble("price"));
                dish.setDescription(rs.getString("description"));
                result.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return result;
        }
    }

    void setDescription(String description) {
    }

    void setPrice(double price) {
    }

    void setName(String name) {
    }

    void setId(int id) {
    }

    public String  getName() {
        return null;
    }

    public Double getPrice() {
        return null;
    }

    public void setImage(String image) {
    }

    public String getImage() {
        return null;
    }

    public String getDescription() {
        return null;
    }

    public int getId() {
        return 0;
    }
}
