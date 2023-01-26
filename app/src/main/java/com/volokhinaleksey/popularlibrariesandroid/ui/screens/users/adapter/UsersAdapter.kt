package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.popularlibrariesandroid.databinding.ItemUserBinding
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserListPresenter
import com.volokhinaleksey.popularlibrariesandroid.ui.images.ImageLoader

class UsersAdapter(val presenter: IUserListPresenter, val imageLoader: ImageLoader<ImageView>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    inner class ViewHolder(private val userBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(userBinding.root), UserItemView {

        override var pos: Int = -1

        override fun setLogin(login: String) {
            userBinding.userLogin.text = login
        }

        override fun setAvatar(url: String) {
            imageLoader.loadImage(url, userBinding.userImage)
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