package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.popularlibrariesandroid.databinding.ItemCommitBinding
import com.volokhinaleksey.popularlibrariesandroid.ui.items.IUserRepoCommitsListPresenter


class CommitsAdapter(
    private val commitsListPresenter: IUserRepoCommitsListPresenter
) : RecyclerView.Adapter<CommitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCommitBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                commitsListPresenter.onItemClickListener?.invoke(this)
            }
        }

    override fun getItemCount(): Int = commitsListPresenter.getItemsCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        commitsListPresenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val commitBinding: ItemCommitBinding) :
        RecyclerView.ViewHolder(commitBinding.root), CommitsItemView {

        override fun setCommitterName(committerName: String) {
            commitBinding.committerName.text = committerName
        }

        override fun setCommitterEmail(committerEmail: String) {
            commitBinding.committerEmail.text = committerEmail
        }

        override fun setCommitDate(date: String) {
            commitBinding.commitDate.text = date
        }

        override fun setCommitMessage(message: String) {
            commitBinding.commitMessage.text = message
        }

        override var pos: Int = -1
    }
}