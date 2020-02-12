package com.hmn.sales_web_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_item.*

class ItemAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        val cat:String = intent.getStringExtra("cat")
        val url ="http://10.112.12.101/SalesWeb/get_items.php?category="+cat
        val list = ArrayList<Items>()
        val rq = Volley.newRequestQueue(this)
        val jar = JsonArrayRequest(Request.Method.GET,url,null,Response.Listener { response ->
            for (x in 0..response.length()-1)
                list.add(Items(response.getJSONObject(x).getInt("id"),response.getJSONObject(x).getString("name"),
                    response.getJSONObject(x).getInt("price"),response.getJSONObject(x).getString("photo")))
            val adp = ItemAdapter(this,list)
            item_rv.layoutManager = LinearLayoutManager(this)
            item_rv.adapter = adp

        },Response.ErrorListener { error ->
            Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
        })
        rq.add(jar)

    }
}
