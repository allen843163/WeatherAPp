package com.allen.core.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class MyBaseAdapter
    (val modelId : Int,
     var itemClickListener: DefaultItemClcikListener
) : RecyclerView.Adapter<MyBaseAdapter.MyViewHoder>()
    , DefaultItemClcikListener {

    override fun onBindViewHolder(holder: MyViewHoder, position: Int) {

        val obj = getObjForPosition(position)

        holder.itemView.setOnClickListener { itemClickListener.onItemClick(obj) }

        holder.bind(modelId, obj)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHoder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false
        )

        return MyViewHoder(binding)

    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getObjForPosition(position: Int): Any?

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    class MyViewHoder(val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(modelId : Int, any : Any?) {

            viewDataBinding.setVariable(modelId, any)

            viewDataBinding.executePendingBindings()

        }

    }

}

interface DefaultItemClcikListener {
    fun onItemClick(obj : Any?)
}
