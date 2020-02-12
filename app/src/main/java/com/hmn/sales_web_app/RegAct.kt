package com.hmn.sales_web_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_reg.*

class RegAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        regSignUp_btn.setOnClickListener {
            if (reg_mobile.text.isEmpty()) {
                reg_mobile.error = "Enter Mobile Number"
                reg_mobile.requestFocus()
                return@setOnClickListener
            }
            if (reg_passward.text.isEmpty()) {
                reg_passward.error = "Enter Passward"
                reg_passward.requestFocus()
                return@setOnClickListener
            }
            if (reg_name.text.isEmpty()) {
                reg_name.error = "Name Cannot be blank"
                reg_name.requestFocus()
                return@setOnClickListener
            }
            if (reg_address.text.isEmpty()) {
                reg_address.error = "Address Need"
                reg_address.requestFocus()
                return@setOnClickListener
            }



            if (reg_passward.text.toString().equals(reg_confirm.text.toString())) {
                val url =
                    "http://10.112.12.101/SalesWeb/add_users.php?mobile=" + reg_mobile.text.toString() + "&passward=" + reg_passward.text.toString() +
                            "&name=" + reg_name.text.toString() + "&address=" + reg_address.text.toString()
                val rq = Volley.newRequestQueue(this)
                val sr = StringRequest(Request.Method.GET, url, Response.Listener { response ->
                    if (response.equals("0"))
                        Toast.makeText(
                            this,
                            "Mobile Number Already Exist",
                            Toast.LENGTH_SHORT
                        ).show()
                    else{
                        UserInfo.mobile = reg_mobile.text.toString()
                        val i = Intent(this,HomeAct::class.java)
                        startActivity(i)
                        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()
                    }


                }, Response.ErrorListener { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                })
                rq.add(sr)
            } else {
                Toast.makeText(this, "Passward does't match", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
