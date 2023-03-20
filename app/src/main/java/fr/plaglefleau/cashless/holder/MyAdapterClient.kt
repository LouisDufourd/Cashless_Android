package fr.plaglefleau.cashless.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.plaglefleau.cashless.databinding.ItemClientBinding
import fr.plaglefleau.cashless.models.Utilisateur

class MyAdapterClient(private val allClient: ArrayList<Utilisateur>) : RecyclerView.Adapter<MyHolderClient>() {
    private lateinit var binding: ItemClientBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderClient {
        binding = ItemClientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolderClient(binding)
    }

    override fun getItemCount(): Int {
        return allClient.size
    }

    override fun onBindViewHolder(holder: MyHolderClient, position: Int) {
        holder.bind(allClient[position])
    }
}