package com.example.razorpayapikotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.razorpayapikotlin.databinding.ActivityAddDataBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class add_data_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submit.setOnClickListener {
            addUser()
        }
    }

    @SuppressLint("CheckResult")
    private fun addUser() {
        val name = binding.userName.text.toString()
        val email = binding.userEmail.text.toString()
        val contact = binding.userContact.text.toString()

        val postData = PostDataModel(
            contact,
            email,
            name,
            gstin = "12ABCDE2356F7GH",
            fail_existing = "1",
            Notes(notes_key_1 = "Tea, Earl Grey, Hot", notes_key_2 = "Tea, Earl Grey… decaf.")
        )
        val apikey = "rzp_test_LZK1b1TZTDQmyw"
        val apiSecret = "fyDTsnjZhuTe6tyjDxrUFQew"

        val authenticator = "Basic " + android.util.Base64.encodeToString(
            "$apikey:$apiSecret".toByteArray(),
            android.util.Base64.NO_WRAP
        )

        val headerMap = HashMap<String, String>()
        headerMap["Authorization"] = authenticator

        RazorpayApi.creatretrofit().postCustomer(headerMap, postData)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result ->
                        Toast.makeText(
                            this,
                            "Data added successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    finish()
                    })
                { error ->
                    Toast.makeText(
                        this,
                        "Error: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

    }
}