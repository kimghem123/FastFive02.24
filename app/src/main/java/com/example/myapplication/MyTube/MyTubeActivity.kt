package com.example.myapplication.MyTube

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.myapplication.OutStarGram.MasterApplication
import com.example.myapplication.OutStarGram.Post
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.activity_my_tube.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTubeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tube)

        (application as MasterApplication).service.getYoutubeList()
            .enqueue(object : Callback<ArrayList<Mytube>>{
                override fun onResponse(
                    call: Call<ArrayList<Mytube>>,
                    response: Response<ArrayList<Mytube>>
                ) {
                    if(response.isSuccessful){
                        val glide = Glide.with(this@MyTubeActivity)
                        val myTubeList = response.body()
                        val adapter = MyTubeAdapter(
                            myTubeList!!,
                            LayoutInflater.from(this@MyTubeActivity),
                            glide,
                            this@MyTubeActivity
                        )
                        mytube_list_recycler.adapter = adapter

                    }
                }

                override fun onFailure(call: Call<ArrayList<Mytube>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}

class MyTubeAdapter(
    var myTubeList: ArrayList<Mytube>,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val activity: Activity
) : RecyclerView.Adapter<MyTubeAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val content: TextView
        val thumbnail: ImageView

        init {
            title = itemView.findViewById(R.id.mytube_title)
            thumbnail = itemView.findViewById(R.id.mytube_thumbnail)
            content = itemView.findViewById(R.id.mytube_content)
            itemView.setOnClickListener {
                val position:Int = absoluteAdapterPosition
                val intent = Intent(activity, MyTubeDetailActivity::class.java)
                intent.putExtra("video_url",myTubeList.get(position).video)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.mytube_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(myTubeList.get(position).title)
        holder.content.setText(myTubeList.get(position).content)
        glide.load(myTubeList.get(position).thumbnail).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return myTubeList.size
    }
}