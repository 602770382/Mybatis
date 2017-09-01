import com.lawrie.pojo.Category;
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

        Product product=session.selectOne("getProduct",16);
        Category c=new Category();
        c.setId(2);
        product.setCategory(c);
        session.update("updateProduct",product);


        List<Product>ps=session.selectList("listProduct");
        for(Product p:ps){
            System.out.println(p+"对应的分类\t"+p.getCategory());
        }

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
}
