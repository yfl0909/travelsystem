package cn.yfl.travel.dao;

import cn.yfl.travel.entity.Category;

import java.util.List;

public interface CategoryDao {

    /**
     * 查询所有
     * @return
     */
    public List<Category> findAll();

}
