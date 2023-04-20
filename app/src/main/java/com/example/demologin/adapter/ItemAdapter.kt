package com.example.demologin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demologin.databinding.AdapterItemBinding
import com.example.demologin.response.ApiResponse

class ItemAdapter(
    val context: Context,
    var list: ArrayList<ApiResponse.Payload> = ArrayList()
//    val callBackListener: CallBackListener


) : RecyclerView.Adapter<ItemAdapter.VieHolder>() {
    class VieHolder(val b: AdapterItemBinding) : RecyclerView.ViewHolder(b.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VieHolder {
        val b = AdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VieHolder(b)
    }

    override fun onBindViewHolder(holder: VieHolder, position: Int) {

        val data = list[position]
        data.itemList[position].item_id
        holder.b.tvName.text = data.itemList[position].title.EN
        holder.b.tvDes.text = data.itemList[position].description.EN
        holder.b.tvPrice.text = data.itemList[position].price.toString()
        Glide.with(context).load(data.itemList[position].image).into(holder.b.ivItem)
        holder.itemView.setOnClickListener {
//            context.startActivity(
//                Intent(context, PerentsGroupActivity::class.java)
//                    .putExtra(PerentsGroupActivity.USER_ID, data.id)
//                    .putExtra(PerentsGroupActivity.IS_EDIT, true)
//            )

        }
//        holder.b.delete.setOnClickListener {
//            callBackListener.onClick(position)
//        }
    }

//    private fun deleteItem(position: Int) {
//        list.removeAt(position)
//        notifyItemRemoved(position)
//        notifyItemRangeChanged(position, list.size)
////        deleteUserParentApi()
//    }

    override fun getItemCount(): Int {
        return list.size

    }

    fun notifyItemChanged() {

    }

    interface CallBackListener {
        fun onClick(position: Int)
    }
}

