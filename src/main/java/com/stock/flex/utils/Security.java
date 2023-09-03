package com.stock.flex.utils;

public class Security {
    public static final String[] PUBLIC_MATCHERS = {
            "/auth/**",
            "/test/**",
    };

    public static final String[] PRIVATE_MATCHERS = {
            "/person/**",

    };
}
