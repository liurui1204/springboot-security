package com.LiuR.lambda;

/**
 * @author liurui
 * @Description : 定义一个MyPredicate接口
 * @create 2022-04-08-11:42 上午
 */
public interface MyPredicate<T> {
    boolean test(T t);
}
