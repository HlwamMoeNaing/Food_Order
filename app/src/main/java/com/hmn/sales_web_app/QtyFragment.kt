package com.hmn.sales_web_app


import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

/**
 * A simple [Fragment] subclass.
 */
class QtyFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_qty, container, false)
        val et = v.findViewById<EditText>(R.id.ed_qty)
        val btn = v.findViewById<Button>(R.id.bt_add)
        btn.setOnClickListener {
            val url ="http://10.112.12.101/SalesWeb/add_temp.php?mobile="+UserInfo.mobile+"&itemid="+UserInfo.itemId+
                    "&qty="+et.text.toString()
            val rq = Volley.newRequestQueue(activity)
            val sr = StringRequest(Request.Method.GET,url,Response.Listener { response ->

                val i = Intent(activity,OrderAct::class.java)
                startActivity(i)

            },Response.ErrorListener { error ->



            })
            rq.add(sr)
        }

        return v
    }


}
