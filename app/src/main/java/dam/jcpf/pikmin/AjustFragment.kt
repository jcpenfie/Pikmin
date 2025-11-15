package dam.jcpf.pikmin

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import dam.jcpf.pikmin.databinding.FragmentAjustBinding
import androidx.core.content.edit
import androidx.core.os.LocaleListCompat

class AjustFragment : Fragment() {

    private var _binding: FragmentAjustBinding? = null
    private lateinit var prefs: SharedPreferences
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAjustBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Configuro las preferencias que voy a guardar
        prefs = requireActivity().getSharedPreferences("ajustes", MODE_PRIVATE)

        // Switches
        // Obtengo el switch del tema oscuro
        val idiomaPrefs = prefs.getString("idioma", "es")
        binding.languajeSwitch.isChecked = (idiomaPrefs == "en")


        binding.languajeSwitch.setOnCheckedChangeListener { _, isChecked ->
            val idioma = if (isChecked) "en" else "es"
            prefs.edit { putString("idioma", idioma) }

            // Aplica idioma y recarga el activity para que cambien los textos
            val locales = LocaleListCompat.forLanguageTags(idioma)
            AppCompatDelegate.setApplicationLocales(locales)
        }

        // Tema oscuro
        // Obtengo el switch del tema oscuro
        val temaOscuro = prefs.getBoolean("temaOscuro", false)
        // Recupero y aplico el estado según el valor del switch del tema ya guardado (o sin guardar)
        binding.temaSwitch.isChecked = temaOscuro

        // Listener para swtich de tema oscuro
        binding.temaSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit { putBoolean("temaOscuro", isChecked) }
            binding.temaSwitch.text = if(isChecked) getString(R.string.temaOscuro) else getString(R.string.temaClaro)
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}