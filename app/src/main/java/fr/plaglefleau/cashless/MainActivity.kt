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
import fr.plaglefleau.cashless.models.input.*
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
        items.add("stockRemoveArticle")
        items.add("creditCard")
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
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditNumberInput(false, false),
                                    EditNumber2Input(false, false),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.getAllClient.text = "GET"
                        }
                        1 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true, true),
                                    EditNumberInput(false, false),
                                    EditNumber2Input(false, false),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textView.text = "CodeNFC"
                            binding.getAllClient.text = "GET"
                        }
                        2 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(true, true),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textViewID.text = "idStand"
                            binding.getAllClient.text = "GET"

                        }
                        3 -> {updateInput(
                            listOf(
                                EditTextInput(false, false),
                                EditNumberInput(true, true),
                                EditNumber2Input(false, false),
                                EditNumber3Input(false, false),
                                EditTextNumberDecimalInput(false, false)
                            ))
                            binding.textViewID.text = "idCarte"
                            binding.getAllClient.text = "DELETE"
                        }
                        4 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(false, false),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textViewID.text = "idClient"
                            binding.getAllClient.text = "DELETE"
                        }
                        5 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(false, false),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textViewID.text = "idStand"
                            binding.getAllClient.text = "DELETE"
                        }
                        6 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(true, true),
                                    EditNumber3Input(false, false),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textViewID.text = "idStock"
                            binding.textViewID2.text = "idStand"
                            binding.getAllClient.text = "DELETE"
                        }
                        7 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(false, false),
                                    EditNumberInput(true, true),
                                    EditNumber2Input(true, true),
                                    EditNumber3Input(true, true),
                                    EditTextNumberDecimalInput(false, false)
                                ))
                            binding.textViewID.text = "idStock"
                            binding.textViewID2.text = "idStand"
                            binding.textViewAmount.text = "amount"
                            binding.getAllClient.text = "DELETE"
                        }
                        8 -> {
                            updateInput(
                                listOf(
                                    EditTextInput(true,true),
                                    EditNumberInput(false,false),
                                    EditNumber2Input(false,false),
                                    EditNumber3Input(false,false),
                                    EditTextNumberDecimalInput(true,true)
                                )
                            )
                            binding.textView.text = "CodeNFC"
                            binding.textViewDecimal.text = "amount"
                            binding.getAllClient.text = "POST"
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

    //
    // This function updates the visibility and enabled state of input fields based on the list of Inputs provided
    fun updateInput(inputs: List<Input>) {
        // Loop through each input
        inputs.forEach { input ->
            // Use when expression to handle different types of input
            when (input) {
                // If input is EditTextInput, update the visibility and enabled state of corresponding fields
                is EditTextInput -> {
                    binding.editText.isVisible = input.isVisible
                    binding.editText.isEnabled = input.isEnabled
                    binding.textView.isVisible = input.isVisible
                }
                // If input is EditNumberInput, update the visibility and enabled state of corresponding fields
                is EditNumberInput -> {
                    binding.editTextNumber.isVisible = input.isVisible
                    binding.editTextNumber.isEnabled = input.isEnabled
                    binding.textViewID.isVisible = input.isVisible
                }
                // If input is EditNumber2Input, update the visibility and enabled state of corresponding fields
                is EditNumber2Input -> {
                    binding.editTextNumber2.isEnabled = input.isEnabled
                    binding.editTextNumber2.isVisible = input.isVisible
                    binding.textViewID2.isVisible = input.isVisible
                }
                // If input is EditNumber3Input, update the visibility and enabled state of corresponding fields
                is EditNumber3Input -> {
                    binding.editTextNumber3.isEnabled = input.isEnabled
                    binding.editTextNumber3.isVisible = input.isVisible
                    binding.textViewAmount.isVisible = input.isVisible
                }
                // If input is EditTextNumberDecimalInput, update the visibility and enabled state of corresponding fields
                is EditTextNumberDecimalInput -> {
                    binding.editTextNumberDecimal.isVisible = input.isVisible
                    binding.editTextNumberDecimal.isEnabled = input.isEnabled
                    binding.textViewDecimal.isVisible = input.isVisible
                }
            }
        }
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
                    7 -> {
                        if(binding.editTextNumber.text.toString().toIntOrNull() != null && binding.editTextNumber2.text.toString().toIntOrNull() != null && binding.editTextNumber3.text.toString().toIntOrNull() != null) {
                            API.api.stockRemoveArticle(binding.editTextNumber.text.toString().toInt(),binding.editTextNumber2.text.toString().toInt(), binding.editTextNumber3.text.toString().toInt())
                        } else {
                            Log.d("Cashless_Log", "can't convert to int")
                            null
                        }
                    }
                    8 -> {
                        if(binding.editTextNumberDecimal.text.toString().toDoubleOrNull() != null) {
                            API.api.cardCredit(binding.editTextNumberDecimal.text.toString(), binding.editTextNumberDecimal.text.toString().toDouble())
                        } else {
                            Log.d("Cashless_Log", "can't convert to double")
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
