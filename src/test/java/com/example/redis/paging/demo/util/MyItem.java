package com.example.redis.paging.demo.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyItem implements Serializable {
    private String name;

    private String price;
}
