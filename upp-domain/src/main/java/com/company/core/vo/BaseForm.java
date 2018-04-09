package com.company.core.vo;


import lombok.Data;

@Data
public class BaseForm<T> {

    private String rows = "10";

    private String pageNo = "1";

    private String page = "1";

    Pagination<T> pagination;
}

