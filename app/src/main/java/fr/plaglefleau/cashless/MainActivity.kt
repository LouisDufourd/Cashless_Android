package fr.plaglefleau.cashless

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.gson.GsonBuilder
import fr.plaglefleau.cashless.databinding.ActivityMainBinding
import fr.plaglefleau.cashless.retrofit.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSpiner()
        setListener()
    }

    fun setSpiner() {
        val items = ArrayList<String>()
        items.add("getAllClient")
        items.add("getSolde")
        items.add("getHistoric")
        items.add("deleteCard")
        items.add("clientUnsubscribe")
        items.add("standRemove")
        items.add("stockRemove")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }

    fun setListener() {

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    when(binding.spinner.selectedItemPosition) {
                        0 -> {
                            updateInput(false,false,false,false, false, false)
                            binding.getAllClient.text = "GET"
                        }
                        1 -> {
                            updateInput(true,false,false,true, false, false)
                            binding.textView.text = "CodeNFC"
                            binding.getAllClient.text = "GET"
                        }
                        2 -> {updateInput(false,true,true,false, false, false)
                            binding.textViewID.text = "idStand"
                            binding.getAllClient.text = "GET"
                        }
                        3 -> {
                            updateInput(false,true,true,false, false, false)
                            binding.textViewID.text = "idCarte"
                            binding.getAllClient.text = "DELETE"
                        }
                        4 -> {
                            updateInput(false,true,true,false, false, false)
                            binding.textViewID.text = "idClient"
                            binding.getAllClient.text = "DELETE"
                        }
                        5 -> {
                            updateInput(false,true,true,false, false, false)
                            binding.textViewID.text = "idStand"
                            binding.getAllClient.text = "DELETE"
                        }
                        6 -> {
                            updateInput(false,true,true,false, true, true)
                            binding.textViewID.text = "idStock"
                            binding.textViewID2.text = "idStand"
                            binding.getAllClient.text = "DELETE"
                        }
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    binding.spinner.setSelection(0)
                }
            }
            binding.getAllClient.setOnClickListener {
            getButton()
        }
    }

    fun updateInput(editText: Boolean, editNumber:Boolean, textViewID:Boolean, textView:Boolean, textViewID2: Boolean, editNumber2: Boolean) {
        binding.editText.isVisible = editText
        binding.editText.isEnabled = editText
        binding.editTextNumber.isVisible = editNumber
        binding.editTextNumber.isEnabled = editNumber
        binding.textViewID.isVisible = textViewID
        binding.textView.isVisible = textView
        binding.editTextNumber2.isEnabled = editNumber2
        binding.editTextNumber2.isVisible = editNumber2
        binding.textViewID2.isVisible = textViewID2
    }

    fun getButton() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = when (binding.spinner.selectedItemPosition) {
                    0 -> API.api.getAllClients()
                    1 -> API.api.getSolde(binding.editText.text.toString())
                    2 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null) {
                            API.api.getHistorique(binding.editTextNumber.text.toString().toInt())
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    3 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null) {
                            API.api.deleteCard(binding.editTextNumber.text.toString().toInt())
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    4 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null) {
                            API.api.clientUnsubscribe(binding.editTextNumber.text.toString().toInt())
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    5 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null) {
                            API.api.standRemove(binding.editTextNumber.text.toString().toInt())
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    6 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editTextNumber2.text.toString().toIntOrNull() != null) {
                            API.api.stockRemove(binding.editTextNumber.text.toString().toInt(),binding.editTextNumber2.text.toString().toInt())
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    else -> {
                        Log.d("Cashless_Log", "member in the list don't exist")
                        null
                    }
                }
                if(response!= null) {
                    if (response?.isSuccessful == true && response.body() != null) {
                        val intent = Intent(this@MainActivity, RequestResultActivity::class.java)
                        val gson = GsonBuilder().create()
                        intent.putExtra("body", gson.toJson(response.body()))
                        intent.putExtra("request", binding.spinner.selectedItemPosition)
                        startActivity(intent)
                    } else {
                        Log.d("Cashless_Log", "GetButton :\n${response.code()} : ${response.message()}")
                        Toast.makeText(this@MainActivity, "${response.code()} : ${response.message()}", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Log.d("Cashless_Log", "no response")
                }
            } catch (e: Exception) {
                Log.e("Cashless_Log", "getButton: ", e)
            }
        }
    }
}
