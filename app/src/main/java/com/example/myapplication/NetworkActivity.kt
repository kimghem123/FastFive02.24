package com.example.myapplication

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_network.*
import kotlinx.android.synthetic.main.network_item_view.view.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.Inflater

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkTask(NetworkRecycler,LayoutInflater.from(this),this).execute()

    }
}

 class NetworkTask(
     val recyclerView: RecyclerView,
     val inflater: LayoutInflater,
     val context: Context
 ):AsyncTask<Any?,Any?,Array<PersonFromServer>>(){

    override fun doInBackground(vararg p0: Any?): Array<PersonFromServer> {
        val urlString : String = "http://mellowcode.org/json/students/"
        val url:URL = URL(urlString)
        val connection : HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod="GET"
        connection.setRequestProperty("Content-Type","application/json")
        var buffer =""
        if(connection.responseCode == HttpURLConnection.HTTP_OK){
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
        }
        val data = Gson().fromJson(buffer,Array<PersonFromServer>::class.java)

        return data
    }

     override fun onPostExecute(result: Array<PersonFromServer>?) {
         //여기는 UI 쓰레드에 접근이 가능
         val adapter = PersonRecyclerViewAdapter(result!!,inflater)
         recyclerView.adapter = adapter
         recyclerView.layoutManager = LinearLayoutManager(context)
         super.onPostExecute(result)
     }
 }

class PersonRecyclerViewAdapter(
    val itemlist :Array<PersonFromServer>,
    val inflater : LayoutInflater
):RecyclerView.Adapter<PersonRecyclerViewAdapter.ViewHolder>(){

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val networkId: TextView
        val networkName: TextView
        val networkAge: TextView
        val networkIntro:TextView
        init {
            networkId = itemView.findViewById(R.id.networkId)
            networkName = itemView.findViewById(R.id.networkName)
            networkAge = itemView.findViewById(R.id.networkAge)
            networkIntro = itemView.findViewById(R.id.networkIntro)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.network_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.networkId.setText(itemlist.get(position).id.toString())
        holder.networkName.setText(itemlist.get(position).name ?: "")
        holder.networkAge.setText(itemlist.get(position).age.toString())
        holder.networkIntro.setText(itemlist.get(position).intro ?: "")
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}