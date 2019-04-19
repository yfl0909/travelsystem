package cn.yfl.travel.dao.impl;

import cn.yfl.travel.dao.UserDao;
import cn.yfl.travel.entity.User;
import cn.yfl.travel.utils.JDBCUtils;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private Connection conn = null;
    private PreparedStatement psmt = null;
    private ResultSet rs = null;

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        String sql = "select * from tab_user where username = ?";
        try {
            conn = JDBCUtils.getConn();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, username);
            rs = psmt.executeQuery();
            while(rs.next()){
                int uid = rs.getInt("uid");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String birthday = rs.getString("birthday");
                String sex = rs.getString("sex");
                String telephone = rs.getString("telephone");
                user = new User(uid, username, password, name, birthday, sex, telephone);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, psmt, conn);
        }
        return user;
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void save(User user){
        String sql = "insert into tab_user values(null, ?, ?, ?, ?, ?, ?)";
        try {
            conn = JDBCUtils.getConn();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user.getUsername());
            psmt.setString(2, user.getPassword());
            psmt.setString(3, user.getName());
            psmt.setString(4, user.getBirthday());
            psmt.setString(5, user.getSex());
            psmt.setString(6, user.getTelephone());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(psmt, conn);
        }
    }

    /**
     * 通过用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        String sql = "select * from tab_user where username = ? and password = ?";
        try {
            conn = JDBCUtils.getConn();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, username);
            psmt.setString(2, password);
            rs = psmt.executeQuery();
            while(rs.next()){
                int uid = rs.getInt("uid");
                String name = rs.getString("name");
                String birthday = rs.getString("birthday");
                String sex = rs.getString("sex");
                String telephone = rs.getString("telephone");
                user = new User(uid, username, password, name, birthday, sex, telephone);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, psmt, conn);
        }
        return user;
    }
}
