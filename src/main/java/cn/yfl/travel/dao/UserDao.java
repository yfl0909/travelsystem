package cn.yfl.travel.dao;

import cn.yfl.travel.entity.User;

public interface UserDao {
    User findByUsername(String username);
    void save(User user);

    User findByUsernameAndPassword(String username, String password);
}
