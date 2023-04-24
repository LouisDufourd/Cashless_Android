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
import fr.plaglefleau.cashless.models.Utilisateur
import fr.plaglefleau.cashless.models.response.carte.CardBalanceResponse
import fr.plaglefleau.cashless.models.response.stand.StandHistoriqueResponse
import fr.plaglefleau.cashless.models.response.utilisateur.ClientConnectResponse

class RequestResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityRequestResultBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_result)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_request_result)


        when(intent.extras?.get("request")) {
            0 -> {
                val carte = Gson().fromJson(intent.extras?.get("body").toString(), CardBalanceResponse::class.java)
                binding.textView.isVisible = true
                binding.recyclerView.isVisible = false
                Log.d("Cashless_Log","${carte.cardBalance}")
                binding.textView.text = "${carte.cardBalance}"
            }
            1-> {
                val historiques = Gson().fromJson(intent.extras?.get("body").toString(),StandHistoriqueResponse::class.java)
                binding.textView.isVisible = false
                val recyclerViewNews = binding.recyclerView
                val newsAdapter = MyAdapterHistorique(historiques.historiques!!)
                recyclerViewNews.adapter = newsAdapter
                val layoutManager = LinearLayoutManager(parent, LinearLayoutManager.VERTICAL, false)
                recyclerViewNews.layoutManager = layoutManager
            }
            11 -> {
                val result = Gson().fromJson(intent.extras?.get("body").toString(),ClientConnectResponse::class.java)
                binding.textView.isVisible = true
                binding.recyclerView.isVisible = false
                binding.textView.text = "responseString = ${result.responseString}\nisGoodLogin = ${result.isGoodLogin}"
            }
        }
    }
}