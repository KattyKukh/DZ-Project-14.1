package ru.netology.repository;

import ru.netology.domain.Product;

public class ProductRepository {
    private Product[] products = new Product[0];

//    Разработайте репозиторий, позволяющий сохранять Product'ы,
//    получать все сохранённые Product'ы и
//    удалять по id.

    public void save(Product product) {
        Product[] tmp = new Product[products.length + 1];
//        System.arraycopy(products,0,tmp,0, products.length);  <-- можно заменить цикл
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public Product[] findById(int id) {
        Product[] tmp = new Product[1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() == id) {
                tmp[0] = products[copyToIndex];
                return tmp;
            } else {
                copyToIndex++;
            }
        }
        return null;
    }

    public Product[] removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                copyToIndex++;
                tmp[copyToIndex - 1] = product;
            }
        }
        products = tmp;
        return products;
    }

    public Product[] getProducts() {
        return products;
    }
}
