package com.volokhinaleksey.popularlibrariesandroid.ui.screens.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentUserBinding
import com.volokhinaleksey.popularlibrariesandroid.di.components.UserSubcomponent
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.navigation.BackButtonListener
import com.volokhinaleksey.popularlibrariesandroid.repository.*
import com.volokhinaleksey.popularlibrariesandroid.ui.FragmentInitializer
import com.volokhinaleksey.popularlibrariesandroid.ui.images.ImageLoader
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.adapter.ReposAdapter
import com.volokhinaleksey.popularlibrariesandroid.utils.parcelable
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val userData: GithubUserDTO? by lazy { arguments?.parcelable() }

    @Inject
    lateinit var imageLoader: ImageLoader<ImageView>

    private var _userSubcomponent: UserSubcomponent? = null

    private val userPresenter by moxyPresenter {
        UserPresenter(githubUser = userData).apply {
            _userSubcomponent = App.appInstance.initUserSubcomponent()
            _userSubcomponent?.injectUserPresenter(userPresenter = this)
        }
    }

    private val reposAdapter: ReposAdapter by lazy {
        ReposAdapter(userPresenter.userReposListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appInstance.userSubcomponent?.injectUserFragment(userFragment = this)
        _binding = FragmentUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object : FragmentInitializer<GithubUserDTO>

    /**
     * Initial initialization list in the Recycler view
     */

    override fun init() {
        binding.reposListContainer.adapter = reposAdapter
        binding.reposListContainer.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * Updating the list in the Recycler view
     */

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        reposAdapter.notifyDataSetChanged()
    }

    /**
     * Installing User data into UI Items
     */

    override fun setUserData(githubUser: GithubUserDTO) {
        binding.userLogin.text = githubUser.login
        binding.personName.text = githubUser.name
        val followers = githubUser.followers ?: 0
        val following = githubUser.following ?: 0
        val publicRepos = githubUser.publicRepos ?: 0
        val publicGists = githubUser.publicGists ?: 0
        binding.followers.text = followers.toString()
        binding.following.text = following.toString()
        binding.publicRepos.text = publicRepos.toString()
        binding.publicGists.text = publicGists.toString()
        githubUser.avatarUrl?.let { imageLoader.loadImage(url = it, target = binding.userImage) }
    }

    /**
     * Showing the progress bar during loading data
     */

    override fun loadingState() {
        binding.progressBar.visibility = View.VISIBLE
        binding.userDetailsContainer?.visibility = View.GONE
    }

    /**
     * Hiding the progress bar when data is loaded
     */

    override fun successState() {
        binding.progressBar.visibility = View.GONE
        binding.userDetailsContainer?.visibility = View.VISIBLE
    }

    /**
     * Error display when loading data
     */

    override fun errorState(message: String) {
        binding.userDetailsContainer?.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.errorMessage.text = message
    }

    override fun backPressed(): Boolean = userPresenter.backPressed()
}
