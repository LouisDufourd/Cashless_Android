package fr.plaglefleau.cashless

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.plaglefleau.cashless.databinding.ActivityRequestResultBinding
import fr.plaglefleau.cashless.holder.MyAdapterClient
import fr.plaglefleau.cashless.holder.MyAdapterHistorique
import fr.plaglefleau.cashless.models.Historique
import fr.plaglefleau.cashless.models.Utilisateur

class RequestResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityRequestResultBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_result)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_request_result)


        when(intent.extras?.get("request")) {
            0 -> {
                binding.textView.isVisible = false

                val utilisateurs = Gson().fromJson(intent.extras?.get("body").toString(), Array<Utilisateur>::class.java).toList() as ArrayList<Utilisateur>
                Log.d("Cashless_Log", intent.extras?.get("body").toString())

                val recyclerViewNews = binding.recyclerView
                val newsAdapter = MyAdapterClient(utilisateurs)
                recyclerViewNews.adapter = newsAdapter
                val layoutManager = LinearLayoutManager(parent, LinearLayoutManager.VERTICAL, false)
                recyclerViewNews.layoutManager = layoutManager
            }
            1 -> {
                val amount = Gson().fromJson(intent.extras?.get("body").toString(), Double::class.java)
                binding.textView.isVisible = true
                binding.recyclerView.isVisible = false
                Log.d("Cashless_Log","$amount")
                binding.textView.text = "$amount"
            }
            2-> {
                val historiques = Gson().fromJson(intent.extras?.get("body").toString(),Array<Historique>::class.java).toList() as ArrayList<Historique>
                binding.textView.isVisible = false
                val recyclerViewNews = binding.recyclerView
                val newsAdapter = MyAdapterHistorique(historiques)
                recyclerViewNews.adapter = newsAdapter
                val layoutManager = LinearLayoutManager(parent, LinearLayoutManager.VERTICAL, false)
                recyclerViewNews.layoutManager = layoutManager
            }
            3 -> {
                val result = Gson().fromJson(intent.extras?.get("body").toString(),String::class.java)
                binding.textView.isVisible = true
                binding.recyclerView.isVisible = false
                binding.textView.text = result
            }
        }
    }
}