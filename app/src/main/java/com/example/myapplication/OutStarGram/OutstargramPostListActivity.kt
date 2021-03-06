package com.example.myapplication.OutStarGram

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.myapplication.R
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_outstargram_post_list.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class OutstargramPostListActivity : AppCompatActivity() {
    lateinit var glide: RequestManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_outstargram_post_list)

        glide = Glide.with(this)

        postList()

        user_info.setOnClickListener {
            startActivity(
                Intent(
                    this@OutstargramPostListActivity,
                    OutstargramUserInfoActivity::class.java
                )
            )
        }
        my_list.setOnClickListener {
            startActivity(
                Intent(
                    this@OutstargramPostListActivity,
                    OutstargramMyPostListActivity::class.java
                )
            )
        }
        upload.setOnClickListener {
            startActivity(
                Intent(
                    this@OutstargramPostListActivity,
                    OutstargramUploadActivity::class.java
                )
            )
        }

        change_sort.setOnClickListener {
            sortPostList()
        }

        change_original.setOnClickListener {
            postList()
        }

    }

    /* fun changeRecycle(postList: ArrayList<Post>){
         postList.sortBy { it.content }
         val adapter = PostAdapter(
             postList!!,
             LayoutInflater.from(this@OutstargramPostListActivity),
             glide
         )
         post_recyclerview.adapter = adapter
         post_recyclerview.layoutManager =
             LinearLayoutManager(this@OutstargramPostListActivity)
         }

     fun changeOriginal(postList: ArrayList<Post>){
         val adapter = PostAdapter(
             postList!!,
             LayoutInflater.from(this@OutstargramPostListActivity),
             glide
         )
         post_recyclerview.adapter = adapter
         post_recyclerview.layoutManager =
             LinearLayoutManager(this@OutstargramPostListActivity)
     }

     fun savePostList(postList: ArrayList<Post>){
         val sp = getSharedPreferences("postList_sp",Context.MODE_PRIVATE)
         val editor = sp.edit()
         val gson = Gson()
         val json = gson.toJson(postList)
         editor.putString("postList_sp",json)
         editor.commit()
     }*/

    fun postList() {
        (application as MasterApplication).service.getAllPosts()
            .enqueue(object : Callback<ArrayList<Post>> {
                override fun onResponse(
                    call: Call<ArrayList<Post>>,
                    response: Response<ArrayList<Post>>
                ) {
                    if (response.isSuccessful) {
                        val postList = response.body()
                        val adapter = PostAdapter(
                            postList!!,
                            LayoutInflater.from(this@OutstargramPostListActivity),
                            glide
                        )
                        post_recyclerview.adapter = adapter
                        post_recyclerview.layoutManager =
                            LinearLayoutManager(this@OutstargramPostListActivity)
                    }
                }

                override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun sortPostList() {
        (application as MasterApplication).service.getAllPosts()
            .enqueue(object : Callback<ArrayList<Post>> {
                override fun onResponse(
                    call: Call<ArrayList<Post>>,
                    response: Response<ArrayList<Post>>
                ) {
                    if (response.isSuccessful) {
                        val postList = response.body()
                        postList!!.sortBy { it.content }
                        val adapter = PostAdapter(
                            postList!!,
                            LayoutInflater.from(this@OutstargramPostListActivity),
                            glide
                        )
                        post_recyclerview.adapter = adapter
                        post_recyclerview.layoutManager =
                            LinearLayoutManager(this@OutstargramPostListActivity)
                    }
                }

                override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}

class PostAdapter(
    var postList: ArrayList<Post>,
    val inflater: LayoutInflater,
    val glide: RequestManager
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postOwner: TextView
        val postImage: ImageView
        val postContent: TextView

        init {
            postOwner = itemView.findViewById(R.id.post_owner)
            postImage = itemView.findViewById(R.id.post_img)
            postContent = itemView.findViewById(R.id.post_content)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.outstargram_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postOwner.setText(postList.get(position).owner_profile.get("username").toString())
        holder.postContent.setText(postList.get(position).content)
        glide.load(postList.get(position).image).into(holder.postImage)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}