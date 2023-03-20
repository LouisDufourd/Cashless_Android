package fr.plaglefleau.cashless.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.plaglefleau.cashless.databinding.ItemHistoriqueBinding
import fr.plaglefleau.cashless.models.Historique

class MyAdapterHistorique(private val allHistorique: ArrayList<Historique>) : RecyclerView.Adapter<MyHolderHistorique>() {
    private lateinit var binding: ItemHistoriqueBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderHistorique {
        binding = ItemHistoriqueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolderHistorique(binding)
    }

    override fun getItemCount(): Int {
        return allHistorique.size
    }

    override fun onBindViewHolder(holder: MyHolderHistorique, position: Int) {
        holder.bind(allHistorique[position])
    }
}