package com.example.retrofit.apiCall

import com.example.retrofit.model.Comments
import com.example.retrofit.model.Post
import retrofit2.Call
import retrofit2.http.*

interface apiInterface {

    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    //@GET("posts")
    //fun getPosts

    // In the Anotation type i will send the post request to the Api
      /*@POST("/posts")
        fun getPostsRequest(@Body post: Post): Call<Post>*/

    @FormUrlEncoded
    @POST("/posts")
    fun getPostsRequest(@Field("userId") userId : Int,
                        @Field("title") title : String,
                        @Field("body") body : String): Call<Post>

//   @GET("posts/{id}/comments")
//   fun getComments(@Path("id") userId : Int) : Call<List<Comments>>

    //@GET("comments")
    //fun getComments(@Query("postId") postId : Int) : Call<List<Comments>>


    // Query processing using url
    @GET("comments")
    fun getComments(@Query("postId") postid : Int,
                    @Query("_sort") sortBy : String,
                    @Query("_oder") oderBy : String) : Call<List<Comments>>

    @GET()
    fun getComments(@Url url : String)

    // ***** put ***** patch ***** delete
    @PUT("posts/{id}")
    fun putPost(@Path("id") id : Int, @Body post: Post)


}














































