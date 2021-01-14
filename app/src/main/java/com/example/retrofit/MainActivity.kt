package com.example.retrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.Adapter.DataAdpter
import com.example.retrofit.apiCall.ApiService
import com.example.retrofit.apiCall.apiInterface
import com.example.retrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var adapter:DataAdpter? = null
    val dataList = ArrayList<Post>()
    val ApiData = ApiService.create().create(apiInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)

        getData()
        adapter = DataAdpter(dataList,this)
        recyclerView.adapter= adapter
        recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun getData(){

        val call : Call<List<Post>> = ApiData.getPosts()

        call.enqueue(object  : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("Fail", t.message.toString())
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    dataList.addAll(response.body()!!)
                    adapter?.notifyDataSetChanged()
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_comments -> {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "click on Comments", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}








































