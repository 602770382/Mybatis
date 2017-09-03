import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lawrie.mapper.*;
import com.lawrie.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();

        /*二级缓存test*/
        /*
        SqlSession session1=sqlSessionFactory.openSession();
        Category c1 = session1.selectOne("getCategory", 1);
        System.out.println(c1);
        Category c2 = session1.selectOne("getCategory", 1);
        System.out.println(c2);

        session1.commit();
        session1.close();

        SqlSession session2 = sqlSessionFactory.openSession();
        Category c3 = session2.selectOne("getCategory", 1);
        System.out.println(c3);
        session2.commit();
        session2.close();*/

        /*分页插件*/
        /*
        PageHelper.offsetPage(30,10);
        List<Category>cs=session.selectList("listCategoryByPage");
        for(Category c:cs){
            System.out.println(c);
        }

        PageInfo pageInfo=new PageInfo<>(cs);
        System.out.println("总数:"+pageInfo.getTotal());
        System.out.println(pageInfo);*/

        /*tag foreach test*/
        /*
        List<Integer>list=new ArrayList<>();
        list.add(14);
        list.add(15);
        list.add(16);

        List<Product> ps2 = session.selectList("listProduct",list);
        for (Product p : ps2) {
            System.out.println(p);
        }*/

        /*tag if test*/
        /*
        List<Product> ps = session.selectList("listProduct");
        for (Product p : ps) {
            System.out.println(p+" 对应的分类是 \t "+ p.getCategory());
        }
        System.out.println("模糊查询");
        Map<String,Object>params=new HashMap<>();
        params.put("name","law");
        List<Product>ps2=session.selectList("listProduct",params);
        for(Product p:ps2){
            System.out.println(p);
        }*/

        /*一对多test*/
        /*List<Category>cs=session.selectList("listCategoryCollection");
        for(Category c:cs){
            System.out.println(c);
            List<Product>ps=c.getProducts();
            for(Product p:ps)
                System.out.println("\t"+p);
        }*/

        /*多条件查询*/
        /*
        Map<String,Object>params=new HashMap<>();
        params.put("id",1);
        params.put("name","本");

        List<Category>l=session.selectList("listCategoryByIdAndName",params);
        for (Category c : l) {
            System.out.println(c.getName());
        }*/


        session.commit();
        session.close();
    }



    private static void ManyToOne(ProductMapper mapper){
        List<Product>list=mapper.list();
        for(Product p:list){
            System.out.print(p.getName());
            Category c=p.getCategory();
            System.out.println("\t"+c.getName());
        }
    }

    private static void OneToMany(CategoryMapper mapper){
        List<Category>list=mapper.list();
        for(Category c:list){
            System.out.println(c.getName());
            List<Product>pl=c.getProducts();
            for(Product p:pl){
                System.out.println("\t"+p.getName());
            }
        }
    }

    private static void listAllProduct(SqlSession session) {
        Map<String,Object> params = new HashMap<>();
//        params.put("name","a");
//        params.put("price","10");
        List<Product> ps2 = session.selectList("listProduct",params);
        for (Product p : ps2) {
            System.out.println(p);
        }
    }


    private static void listAllCategory(SqlSession session) {
        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

    //多对多test
    private static void listOrder(SqlSession session){
        OrderMapper mapper=session.getMapper(OrderMapper.class);
        List<Order>os=mapper.list();
        for(Order o:os){
            System.out.println(o.getCode());
            List<OrderItem>ois=o.getOrderItems();
            for(OrderItem oi:ois){
                System.out.printf("\t%s\t%f\t%d%n",oi.getProduct().getName(),
                        oi.getProduct().getPrice(),oi.getNumber());
            }
        }
    }

    private static void addOrderItem(SqlSession session){
        Order o1=session.selectOne("getOrder",1);
        Product p1=session.selectOne("getProduct",19);

        OrderItem oi=new OrderItem();
        oi.setOrder(o1);oi.setProduct(p1);oi.setNumber(200);
        session.insert("addOrderItem",oi);
    }

    private static void deleteOrderItem(SqlSession session){
        Order o1=session.selectOne("getOrder",1);
        Product p1=session.selectOne("getProduct",19);
        OrderItem oi=new OrderItem();
        oi.setOrder(o1);
        oi.setProduct(p1);
        oi.setNumber(200);
        session.delete("deleteOrderItem",oi);
    }

    private static void addCategory(CategoryCRUDMapper mapper){
        Category c=new Category();
        c.setName("category3");
        mapper.add(c);
        listAllCategoryByAnnotation(mapper);
    }

    private static void deleteCategory(CategoryCRUDMapper mapper){
        mapper.delete(4);
        listAllCategoryByAnnotation(mapper);
    }

    private static void udpateCategory(CategoryCRUDMapper mapper){
        Category c=mapper.get(2);
        c.setName("category2");
        mapper.udpate(c);
        listAllCategoryByAnnotation(mapper);
    }

    private static void listAllCategoryByAnnotation(CategoryCRUDMapper mapper){
        List<Category> cs=mapper.list();
        for(Category c:cs){
            System.out.println(c);
        }
    }

}
