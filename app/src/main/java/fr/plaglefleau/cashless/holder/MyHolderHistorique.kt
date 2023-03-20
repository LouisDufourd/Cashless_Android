package fr.plaglefleau.cashless.holder

import androidx.recyclerview.widget.RecyclerView
import fr.plaglefleau.cashless.databinding.ItemHistoriqueBinding
import fr.plaglefleau.cashless.models.Historique

class MyHolderHistorique(private val binding: ItemHistoriqueBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(historique: Any) {
        binding.historique = historique as Historique?
    }
}