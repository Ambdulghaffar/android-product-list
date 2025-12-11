package com.example.listeproduits;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.listeproduits.ui.details.DetailsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private NavController navController;
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate - Démarrage");

        // Vérifier si on est en mode tablette (deux panneaux)
        View detailsContainer = findViewById(R.id.detailsContainer);
        isTwoPane = detailsContainer != null && detailsContainer.getVisibility() == View.VISIBLE;

        Log.d(TAG, "Mode tablette: " + isTwoPane);

        // Configuration de la navigation pour le mode téléphone uniquement
        if (!isTwoPane) {
            setupPhoneMode();
        } else {
            setupTabletMode();
        }
    }

    private void setupPhoneMode() {
        // Configurer la Toolbar (ActionBar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            Log.d(TAG, "Toolbar configurée");
        }

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            Log.d(TAG, "NavController obtenu");

            // Seulement si la toolbar existe
            if (toolbar != null) {
                AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                        navController.getGraph()
                ).build();

                NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
                Log.d(TAG, "NavigationUI configuré");
            }


        } else {
            Log.e(TAG, "NavHostFragment est NULL !");
        }
    }

    private void setupTabletMode() {
        // Mode tablette : afficher un fragment vide par défaut
        if (getSupportFragmentManager().findFragmentById(R.id.detailsContainer) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailsContainer, new DetailsFragment())
                    .commit();
        }

        // Gérer le bouton retour en mode tablette
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d(TAG, "Mode tablette - Fermeture de l'app");
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.d(TAG, "onSupportNavigateUp - Bouton flèche cliqué");
        if (!isTwoPane && navController != null) {
            return navController.navigateUp() || super.onSupportNavigateUp();
        }
        return super.onSupportNavigateUp();
    }

}