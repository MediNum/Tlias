package com.itheima.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class R {

    private Integer code; // 1 成功 , 0 失败
    private String mes; // 响应信息
    private Object data; // 响应数据 , 如果有数据则封装给data, 如果没有数据则data为null

    /**
     * 快速封装成功R对象(不携带data数据)
     *
     * @return 表示成功的R对象(不携带data数据)
     */
    public static R ok() {
        return R.builder().code(1).mes("Success").build();
    }

    /**
     * 快速封装成功R对象(携带data数据)
     *
     * @return 表示成功的R对象(携带data数据)
     */
    public static R ok(Object data) {
        return R.builder().code(1).mes("Success").data(data).build();
    }

    /**
     * 快速封装失败R对象(不携带data数据)
     *
     * @return 表示失败的R对象(不携带data数据)
     */
    public static R error(String message) {
        return R.builder().code(0).mes(message).build();
    }
}
