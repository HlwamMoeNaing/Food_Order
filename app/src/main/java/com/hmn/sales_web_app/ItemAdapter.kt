package com.hmn.sales_web_app

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_rows.view.*

class ItemAdapter(val context:Context,val list:ArrayList<Items>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v:View = LayoutInflater.from(context).inflate(R.layout.item_rows,parent,false)
        return ItemHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(list[position].name,list[position].price,list[position].photo,list[position].id)

    }
    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(n:String,p:Int,u:String,item_id:Int){
            itemView.item_name.text = n
            itemView.item_price.text = p.toString()
            var web:String = "http://10.112.12.101/SalesWeb/images/"+u
            web = web.replace(" ","%20")
            Picasso.with(itemView.context).load(web).into(itemView.item_photo)
            itemView.item_add_photo.setOnClickListener {
                UserInfo.itemId = item_id
                val obj = QtyFragment()
                val manager = (itemView.context as Activity).fragmentManager
                obj.show(manager,"QTY")

            }
            itemView.setOnClickListener {
                Toast.makeText(itemView.context,"Clicc",Toast.LENGTH_LONG).show()
            }
        }
    }
}