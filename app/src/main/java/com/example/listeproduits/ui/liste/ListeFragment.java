package com.example.listeproduits.ui.liste;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listeproduits.R;
import com.example.listeproduits.adapter.ProductAdapter;
import com.example.listeproduits.data.ProductRepository;
import com.example.listeproduits.model.Product;
import com.example.listeproduits.ui.details.DetailsFragment;

public class ListeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ProductRepository repository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = ProductRepository.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liste, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ProductAdapter(product -> {
            // Vérifier si on est en mode tablette (deux panneaux)
            View detailsContainer = getActivity().findViewById(R.id.detailsContainer);

            if (detailsContainer != null && detailsContainer.getVisibility() == View.VISIBLE) {
                // Mode tablette : remplacer le fragment de détails
                showDetailsInContainer(product);
            } else {
                // Mode téléphone : naviguer vers une nouvelle page
                navigateToDetails(product);
            }
        });

        recyclerView.setAdapter(adapter);
        loadProducts();
    }

    private void loadProducts() {
        adapter.setProducts(repository.getAllProducts());
    }

    private void showDetailsInContainer(Product product) {
        DetailsFragment detailsFragment = DetailsFragment.newInstance(product.getId());
        getParentFragmentManager().beginTransaction()
                .replace(R.id.detailsContainer, detailsFragment)
                .commit();
    }

    private void navigateToDetails(Product product) {
        Bundle bundle = new Bundle();
        bundle.putInt("productId", product.getId());
        Navigation.findNavController(getView())
                .navigate(R.id.action_listeFragment_to_detailsFragment, bundle);
    }
}