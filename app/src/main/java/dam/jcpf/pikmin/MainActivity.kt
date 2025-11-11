package dam.jcpf.pikmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import dam.jcpf.pikmin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SnackBar de bienvenida
        Snackbar.make(binding.root, "¡Bienvenidos al mundo Pikmin!", Snackbar.LENGTH_SHORT).show()

        // Configurar toolbar como ActionBar
        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Cambio el título según el destino
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.listFragment -> {
                    supportActionBar?.title = "Pikmin"
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.detailsFragment -> {
                    supportActionBar?.title = "Detalle Pikmin"
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}