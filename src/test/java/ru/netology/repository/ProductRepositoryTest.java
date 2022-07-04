package ru.netology.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import javax.management.StringValueExp;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {


    ProductRepository repo = new ProductRepository();

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
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.save(product4);
        repo.save(product5);
        repo.save(product6);
        repo.save(product7);
        repo.save(product8);
        repo.save(product9);
        repo.save(product10);
    }

    @Test
    public void shouldSaveProductAndFindAll() {
        prepare();
        repo.save(product11);
        Product[] expected = {product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSaveProductNoBookNoSmart() {
        prepare();
        repo.save(product11);
        Product[] expected = {product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11};
        Product[] actual = repo.getProducts();
        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldNullIfNoSaveProduct() {
        Product[] expected = {};
        Product[] actual = repo.getProducts();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindById() {
        prepare();
        Product[] expected = {product3};
        Product[] actual = repo.findById(33);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNullIfFindByNotExistingId() {
        prepare();
        Product[] expected = null;
        Product[] actual = repo.findById(2);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByValidId() {
        prepare();
        Product[] expected = {product1, product2, product3, product4, product5, product6, product7, product8, product10};
        Product[] actual = repo.removeById(39);
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "-1",
            "60"
    })
    public void shouldThrowReportIfTryRemoveByNotExistingId(int id) {
        prepare();
        String expected = "Element with id: " + id + " not found";
        assertThrows(NotFoundException.class, () -> {
            repo.removeById(id);
        });
    }
}
