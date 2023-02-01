package com.volokhinaleksey.popularlibrariesandroid.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.popularlibrariesandroid.databinding.ItemUserBinding
import com.volokhinaleksey.popularlibrariesandroid.presenter.IUserListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.UserItemView

class UsersAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(val item: ItemUserBinding) :
        RecyclerView.ViewHolder(item.root), UserItemView {

        override var pos: Int = -1

        override fun setLogin(login: String) {
            item.tvLogin.text = login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
    ).apply {
        itemView.setOnClickListener {
            presenter.onItemClickListener?.invoke(this)
        }
    }

    override fun getItemCount(): Int = presenter.getItemsCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }
}