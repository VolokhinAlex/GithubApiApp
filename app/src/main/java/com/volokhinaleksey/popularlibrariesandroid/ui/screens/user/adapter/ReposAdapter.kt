package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.popularlibrariesandroid.databinding.ItemRepoBinding
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserReposListPresenter
import javax.inject.Inject

class ReposAdapter @Inject constructor(
    private val presenter: IUserReposListPresenter
) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.onItemClickListener?.invoke(this)
            }
        }

    override fun getItemCount(): Int = presenter.getItemsCount()

    override fun onBindViewHolder(holder: ReposAdapter.ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val userBinding: ItemRepoBinding) :
        RecyclerView.ViewHolder(userBinding.root), RepoItemView {

        override var pos: Int = -1

        override fun setRepoName(repoName: String) {
            userBinding.repoName.text = repoName
        }
    }
}