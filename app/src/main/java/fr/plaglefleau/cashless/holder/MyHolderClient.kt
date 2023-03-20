package fr.plaglefleau.cashless.holder

import androidx.recyclerview.widget.RecyclerView
import fr.plaglefleau.cashless.databinding.ItemClientBinding
import fr.plaglefleau.cashless.models.Utilisateur

class MyHolderClient(private val binding: ItemClientBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(client: Any) {
        binding.client = client as Utilisateur?
    }
}