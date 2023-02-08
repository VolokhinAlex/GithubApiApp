package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.popularlibrariesandroid.databinding.ItemUserBinding
import com.volokhinaleksey.popularlibrariesandroid.ui.images.ImageLoader
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserListPresenter
import javax.inject.Inject

class UsersAdapter(
    private val userListPresenter: IUserListPresenter
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: dagger.Lazy<ImageLoader<ImageView>>

    inner class ViewHolder(private val userBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(userBinding.root), UserItemView {

        override var pos: Int = -1

        override fun setLogin(login: String) {
            userBinding.userLogin.text = login
        }

        override fun setUserAvatar(url: String) {
            imageLoader.get().loadImage(url, userBinding.userImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
    ).apply {
        itemView.setOnClickListener {
            userListPresenter.onItemClickListener?.invoke(this)
        }
    }

    override fun getItemCount(): Int = userListPresenter.getItemsCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        userListPresenter.bindView(holder.apply { pos = position })
    }
}