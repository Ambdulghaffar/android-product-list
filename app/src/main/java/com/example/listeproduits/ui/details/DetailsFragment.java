package com.example.listeproduits.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.listeproduits.R;
import com.example.listeproduits.data.ProductRepository;
import com.example.listeproduits.model.Product;

import java.util.Locale;

public class DetailsFragment extends Fragment {

    private static final String ARG_PRODUCT_ID = "productId";

    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private TextView productCategory;
    private TextView productDescription;

    private ProductRepository repository;
    private int productId;

    public static DetailsFragment newInstance(int productId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = ProductRepository.getInstance();

        if (getArguments() != null) {
            productId = getArguments().getInt(ARG_PRODUCT_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productImage = view.findViewById(R.id.detailProductImage);
        productName = view.findViewById(R.id.detailProductName);
        productPrice = view.findViewById(R.id.detailProductPrice);
        productCategory = view.findViewById(R.id.detailProductCategory);
        productDescription = view.findViewById(R.id.detailProductDescription);

        // Bouton "Retour à la liste"
        View btnBack = view.findViewById(R.id.btnBackToList);
        if (btnBack != null) {
            btnBack.setOnClickListener(v ->
                    androidx.navigation.Navigation.findNavController(v).navigateUp()
            );
        }

        loadProductDetails();
    }


    private void loadProductDetails() {
        Product product = repository.getProductById(productId);

        if (product != null) {
            productImage.setImageResource(product.getImageResource());
            productName.setText(product.getName());
            productPrice.setText(String.format(Locale.getDefault(), "%.2f €", product.getPrice()));
            productCategory.setText(product.getCategory());
            productDescription.setText(product.getDescription());
        }
    }
}