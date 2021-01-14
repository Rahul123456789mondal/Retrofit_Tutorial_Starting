package com.example.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.apiCall.ApiService
import com.example.retrofit.apiCall.apiInterface
import com.example.retrofit.model.Comments
import com.example.retrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity2 : AppCompatActivity() {

    val ApiData = ApiService.create().create(apiInterface::class.java)
    lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mTextView = findViewById(R.id.textView2)
        getComment()
        createPost()
    }

    fun getComment() {

        val call: Call<List<Comments>> = ApiData.getComments(2, "id", "desc")
        call.enqueue(object : Callback<List<Comments>> {
            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                Log.d("TAG",response.code().toString())
                if (response.isSuccessful) {
                    showComments(response.body())
                }
            }

        })
    }

    private fun showComments(body: List<Comments>?) {

        for (comment : Comments in body!!){
            mTextView.append("id :- "+comment.id+"\n")
            mTextView.append("postId :- "+comment.postId+"\n")
            mTextView.append("Name :- "+comment.name+"\n")
            mTextView.append("Email :- "+comment.email+"\n")
            mTextView.append("Body :- "+comment.body+"\n")
        }
    }

    fun createPost(){
        val post : Post = Post(23, null, "Arka", "I Am A Cricketer") // This line used for when the send the whole body
        val call = ApiData.getPostsRequest(2, "Arka Mondal", "I am a Big man") // This line use for to send data for single parameter data

        call.enqueue(object : Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable) {
                mTextView.text = t.message.toString()
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                mTextView.text = response.code().toString()
                showPost(response.body()!!)
            }
        })
    }

    private fun showPost(post : Post) {
            mTextView.append("id :- "+post.id+"\n")
            mTextView.append("UserId :- "+post.userId+"\n")
            mTextView.append("Title :- "+post.title+"\n")
            mTextView.append("Body :- "+post.body+"\n")
    }
}




































