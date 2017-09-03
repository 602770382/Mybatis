package com.lawrie.mapper;

import com.lawrie.pojo.Order;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    @Select("SELECT * FROM order_")
    @Results({
            @Result(property="id",column="oid"),
            @Result(property="orderItems",javaType=List.class,column="id",
            many=@Many(select="com.lawrie.mapper.OrderItemMapper.listByOrder"))
    })
    public List<Order>list();
}
