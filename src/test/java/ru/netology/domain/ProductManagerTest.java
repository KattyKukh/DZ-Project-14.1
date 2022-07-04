package ru.netology.domain;

import org.junit.jupiter.api.Test;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product product1 = new Book(31, "First book", 100, "Первый А.Б.");
    Product product2 = new Book(32, "Second book", 100, "Второй В.Г.");
    Product product3 = new Book(33, "Third book", 100, "Первый А.Б.");
    Product product4 = new Book(34, "Forth book", 100, "Второй В.Г.");
    Product product5 = new Book(35, "Fifth book", 100, "Третий Е.Ж.");
    Product product6 = new Smartphone(36, "1001 smart", 100, "Samsung");
    Product product7 = new Smartphone(37, "2005 smart", 100, "Xiaomi");
    Product product8 = new Smartphone(38, "3562 smart", 100, "Motorola");
    Product product9 = new Smartphone(39, "4568 smart", 100, "Samsung");
    Product product10 = new Smartphone(40, "5689 smart", 100, "Xiaomi");
    Product product11 = new Product(41, "ProductNobookNoSmart", 40_000);

    public void prepare() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        manager.add(product5);
        manager.add(product6);
        manager.add(product7);
        manager.add(product8);
        manager.add(product9);
        manager.add(product10);
    }

    @Test
    public void shouldAddProductAndFindAll() {
        prepare();
        Product[] expected = {product1, product2, product3, product4, product5, product6, product7, product8, product9, product10};
        Product[] actual = manager.findAll();
        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSaveProductNoBookNoSmart() {
        prepare();
        manager.add(product11);
        Product[] expected = {product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11};
        Product[] actual = manager.findAll();
        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldNullIfNoSaveProduct() {
        Product[] expected = {};
        Product[] actual = manager.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindText() {
        prepare();
        Product[] expected = {product1, product2, product3, product4, product5};
        Product[] actual = manager.searchBy("book");
        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldNotFindWhenNonExistentText() {
        prepare();
        Product[] expected = {};
        Product[] actual = manager.searchBy("Hat");
        assertArrayEquals(expected, actual);

    }
}
