package com.example.listeproduits.data;

import com.example.listeproduits.R;
import com.example.listeproduits.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static ProductRepository instance;
    private List<Product> products;

    private ProductRepository() {
        initializeProducts();
    }

    public static synchronized ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    private void initializeProducts() {
        products = new ArrayList<>();

        products.add(new Product(1, "Smartphone Galaxy",
                "Écran AMOLED 6.5 pouces, 128 Go de stockage, appareil photo 48MP, batterie 4500mAh",
                699.99, "Électronique", android.R.drawable.ic_menu_camera));

        products.add(new Product(2, "Laptop ProBook",
                "Intel i7, 16 Go RAM, SSD 512 Go, écran 15.6 pouces Full HD",
                1299.99, "Informatique", android.R.drawable.ic_menu_compass));

        products.add(new Product(3, "Casque Audio Premium",
                "Réduction de bruit active, autonomie 30h, Bluetooth 5.0, son Hi-Fi",
                249.99, "Audio", android.R.drawable.ic_menu_camera));

        products.add(new Product(4, "Montre Connectée",
                "Suivi fitness, GPS intégré, étanche 50m, autonomie 7 jours",
                349.99, "Accessoires", android.R.drawable.ic_menu_compass));

        products.add(new Product(5, "Tablette Tab Pro",
                "Écran 11 pouces, 256 Go, stylet inclus, processeur octa-core",
                599.99, "Informatique", android.R.drawable.ic_menu_camera));

        products.add(new Product(6, "Appareil Photo Reflex",
                "24MP, vidéo 4K, stabilisation d'image, objectif 18-55mm",
                899.99, "Photo", android.R.drawable.ic_menu_camera));

        products.add(new Product(7, "Enceinte Bluetooth",
                "Son 360°, étanche IPX7, autonomie 12h, assistant vocal",
                129.99, "Audio", android.R.drawable.ic_menu_compass));

        products.add(new Product(8, "Clavier Mécanique RGB",
                "Switchs mécaniques, rétroéclairage RGB, anti-ghosting",
                159.99, "Accessoires", android.R.drawable.ic_menu_camera));

        products.add(new Product(9, "Souris Gaming",
                "Capteur 16000 DPI, 8 boutons programmables, éclairage RGB",
                79.99, "Accessoires", android.R.drawable.ic_menu_compass));

        products.add(new Product(10, "Écran 27\" 2K",
                "Résolution 2560x1440, 144 Hz, temps de réponse 1 ms",
                349.99, "Informatique", android.R.drawable.ic_menu_camera));

        products.add(new Product(11, "Disque SSD externe",
                "1 To, USB‑C 3.2, résistant aux chocs",
                189.99, "Stockage", android.R.drawable.ic_menu_compass));

        products.add(new Product(12, "Routeur Wi‑Fi 6",
                "Débit jusqu'à 3 Gbit/s, couverture longue portée",
                229.99, "Réseau", android.R.drawable.ic_menu_camera));

        products.add(new Product(13, "Webcam Full HD",
                "1080p, micro intégré, idéale pour visioconférence",
                59.99, "Accessoires", android.R.drawable.ic_menu_compass));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> filtered = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                filtered.add(product);
            }
        }
        return filtered;
    }
}