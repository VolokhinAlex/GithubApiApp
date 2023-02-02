package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentRepoDetailsBinding
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.navigation.BackButtonListener
import com.volokhinaleksey.popularlibrariesandroid.ui.FragmentInitializer
import com.volokhinaleksey.popularlibrariesandroid.utils.parcelable
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoDetailsFragment : MvpAppCompatFragment(), RepoDetailsView, BackButtonListener {
    private val detailsRepoData: GithubRepositoryDTO? by lazy {
        arguments?.parcelable()
    }
    private var _binding: FragmentRepoDetailsBinding? = null
    private val binding get() = _binding!!

    private val detailsPresenter by moxyPresenter {
        RepoDetailsPresenter(detailsRepoData).apply {
            App.appInstance.userSubcomponent?.injectRepoDetailsPresenter(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object : FragmentInitializer<GithubRepositoryDTO>

    override fun backPressed(): Boolean = detailsPresenter.backPressed()
    override fun setRepoName(repoName: String) {
        binding.repoName.text = repoName
    }

    override fun setForkCount(forksCount: String) {
        binding.forkCount.text = forksCount
    }

    override fun repoUrl(repoUrl: String) {
        binding.openInBrowser.setOnClickListener {
            val openRepository = Intent(Intent.ACTION_VIEW, Uri.parse(repoUrl))
            startActivity(openRepository)
        }
    }
}