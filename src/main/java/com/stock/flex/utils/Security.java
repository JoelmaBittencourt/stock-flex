package com.stock.flex.utils;

public class Security {
    public static final String[] PUBLIC_MATCHERS = {
            "/person/**",
            "/auth/**",
            "/test/**",
    };

    public static final String[] PUBLIC_MATCHERS_POST = {
            "/user",
            "/login"
    };
}
