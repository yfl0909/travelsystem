package cn.yfl.travel.service;

import cn.yfl.travel.entity.User;

public interface UserService {
    boolean regist(User user);
    User login(User user);
}
