package com.LiuR.lambda;

import com.LiuR.lambda.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liurui
 * @Description : 定义一个颜色过滤类
 * @create 2022-04-08-11:43 上午
 */
public class ColorPredicate implements MyPredicate<Product>{

    private static final String RED = "红色";

    @Override
    public boolean test(Product product) {
        return RED.equals(product.getColor());
    }

}
