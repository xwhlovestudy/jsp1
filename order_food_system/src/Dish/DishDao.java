package Dish;

import com.example.util.DBUtils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishDao {

    public boolean addDish(Dish dish) {//增
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO dish (name, price, description) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dish.getName());
            ps.setDouble(2, dish.getPrice());
            ps.setString(3, dish.getDescription());
            int count = ps.executeUpdate();
            if(count > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return false;
    }

    public boolean deleteDish(int id) {//删
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE FROM dish WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            if(count > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return false;
    }

    public boolean updateDish(Dish dish) {//改
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE dish SET name=?, price=?, description=? WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dish.getName());
            ps.setDouble(2, dish.getPrice());
            ps.setString(3, dish.getDescription());
            ps.setInt(4, dish.getId());
            int count = ps.executeUpdate();
            if(count > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, conn);
        }
        return false;
    }

    public List<Dish> getAllDishes() {//查
        List<Dish> result = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM dish";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Dish dish = new Dish();
                dish.setId(rs.getInt("id"));
                dish.setName(rs.getString("name"));
                dish.setImage(rs.getString("image"));
                dish.setPrice(rs.getDouble("price"));
                dish.setDescription(rs.getString("description"));
                result.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return result;
    }

    public List<Dish> searchDish(String keyword) {
        List<Dish> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM dish WHERE name LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Dish dish = new Dish();
                dish.setId(rs.getInt("id"));
                dish.setName(rs.getString("name"));
                dish.setImage(rs.getString("image"));
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

    public Dish getDishById(int id) {
        Dish dish = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM dish WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                dish = new Dish();
                dish.setId(rs.getInt("id"));
                dish.setName(rs.getString("name"));
                dish.setImage(rs.getString("image"));
                dish.setPrice(rs.getDouble("price"));
                dish.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return dish;
    }

    }
}