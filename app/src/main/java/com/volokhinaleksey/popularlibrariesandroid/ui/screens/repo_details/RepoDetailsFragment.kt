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
import com.volokhinaleksey.popularlibrariesandroid.utils.parcelable
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val ARG_DETAILS_REPO = "Details Repo"

class RepoDetailsFragment : MvpAppCompatFragment(), RepoDetailsView, BackButtonListener {
    private var detailsRepoData: GithubRepositoryDTO? = null
    private var _binding: FragmentRepoDetailsBinding? = null
    private val binding get() = _binding!!

    private val repoDetailsRepository by moxyPresenter {
        App.appInstance.appComponent.injectRepoDetailsPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            detailsRepoData = it.parcelable(ARG_DETAILS_REPO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoDetailsBinding.inflate(inflater)
        detailsRepoData?.apply {
            binding.repoName.text = name
            binding.forkCount.text = forks.toString()
            binding.openInBrowser.setOnClickListener {
                val openRepository = Intent(Intent.ACTION_VIEW, Uri.parse(htmlUrl))
                startActivity(openRepository)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(repoData: GithubRepositoryDTO) =
            RepoDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DETAILS_REPO, repoData)
                }
            }
    }

    override fun backPressed(): Boolean = repoDetailsRepository.backPressed()
}