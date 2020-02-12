package com.hmn.sales_web_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_order.*

class OrderAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val url = "http://10.112.12.101/SalesWeb/get_temp.php?mobile=" + UserInfo.mobile
        val list = ArrayList<String>()
        val rq = Volley.newRequestQueue(this)
        val jar = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0..response.length() - 1) {
                list.add(
                    "Name :" + response.getJSONObject(x).getString("name") + "\n" +
                            "Price :" + response.getJSONObject(x).getString("price")
                            + "\n" + "Qty   :" + response.getJSONObject(x).getString("qty")
                )
                val adp = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
                lv.adapter = adp
            }

        }, Response.ErrorListener { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        })
        rq.add(jar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if (item.itemId==R.id.item_menu)
       {
           val i = Intent(this,HomeAct::class.java)
           startActivity(i)

       }

        return super.onOptionsItemSelected(item)
    }

}
