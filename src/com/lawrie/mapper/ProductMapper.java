package com.lawrie.mapper;

import com.lawrie.pojo.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    @Select("SELECT * FROM product_ WHERE cid=#{cid}")
    public List<Product> listByCategory(int cid);

    @Select("SELECT * FROM product_ ")
    @Results({
            @Result(property="category", column="cid",
                    one=@One(select="com.lawrie.mapper.CategoryMapper.get"))
    })
    public List<Product>list();

    @Select("SELECT * FROM product_ WHERE id=#{id}")
    public Product get(int id);
}
