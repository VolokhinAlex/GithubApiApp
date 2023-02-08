package com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentRepoDetailsBinding
import com.volokhinaleksey.popularlibrariesandroid.model.GithubRepositoryDTO
import com.volokhinaleksey.popularlibrariesandroid.navigation.BackButtonListener
import com.volokhinaleksey.popularlibrariesandroid.ui.FragmentInitializer
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.repo_details.adapter.CommitsAdapter
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
        App.appInstance.userSubcomponent?.repoDetailsPresenterFactory!!.create(githubRepositoryDTO = detailsRepoData)
    }

    private val commitsAdapter: CommitsAdapter by lazy {
        CommitsAdapter(commitsListPresenter = detailsPresenter.userRepoCommitsListPresenter)
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

    override fun onBackPressed(): Boolean = detailsPresenter.backPressed()
    override fun setRepoName(repoName: String) {
        binding.repoName.text = repoName
    }

    override fun setForksCount(forksCount: String) {
        binding.forkCount.text = forksCount
    }

    override fun setRepoUrl(repoUrl: String) {
        binding.openInBrowser.setOnClickListener {
            val openRepository = Intent(Intent.ACTION_VIEW, Uri.parse(repoUrl))
            startActivity(openRepository)
        }
    }

    /**
     * Initial initialization list in the Recycler view
     */

    override fun init() {
        binding.commitsListContainer.adapter = commitsAdapter
        binding.commitsListContainer.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * Updating the list in the Recycler view
     */

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        commitsAdapter.notifyDataSetChanged()
    }

    /**
     * Installing User data into UI Items
     */

    override fun loadingState() {
        binding.progressBar.visibility = View.VISIBLE
        binding.repoDetailsContainer.visibility = View.GONE
    }

    /**
     * Hiding the progress bar when data is loaded
     */

    override fun successState() {
        binding.progressBar.visibility = View.GONE
        binding.repoDetailsContainer.visibility = View.VISIBLE
    }

    /**
     * Error display when loading data
     */

    override fun errorState(message: String) {
        binding.repoDetailsContainer.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.errorMessage.text = message
    }
}