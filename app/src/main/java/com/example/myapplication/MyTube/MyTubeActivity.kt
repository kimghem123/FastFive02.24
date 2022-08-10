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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.myapplication.OutStarGram.MasterApplication
import com.example.myapplication.OutStarGram.Post
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMyTubeBinding
import com.example.myapplication.databinding.MytubeItemViewBinding
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.activity_my_tube.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTubeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyTubeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMyTubeBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        (application as MasterApplication).service.getYoutubeList()
            .enqueue(object : Callback<ArrayList<Mytube>> {
                override fun onResponse(
                    call: Call<ArrayList<Mytube>>,
                    response: Response<ArrayList<Mytube>>
                ) {
                    if (response.isSuccessful) {
                        val glide = Glide.with(this@MyTubeActivity)
                        val myTubeList = response.body()
                        val adapter = MyTubeAdapter(
                            myTubeList!!,
                            LayoutInflater.from(this@MyTubeActivity),
                            glide,
                            this@MyTubeActivity
                        )
                        binding.mytubeListRecycler.adapter = adapter
                        binding.mytubeListRecycler.layoutManager =
                            LinearLayoutManager(this@MyTubeActivity)
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
    inner class ViewHolder(binding: MytubeItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView
        val content: TextView
        val thumbnail: ImageView

        init {
            title = binding.mytubeTitle
            thumbnail = binding.mytubeThumbnail
            content = binding.mytubeContent
            itemView.setOnClickListener {
                val position: Int = absoluteAdapterPosition
                val intent = Intent(activity, MyTubeDetailActivity::class.java)
                intent.putExtra("video_url", myTubeList.get(position).video)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.mytube_item_view, parent, false)
        return ViewHolder(MytubeItemViewBinding.bind(view))
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