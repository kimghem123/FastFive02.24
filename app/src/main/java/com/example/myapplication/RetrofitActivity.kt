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
import kotlinx.android.synthetic.main.activity_network.*
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)
        //GET
        service.getStudentList().enqueue(object : Callback<ArrayList<PersonFromServer>> {
            override fun onResponse(
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                if (response.isSuccessful) {
                    val personList = response.body()
                    Log.d("retrofit", "res : " + personList?.get(0)?.age)

                    val code = response.code()
                    Log.d("retrofit", "code : " + code)

                    val error = response.errorBody()
                    val header = response.headers()
                    val adapter = RetrofitRecyclerViewAdapter(
                        response.body()!!,
                        LayoutInflater.from(this@RetrofitActivity)
                    )
                    RetrofitRecycler.adapter = adapter
                    RetrofitRecycler.layoutManager = LinearLayoutManager(this@RetrofitActivity)
                }
            }

            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) {
                Log.d("retrofit", "ERROR")
            }
        })
        //POST 1
        /*val params = HashMap<String,Any>()
        params.put("name","곰돌이")
        params.put("age",20)
        params.put("intro","와구와구")
        service.createStudent(params).enqueue(object : Callback<PersonFromServer>{
            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if(response.isSuccessful){
                    val person = response.body()
                    Log.d("retrofit","name :"+person?.name)
                }
            }

            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
            }
        })*/

        //POST 2
        val person = PersonFromServer(name = "곰돌이2", age = 21, intro = "와구와구")
        service.createStudentEasy(person).enqueue(object : Callback<PersonFromServer> {
            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if (response.isSuccessful) {
                    val person = response.body()
                    Log.d("retrofit", "name :" + person?.name)
                }
            }

            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {

            }
        })

    }

}

class RetrofitRecyclerViewAdapter(
    val itemlist: ArrayList<PersonFromServer>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<RetrofitRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val networkId: TextView
        val networkName: TextView
        val networkAge: TextView
        val networkIntro: TextView

        init {
            networkId = itemView.findViewById(R.id.networkId)
            networkName = itemView.findViewById(R.id.networkName)
            networkAge = itemView.findViewById(R.id.networkAge)
            networkIntro = itemView.findViewById(R.id.networkIntro)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.network_item_view, parent, false)
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