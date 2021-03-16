package ru.goncharov.aliexpressparser.mapper;

import ru.goncharov.aliexpressparser.products.Product;

import java.util.List;

public interface Mapper {
    List<Product> makeListOfProductsFromString();
}
