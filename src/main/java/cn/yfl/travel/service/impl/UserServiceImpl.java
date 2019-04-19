package cn.yfl.travel.service.impl;

import cn.yfl.travel.dao.UserDao;
import cn.yfl.travel.dao.impl.UserDaoImpl;
import cn.yfl.travel.entity.User;
import cn.yfl.travel.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User u) {
        User user = userDao.findByUsername(u.getUsername());
        if(user != null){
            //用户已存在 注册失败
            return false;
        }
        userDao.save(u);
        return true;
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

}
