package com.hmn.sales_web_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reg.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        singup_btn.setOnClickListener {
            val i= Intent(this,RegAct::class.java)
            startActivity(i)
        }

        login_btn.setOnClickListener {
            val url =
                "http://10.112.12.101/SalesWeb/login.php?mobile=" + login_mobile.text.toString() + "&passward=" + login_passward.text.toString()

            val rq = Volley.newRequestQueue(this)
            val sr = StringRequest(Request.Method.GET, url, Response.Listener { response ->
                if (response.equals("0"))
                    Toast.makeText(
                        this,
                        "Login Fail",
                        Toast.LENGTH_SHORT
                    ).show()
                else{
                    UserInfo.mobile = login_mobile.text.toString()
                    val i = Intent(this,HomeAct::class.java)
                    startActivity(i)

                }


            }, Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            })
            rq.add(sr)
        }
    }
}
