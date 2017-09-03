package com.lawrie.mapper;

import com.lawrie.CategoryDynaSqlProvider;
import com.lawrie.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryCRUDMapper {
    @InsertProvider(type= CategoryDynaSqlProvider.class,method="add")
    public int add(Category category);

    @DeleteProvider(type=CategoryDynaSqlProvider.class,method="delete")
    public void delete(int id);

    @SelectProvider(type=CategoryDynaSqlProvider.class,method="get")
    public Category get(int id);

    @UpdateProvider(type=CategoryDynaSqlProvider.class,method="update")
    public void udpate(Category category);

    @SelectProvider(type=CategoryDynaSqlProvider.class,method="list")
    public List<Category>list();
}
