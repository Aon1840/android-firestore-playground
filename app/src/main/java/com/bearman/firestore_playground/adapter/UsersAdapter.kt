package com.bearman.firestore_playground.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bearman.firestore_playground.R
import com.bearman.firestore_playground.interfaces.UserClickListener
import com.bearman.firestore_playground.model.User

class UsersAdapter(
    var value: List<User>,
    var context: Context,
    var userClickListener: UserClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return UserViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return value.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is UserViewHolder -> holder.bindItem(
                value[position],
                userClickListener
            )
            else -> throw IllegalArgumentException(
                "No viewholder to show this data, did you forgot to add it to the onBindViewHolder?"
            )
        }
    }

    inner class UserViewHolder(itemView: View) : BaseViewHolder<User>(itemView) {

        private lateinit var tvTitle: TextView
        private lateinit var tvDesc: TextView
        private lateinit var ivEditBtn: ImageView
        private lateinit var ivDeleteBtn: ImageView

        override fun bindItem(model: User, userClickListener: UserClickListener) {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvDesc = itemView.findViewById(R.id.tvDesc)
            ivEditBtn = itemView.findViewById(R.id.ivEditBtn)
            ivDeleteBtn = itemView.findViewById(R.id.ivDeleteBtn)

            tvTitle.text = model.first + " " + model.last
            tvDesc.text = model.born.toString()
            ivEditBtn.setOnClickListener {
                userClickListener.onEditClickListener(model.first)
            }
            ivDeleteBtn.setOnClickListener {
                userClickListener.onDeleteClickListener(model.first)
            }
        }
    }
}