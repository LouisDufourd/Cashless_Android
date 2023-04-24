package fr.plaglefleau.cashless

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.gson.GsonBuilder
import fr.plaglefleau.cashless.databinding.ActivityMainBinding
import fr.plaglefleau.cashless.models.*
import fr.plaglefleau.cashless.models.input.*
import fr.plaglefleau.cashless.retrofit.API
import kotlinx.coroutines.DelicateCoroutinesApi
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

    private fun setSpiner() {
        val items = ArrayList<String>()
        items.add("getSolde") //0
        items.add("getHistoric") //1
        items.add("deleteCard") //2
        items.add("clientUnsubscribe") //3
        items.add("standRemove") //4
        items.add("stockRemove") //5
        items.add("stockRemoveArticle") //6
        items.add("creditCard") //7
        items.add("debitCard") //8
        items.add("modifyCard") //9
        items.add("createCard") //10
        items.add("clientConnect") //11
        items.add("stockEdit") //12
        items.add("stockAddArticle") //13
        items.add("cardConnect") //14
        items.add("clientEdit") //15
        items.add("clientRegister") //16
        items.add("stockAdd") //17
        items.add("standAdd") //18
        items.add("standEdit") //19
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }

    private fun setListener() {

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    when(binding.spinner.selectedItemPosition) {
                        0 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true, true),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(false, false),
                                    EditNumber2Input(false, false),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textView.text = "CodeNFC"
                            binding.getAllClient.text = "GET"
                        }
                        1 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(false, false),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textViewID.text = "idStand"
                            binding.getAllClient.text = "GET"

                        }
                        2 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(false, false),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                )
                            )
                            binding.textViewID.text = "idCarte"
                            binding.getAllClient.text = "DELETE"
                        }
                        3 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(false, false),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textViewID.text = "idClient"
                            binding.getAllClient.text = "DELETE"
                        }
                        4 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(false, false),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textViewID.text = "idStand"
                            binding.getAllClient.text = "DELETE"
                        }
                        5 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(true, true),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textViewID.text = "idStock"
                            binding.textViewID2.text = "idStand"
                            binding.getAllClient.text = "DELETE"
                        }
                        6 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(true, true),
                                    EditNumber3Input(true, true),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textViewID.text = "idStock"
                            binding.textViewID2.text = "idStand"
                            binding.textViewAmount.text = "amount"
                            binding.getAllClient.text = "PUT"
                        }
                        7 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true,true),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(false,false),
                                    EditNumber2Input(false,false),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(true,true)
                                )
                            )
                            binding.textView.text = "CodeNFC"
                            binding.textViewDecimal.text = "amount"
                            binding.getAllClient.text = "PUT"
                        }
                        8 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true,true),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(false,false),
                                    EditNumber2Input(false,false),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(true,true)
                                )
                            )
                            binding.textView.text = "codeNFC"
                            binding.textViewDecimal.text = "amount"
                            binding.getAllClient.text = "PUT"
                        }
                        9 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true,true),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true,true),
                                    EditNumber2Input(true,true),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(true,true)
                                )
                            )
                            binding.textViewID.text = "ID"
                            binding.textViewID2.text = "PIN"
                            binding.textView.text = "CodeNFC"
                            binding.textViewDecimal.text = "amount"
                            binding.getAllClient.text = "POST"
                        }
                        10 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true,true),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true,true),
                                    EditNumber2Input(false,false),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(false,false)
                                )
                            )
                            binding.textViewID.text = "PIN"
                            binding.textView.text = "CodeNFC"
                            binding.getAllClient.text = "PUT"
                        }
                        11 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true,true),
                                    EditTextPasswordInput(true,true),
                                    EditNumberInput(false,false),
                                    EditNumber2Input(false,false),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(false,false)
                                )
                            )
                            binding.textView.text = "Username"
                            binding.textViewPassword.text = "Password"
                            binding.getAllClient.text = "GET"
                        }
                        12 -> {
                            stockAddOrEdit()
                            binding.getAllClient.text = "PUT"
                        }
                        13 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false,false),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true,true),
                                    EditNumber2Input(true,true),
                                    EditNumber3Input(true,true),
                                    EditTextNumberDecimalInput(false,false)
                                )
                            )
                            binding.textViewID.text = "IDStand"
                            binding.textViewID2.text = "IDArticle"
                            binding.textViewAmount.text = "amount"
                            binding.getAllClient.text = "PUT"
                        }
                        14 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false,false),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true,true),
                                    EditNumber2Input(true,true),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(false,false)
                                )
                            )
                            binding.textViewID.text = "IDUtilisateur"
                            binding.textViewID2.text = "PIN"
                            binding.getAllClient.text = "PUT"
                        }
                        15 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true,true),
                                    EditTextPasswordInput(true,true),
                                    EditNumberInput(true,true),
                                    EditNumber2Input(true,true),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(false,false)
                                )
                            )
                            binding.textViewID.text = "IDUtilisateur"
                            binding.textViewID2.text = "IDCarte"
                            binding.textView.text = "user"
                            binding.textViewPassword.text = "password"
                            binding.getAllClient.text = "PUT"
                        }
                        16 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true,true),
                                    EditTextPasswordInput(true,true),
                                    EditNumberInput(false,false),
                                    EditNumber2Input(false,false),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(false,false)
                                )
                            )
                            binding.textView.text = "user"
                            binding.textViewPassword.text = "password"
                            binding.getAllClient.text = "POST"
                        }
                        17 -> {
                            stockAddOrEdit()
                            binding.getAllClient.text = "POST"
                        }
                        18 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true,true),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(false,false),
                                    EditNumber2Input(false,false),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(false,false)
                                )
                            )
                            binding.textView.text = "standName"
                            binding.getAllClient.text = "POST"
                        }
                        19 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true,true),
                                    EditTextPasswordInput(false,false),
                                    EditNumberInput(true,true),
                                    EditNumber2Input(false,false),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(false,false)
                                )
                            )
                            binding.textView.text = "standName"
                            binding.textViewID.text = "idStand"
                            binding.getAllClient.text = "PUT"
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

    @SuppressLint("SetTextI18n")
    fun stockAddOrEdit() {
        updateInput(
            listOf(
                EditTextInput(false,false),
                EditTextPasswordInput(false,false),
                EditNumberInput(true,true),
                EditNumber2Input(true,true),
                EditNumber3Input(true,true),
                EditTextNumberDecimalInput(true,true)
            )
        )
        binding.textViewID.text = "IDStand"
        binding.textViewID2.text = "IDArticle"
        binding.textViewAmount.text = "amount"
        binding.textViewDecimal.text = "prix"
    }

    fun updateInput(inputs: List<Input>) {
        inputs.forEach { input ->
            when (input) {
                is EditTextInput -> {
                    binding.editText.isVisible = input.isVisible
                    binding.editText.isEnabled = input.isEnabled
                    binding.textView.isVisible = input.isVisible
                }
                is EditTextPasswordInput -> {
                    binding.editTextPassword.isVisible = input.isVisible
                    binding.editTextPassword.isEnabled = input.isEnabled
                    binding.textViewPassword.isVisible = input.isVisible
                }
                is EditNumberInput -> {
                    binding.editTextNumber.isVisible = input.isVisible
                    binding.editTextNumber.isEnabled = input.isEnabled
                    binding.textViewID.isVisible = input.isVisible
                }
                is EditNumber2Input -> {
                    binding.editTextNumber2.isEnabled = input.isEnabled
                    binding.editTextNumber2.isVisible = input.isVisible
                    binding.textViewID2.isVisible = input.isVisible
                }
                is EditNumber3Input -> {
                    binding.editTextNumber3.isEnabled = input.isEnabled
                    binding.editTextNumber3.isVisible = input.isVisible
                    binding.textViewAmount.isVisible = input.isVisible
                }
                is EditTextNumberDecimalInput -> {
                    binding.editTextNumberDecimal.isVisible = input.isVisible
                    binding.editTextNumberDecimal.isEnabled = input.isEnabled
                    binding.textViewDecimal.isVisible = input.isVisible
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getButton() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = when (binding.spinner.selectedItemPosition) {
                    0 -> API.api.getSolde(binding.editText.text.toString())
                    1 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null) {
                            API.api.getHistorique(binding.editTextNumber.text.toString().toInt())
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    2 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null) {
                            API.api.deleteCard(
                                Carte(
                                    binding.editTextNumber.text.toString().toInt(),
                                    null,
                                    null,
                                    null
                                )
                            )
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    3 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null) {
                            API.api.clientUnsubscribe(
                                Utilisateur(
                                    binding.editTextNumber.text.toString().toInt(),
                                    null,
                                    null,
                                    null
                                )
                            )
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    4 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null) {
                            API.api.standRemove(
                                Stand(
                                    binding.editTextNumber.text.toString().toInt(),
                                    null
                                )
                            )
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    5 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editTextNumber2.text.toString().toIntOrNull() != null) {
                            API.api.stockRemove(
                                Stock(
                                    Stand(
                                        binding.editTextNumber.text.toString().toInt(),
                                        null
                                    ),
                                    Article(
                                        binding.editTextNumber2.text.toString().toInt(),
                                        null
                                    ),
                                    null,
                                    null
                                )
                            )
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    6 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editTextNumber2.text.toString().toIntOrNull() != null && binding.editTextNumber3.text.toString().toIntOrNull() != null) {
                            API.api.stockRemoveArticle(
                                Stock(
                                    Stand(
                                        binding.editTextNumber.text.toString().toInt(),
                                        null
                                    ),
                                    Article(
                                        binding.editTextNumber2.text.toString().toInt(),
                                        null
                                    ),
                                    binding.editTextNumber3.text.toString().toInt(),
                                    null
                                )
                            )
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    7 -> {
                        if(binding.editTextNumberDecimal.text.toString().toDoubleOrNull() != null) {
                            API.api.cardCredit(
                                Carte(
                                    null,
                                    null,
                                    binding.editTextNumberDecimal.text.toString().toDouble(),
                                    binding.editText.text.toString()
                                )
                            )
                        } else {
                            Log.d("Cashless_Log", "can't convert to double")
                            null
                        }
                    }
                    8 -> {
                        if(binding.editText.text.toString().isNotEmpty() && binding.editTextNumberDecimal.text.toString().toDoubleOrNull() != null) {
                            API.api.cardDebit(
                                Carte(
                                    null,
                                    null,
                                    binding.editTextNumberDecimal.text.toString().toDoubleOrNull(),
                                    binding.editText.text.toString()
                                )
                            )
                        } else {
                            null
                        }
                    }
                    9 -> {
                        if(binding.editTextNumberDecimal.text.toString().toDoubleOrNull() != null && binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editTextNumber2.text.toString().toIntOrNull() != null) {
                            API.api.modifyCard(
                                Carte(
                                    binding.editTextNumber.text.toString().toInt(),
                                    binding.editTextNumber2.text.toString().toInt(),
                                    binding.editTextNumberDecimal.text.toString().toDouble(),
                                    binding.editText.text.toString()
                                )
                            )
                        } else {
                            Log.d("Cashless_Log", "can't convert")
                            null
                        }
                    }
                    10 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editText.text.toString().isNotEmpty()) {
                            API.api.createCard(
                                Carte(
                                    null,
                                    binding.editTextNumber.text.toString().toInt(),
                                    null,
                                    binding.editText.text.toString()
                                )
                            )
                        } else {
                            null
                        }
                    }
                    11 -> {
                        if(binding.editText.text.toString().isNotEmpty() && binding.editTextPassword.text.toString().isNotEmpty()) {
                            API.api.clientConnect(binding.editText.text.toString(),binding.editTextPassword.text.toString())
                        } else {
                            null
                        }
                    }
                    12 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editTextNumber2.text.toString().toIntOrNull() != null && binding.editTextNumber3.text.toString().toIntOrNull() != null && binding.editTextNumberDecimal.text.toString().toDoubleOrNull() != null) {
                            API.api.stockEdit(Stock(Stand(binding.editTextNumber.text.toString().toInt(),null),Article(binding.editTextNumber2.text.toString().toInt(),null),binding.editTextNumber3.text.toString().toInt(),binding.editTextNumberDecimal.text.toString().toDouble()))
                        } else {
                            null
                        }
                    }
                    13 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editTextNumber2.text.toString().toIntOrNull() != null && binding.editTextNumber3.text.toString().toIntOrNull() != null) {
                            API.api.stockAddArticle(Stock(Stand(binding.editTextNumber.text.toString().toInt(),null),Article(binding.editTextNumber2.text.toString().toInt(),null),binding.editTextNumber3.text.toString().toInt(),null))
                        } else {
                            null
                        }
                    }
                    14 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editTextNumber2.text.toString().toIntOrNull() != null) {
                            API.api.cardConnect(Utilisateur(binding.editTextNumber.text.toString().toInt(),Carte(null,binding.editTextNumber2.text.toString().toInt(),null,null),null,null))
                        } else {
                            null
                        }
                    }
                    15 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editText.text.toString().isNotEmpty() && binding.editTextPassword.text.toString().isNotEmpty()) {
                            if(binding.editTextNumber2.text.toString().toIntOrNull() != null) {
                                API.api.clientEdit(Utilisateur(binding.editTextNumber.text.toString().toInt(),Carte(binding.editTextNumber2.text.toString().toInt(),null,null,null),binding.editText.text.toString(),binding.editTextPassword.text.toString()))
                            } else {
                                API.api.clientEdit(Utilisateur(binding.editTextNumber.text.toString().toInt(),null,binding.editText.text.toString(),binding.editTextPassword.text.toString()))
                            }
                        } else {
                            null
                        }
                    }
                    16 -> {
                        if(binding.editText.text.toString().isNotEmpty() && binding.editTextPassword.text.toString().isNotEmpty()) {
                            API.api.clientRegister(Utilisateur(null,null,binding.editText.text.toString(),binding.editTextPassword.text.toString()))
                        } else {
                            null
                        }
                    }
                    17 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editTextNumber2.text.toString().toIntOrNull() != null && binding.editTextNumber3.text.toString().toIntOrNull() != null && binding.editTextNumberDecimal.text.toString().toDoubleOrNull() != null) {
                            API.api.stockAdd(Stock(Stand(binding.editTextNumber.text.toString().toInt(),null),Article(binding.editTextNumber2.text.toString().toInt(),null), binding.editTextNumber3.text.toString().toInt(),binding.editTextNumberDecimal.text.toString().toDouble()))
                        } else {
                            null
                        }
                    }
                    18 -> {
                        if(binding.editText.text.toString().isNotEmpty()) {
                            API.api.standAdd(Stand(null,binding.editText.text.toString()))
                        } else {
                            null
                        }
                    }
                    19 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editText.text.toString().isNotEmpty()) {
                             API.api.standEdit(Stand(binding.editTextNumber.text.toString().toInt(),binding.editText.text.toString()))
                        } else {
                            null
                        }
                    }
                    else -> {
                        Log.d("Cashless_Log", "member in the list don't exist")
                        null
                    }
                }
                if(response!= null) {
                    if (response.isSuccessful && response.body() != null && binding.getAllClient.text.toString().contentEquals("GET",ignoreCase = true)) {
                        val intent = Intent(this@MainActivity, RequestResultActivity::class.java)
                        val gson = GsonBuilder().create()
                        intent.putExtra("body", gson.toJson(response.body()))
                        intent.putExtra("request", binding.spinner.selectedItemPosition)
                        startActivity(intent)
                    } else {
                        Log.d("Cashless_Log", "GetButton :\n${response.code()} : ${response.message()}")
                        Toast.makeText(this@MainActivity,"${response.code()} : ${response.message()}",Toast.LENGTH_LONG).show()
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
