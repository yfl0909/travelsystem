package cn.yfl.travel.dao.impl;

import cn.yfl.travel.dao.CategoryDao;
import cn.yfl.travel.entity.Category;
import cn.yfl.travel.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {


    private Connection conn = null;
    private PreparedStatement psmt = null;
    private ResultSet rs = null;

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        List<Category> categories = new ArrayList<>();
        try {
            conn = JDBCUtils.getConn();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while(rs.next()){
                Category category = new Category(rs.getInt("cid"), rs.getString("cname"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
