package dam.jcpf.pikmin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.jcpf.pikmin.databinding.CharacterItemBinding

class MyAdapter(
    private val characterList: List<CharacterPikmin>,
    private val onClick: (CharacterPikmin) -> Unit
): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bin(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    // Para el grid
    inner class MyViewHolder (private val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bin (character: CharacterPikmin){
            binding.chracterName.text = character.name
            binding.characterImage.setImageResource(character.getImage())

            binding.root.setOnClickListener {
                onClick(character)
            }
        }
    }
}