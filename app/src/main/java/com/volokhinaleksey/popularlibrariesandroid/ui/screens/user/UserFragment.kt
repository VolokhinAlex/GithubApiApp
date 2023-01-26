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
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser
import com.volokhinaleksey.popularlibrariesandroid.navigation.BackButtonListener
import com.volokhinaleksey.popularlibrariesandroid.navigation.NavigationScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubApiHolder
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepoImpl
import com.volokhinaleksey.popularlibrariesandroid.ui.images.CoilImageLoader
import com.volokhinaleksey.popularlibrariesandroid.ui.images.ImageLoader
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.adapter.ReposAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private var userData: GithubUser? = null
    private val userPresenter by moxyPresenter {
        UserPresenter(
            userRepo = GithubUsersRepoImpl(GithubApiHolder.githubApi),
            uiScheduler = AndroidSchedulers.mainThread(),
            router = App.appInstance.router,
            screens = NavigationScreens()
        )
    }
    private val imageLoader: ImageLoader<ImageView> = CoilImageLoader()

    private val reposAdapter: ReposAdapter by lazy {
        ReposAdapter(presenter = userPresenter.userReposListPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userData = it.getParcelable(ARG_USER_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater)
        userData?.let { user ->
            user.login?.let {
                userPresenter.getUserInfoByLogin(it)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_USER_DATA = "UserData"

        @JvmStatic
        fun newInstance(userData: GithubUser) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER_DATA, userData)
                }
            }
    }

    override fun init() {
        binding.reposListContainer.adapter = reposAdapter
        binding.reposListContainer.layoutManager = LinearLayoutManager(requireContext())
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        reposAdapter.notifyDataSetChanged()
    }

    override fun setUserData(githubUser: GithubUser) {
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
        imageLoader.loadImage(githubUser.avatarUrl.orEmpty(), binding.userImage)
    }

    override fun backPressed(): Boolean = userPresenter.backPressed()
}