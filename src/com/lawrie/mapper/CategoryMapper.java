package com.lawrie.mapper;

import com.lawrie.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryMapper {
    @Select("SELECT * FROM category_")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "products", javaType = List.class,
                    column = "id", many = @Many(select = "com.lawrie.mapper.ProductMapper.listByCategory") )
    })
    public List<Category>list();

    //分页查询
    @Select("SELECT * FROM category_ limit #{start},#{count}")
    public List<Category>listByPage(@Param("start")int start,@Param("count")int count);
}
