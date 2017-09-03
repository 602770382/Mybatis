package com.lawrie.mapper;

import com.lawrie.pojo.OrderItem;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderItemMapper {
    @Select("SELECT * FROM order_item WHERE oid=#{oid}")
    @Results({
            @Result(property="product",column="pid",
                    one=@One(select="com.lawrie.mapper.ProductMapper.get"))
    })
    public List<OrderItem> listByOrder(int oid);
}
