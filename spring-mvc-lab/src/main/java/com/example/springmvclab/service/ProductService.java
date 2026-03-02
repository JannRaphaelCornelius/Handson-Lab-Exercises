package com.example.springmvclab.service;

import com.example.springmvclab.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        // ── Elektronik ──────────────────────────────────────────
        products.add(new Product(1L,  "Laptop ASUS VivoBook 15",   "Elektronik",  12_500_000, 15));
        products.add(new Product(2L,  "Mouse Logitech MX Master 3","Elektronik",     650_000, 50));
        products.add(new Product(3L,  "Headphone Sony WH-1000XM5", "Elektronik",   3_200_000, 12));
        products.add(new Product(4L,  "Keyboard Mechanical Keychron K2", "Elektronik", 1_150_000, 25));

        // ── Buku ────────────────────────────────────────────────
        products.add(new Product(5L,  "Buku Java Programming",     "Buku",           150_000, 30));
        products.add(new Product(6L,  "Novel Laskar Pelangi",       "Buku",            75_000, 45));
        products.add(new Product(7L,  "Clean Code - Robert Martin", "Buku",           220_000, 20));
        products.add(new Product(8L,  "Atomic Habits",              "Buku",           130_000, 60));

        // ── Makanan ─────────────────────────────────────────────
        products.add(new Product(9L,  "Kopi Arabica 250g",          "Makanan",         85_000, 100));
        products.add(new Product(10L, "Teh Hijau Organik 100g",     "Makanan",         55_000,  80));

        // ── Lifestyle ───────────────────────────────────────────
        products.add(new Product(11L, "Tote Bag Canvas Premium",    "Lifestyle",      120_000,  40));
        products.add(new Product(12L, "Jam Tangan Casio Vintage",   "Lifestyle",      450_000,  18));
        products.add(new Product(13L, "Tumbler Stainless 500ml",    "Lifestyle",       95_000,  55));

        // ── Home & Living ────────────────────────────────────────
        products.add(new Product(14L, "Lampu LED Aesthetic Warm",   "Home&Living",     75_000,  70));
        products.add(new Product(15L, "Diffuser Aroma Terapi",      "Home&Living",    210_000,  30));
        products.add(new Product(16L, "Pot Tanaman Minimalis",      "Home&Living",     65_000,  90));
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Product> findByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public List<Product> search(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    public List<String> getAllCategories() {
        return products.stream().map(Product::getCategory).distinct().sorted().toList();
    }

    public Map<String, Integer> getProductCountByCategory() {
        Map<String, Integer> stats = new HashMap<>();
        for (Product p : products) {
            stats.put(p.getCategory(), stats.getOrDefault(p.getCategory(), 0) + 1);
        }
        return stats;
    }

    public double getAveragePrice() {
        return products.stream().mapToDouble(Product::getPrice).average().orElse(0);
    }

    public Product getCheapestProduct() {
        return products.stream().min(Comparator.comparing(Product::getPrice)).orElse(null);
    }

    public Product getMostExpensiveProduct() {
        return products.stream().max(Comparator.comparing(Product::getPrice)).orElse(null);
    }

    public long getLowStockCount() {
        return products.stream().filter(p -> p.getStock() < 20).count();
    }
}