import com.lawrie.pojo.Category;
import com.lawrie.pojo.Order;
import com.lawrie.pojo.OrderItem;
import com.lawrie.pojo.Product;
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


        session.delete("deleteOrder",1);
        listOrder(session);

        /* 一对多test
        List<Category>cs=session.selectList("listCategory");
        for(Category c:cs){
            System.out.println(c);
            List<Product>ps=c.getProducts();
            for(Product p:ps)
                System.out.println("\t"+p);
        }*/

        /* 多条件查询
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

    private static void listAll(SqlSession session) {
        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

    //多对多test
    private static void listOrder(SqlSession session){
        List<Order>os=session.selectList("listOrder");
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

}
