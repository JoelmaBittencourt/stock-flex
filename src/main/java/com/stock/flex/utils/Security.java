package com.stock.flex.utils;

public class Security {
    public static final String[] PUBLIC_MATCHERS = {
            "/auth/**",
            "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**"
    };

    public static final String[] PRIVATE_MATCHERS = {
            "/person/**",

    };
}
