package dam.jcpf.pikmin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import dam.jcpf.pikmin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Obtener los prefs
        val prefs = getSharedPreferences("ajustes", MODE_PRIVATE)
        val temaOscuro = prefs.getBoolean("temaOscuro", false)
        val idioma = prefs.getString("idioma", "es")?: "es"

        if (temaOscuro) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Aplicar idioma
        val locales = LocaleListCompat.forLanguageTags(idioma)
        AppCompatDelegate.setApplicationLocales(locales)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SnackBar de bienvenida
        Snackbar.make(binding.root, R.string.welcome, Snackbar.LENGTH_SHORT).show()

        // Configurar toolbar como ActionBar
        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Cambio el título según el destino
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.listFragment -> {
                    supportActionBar?.title = getString(R.string.app_name)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }

                R.id.detailsFragment -> {
                    supportActionBar?.title = getString(R.string.detailTitle)
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

    /**
     * Aquí inflamos el menú que va a estar en el Toolbar de la App
     *
     * @params menu que he creado con las opcines "Acerca de..." y "Ajustes"
     * @return `true` se ha inflado correctamente
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * Listener de los items del menu
     *
     * @params item del menú clickado
     * @return `true` se ha capturado correctamente
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_acercaDe -> {
            AlertDialog.Builder(this)
                .setTitle(R.string.acerca_de)
                .setMessage(R.string.acerca_de_message)
                .show()
            true
        }

        R.id.menu_ajustes -> {
            // Navegar a la segunda actividad
            val intent = Intent(this, Ajustes::class.java)
            startActivity(intent)
            true
        }

        android.R.id.home -> {
            onBackPressedDispatcher.onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}

