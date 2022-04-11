package com.LiuR.lambda;

import com.LiuR.lambda.entity.Product;

/**
 * @author liurui
 * @Description : 价格筛选类
 * @create 2022-04-08-11:50 上午
 */
public class PricePredicate implements MyPredicate<Product>{
    @Override
    public boolean test(Product product) {
        return product.getPrice() < 8000;
    }
}
