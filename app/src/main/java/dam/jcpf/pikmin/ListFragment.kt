package dam.jcpf.pikmin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dam.jcpf.pikmin.databinding.FragmentListBinding
import java.io.BufferedReader

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pikminRecyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.pikminRecyclerview.adapter =
            MyAdapter(loadCharacterFromJson()) { seletedCharacter ->
                val bundle = Bundle()
                bundle.putInt("image", seletedCharacter.getImage())
                bundle.putString("type", seletedCharacter.type)
                bundle.putString("description", seletedCharacter.description)
                bundle.putString("skills", seletedCharacter.skills)
                findNavController().navigate(R.id.detailsFragment, bundle)
            }
    }

    private fun loadCharacterFromJson(): List<CharacterPikmin> {
        val inputStream = resources.openRawResource(R.raw.pikmins)
        val jsonString = inputStream.bufferedReader().use(BufferedReader::readText)

        val listType = object : TypeToken<List<CharacterPikmin>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}