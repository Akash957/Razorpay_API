package com.example.razorpayapikotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val userList = ArrayList<Item>()
    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var post_button: FloatingActionButton

    @SuppressLint("MissingInflatedId", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycleView)
        post_button=findViewById(R.id.post_button)


    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("CheckResult")
    fun getData(){
        val apiKey = "rzp_test_LZK1b1TZTDQmyw"
        val apiSecret = "fyDTsnjZhuTe6tyjDxrUFQew"

        val credentials = "$apiKey:$apiSecret"
        val authHeader = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

        val headerMap = HashMap<String, String>()
        headerMap["Authorization"] = authHeader

        RazorpayApi.creatretrofit().getCustomer(headerMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                val allData = result.items
                if (!allData.isNullOrEmpty()) {
                    userList.addAll(allData)
                    adapter = UserAdapter(this, userList)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this)
                } else {
                    Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
                }
            }, { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            })
        post_button.setOnClickListener {
            startActivity(Intent(this, add_data_Activity::class.java))
        }
    }

}
