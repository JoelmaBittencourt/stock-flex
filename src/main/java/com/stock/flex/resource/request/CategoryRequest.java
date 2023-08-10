package com.stock.flex.resource.request;

import java.util.List;

public record CategoryRequest(String name,
                              String description,
                              List<String> products) {

}