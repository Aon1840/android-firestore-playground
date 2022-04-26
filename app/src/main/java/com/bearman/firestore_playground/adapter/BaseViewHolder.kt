package com.bearman.firestore_playground.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bearman.firestore_playground.interfaces.UserClickListener

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindItem(item: T, coinClickListener: UserClickListener)
}