package org.sx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 用于分页的类
public class PageData {
    private Integer page;   //  当前页
    private Integer size;   //  每页显示的条数
    private String param;   //  用于迷糊查询的参数
}
