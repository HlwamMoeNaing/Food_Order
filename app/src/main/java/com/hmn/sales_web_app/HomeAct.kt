package com.hmn.sales_web_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*

class HomeAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val url = "http://10.112.12.101/SalesWeb/get_cat.php"
        val list = ArrayList<String>()
        val rq = Volley.newRequestQueue(this)
        val jar = JsonArrayRequest(Request.Method.GET,url,null,Response.Listener { response ->
            for (x in 0..response.length()-1){
                list.add(response.getJSONObject(x).getString("category"))
                val adp = ArrayAdapter(this,R.layout.my_textview,list)
                home_cat.adapter = adp
            }

        },Response.ErrorListener { error ->
            Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
        })
        rq.add(jar)

        home_cat.setOnItemClickListener { adapterView, view, i, l ->
            val cat:String = list[i]
            val obj = Intent(this,ItemAct::class.java)
            obj.putExtra("cat",cat)
            startActivity(obj)
        }
    }
}
