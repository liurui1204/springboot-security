package com.LiuR.lambda;

import com.LiuR.lambda.entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author liurui
 * @Description : 测试lambda表达式
 * @create 2022-04-08-11:16 上午
 */
public class TestLambda {
    public void test(){
        //匿名内部类
        Comparator<Integer> cpt = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> set = new TreeSet<>(cpt);

        //使用lambda表达式
        Comparator<Integer> cpt2 = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> set2 = new TreeSet<>(cpt2);
    }

    //筛选颜色为红色
    public List<Product> filterProductByColor(List<Product> list){
        List<Product> products = new ArrayList<>();
        for (Product product : products){
            if("红色".equals(product.getColor())){
                products.add(product);
            }
        }
        return products;
    }

    //筛选间隔小于8千的
    public List<Product> filterProductByPrice(List<Product> list){
        List<Product> products = new ArrayList<>();
        for (Product product : products){
            if(product.getPrice() < 8000){
                products.add(product);
            }
        }
        return products;
    }

    //定义过滤方法，将接口当作参数传入，这样这个过滤方法就不用修改，在实际调用的时候将具体的实现类传入即可。
    public List<Product> filterProductByPredicate(List<Product> list, MyPredicate<Product> mp){
        List<Product> products = new ArrayList<>();
        for (Product product : list){
            if(mp.test(product)){
                products.add(product);
            }
        }
        return products;
    }

    //是否匿名内部类实现

    //筛选是否是红色
    public void test2(){
        List<Product> list = new ArrayList<>();
        filterProductByPredicate(list, new MyPredicate<Product>() {
            @Override
            public boolean test(Product product) {
                return "红色".equals(product.getColor());
            }
        });
    }
    //筛选是否是小于8千
    public void test3(){
        List<Product> list = new ArrayList<>();
        filterProductByPredicate(list, new MyPredicate<Product>() {
            @Override
            public boolean test(Product product) {
                return product.getPrice() < 8000;
            }
        });
    }

    //使用lambda表达式实现

    //筛选红色
    public void test4(){
        List<Product> list = new ArrayList<>();
        filterProductByPredicate(list, p->"红色".equals(p.getColor()));
    }

    //筛选小于8000
    public void test5(){
        List<Product> list = new ArrayList<>();
        filterProductByPredicate(list, p->p.getPrice()<8000);
    }

    //不用定义过滤方法，直接在集合上进行操作。
    public static void test6(List<Product> list){
        //根据价格过滤
        list.stream()
                .filter(p->p.getPrice()<8000)
                .limit(2)
                .forEach(p->{
                    System.out.println("颜色是："+p.getColor());
                    System.out.println("价格是："+p.getPrice());
                });
        //根据颜色过滤
        list.stream()
                .filter(p->p.getColor().equals("红色"))
                .limit(2)
                .forEach(p->{
                    System.out.println("颜色是："+p.getColor());
                    System.out.println("价格是："+p.getPrice());
                });
        //遍历输出商品名称
        list.stream()
                .filter(p->p.getColor().equals("红色"))
                .filter(p->p.getPrice() < 8000)
                .forEach(p->{
                    System.out.println("颜色是："+p.getColor());
                    System.out.println("价格是："+p.getPrice());
                });
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setPrice(9000F);
        product.setColor("红色");
        products.add(product);
        Product pro = new Product();
        pro.setColor("绿色");
        pro.setPrice(6000F);
        products.add(pro);
        test6(products);
    }

    public void Test(){
        System.out.println("123");
    }

}
