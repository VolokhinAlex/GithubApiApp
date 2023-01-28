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
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUserDTO
import com.volokhinaleksey.popularlibrariesandroid.navigation.BackButtonListener
import com.volokhinaleksey.popularlibrariesandroid.navigation.NavigationScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.*
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.ui.images.ImageLoader
import com.volokhinaleksey.popularlibrariesandroid.ui.images.CachedImageLoader
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.user.adapter.ReposAdapter
import com.volokhinaleksey.popularlibrariesandroid.utils.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val userData: GithubUserDTO? by lazy {
        arguments?.getParcelable(ARG_USER_DATA)
    }
    private val userPresenter by moxyPresenter {
        UserPresenter(
            userRepo = GithubUsersRepositoryImpl(
                remoteApiSource = GithubApiHolder.githubApi,
                networkStatus = AndroidNetworkStatus(requireContext()),
                localDatabase = GithubRoomDatabase.getInstance(),
                roomGithubUsersCache = RoomGithubUsersCacheImpl()
            ),
            repositoryRepo = GithubRepositoriesRepositoryImpl(
                remoteApiSource = GithubApiHolder.githubApi,
                networkStatus = AndroidNetworkStatus(requireContext()),
                localDatabase = GithubRoomDatabase.getInstance(),
                repositoriesCache = RoomGithubRepositoriesCacheImpl()
            ),
            uiScheduler = AndroidSchedulers.mainThread(),
            router = App.appInstance.router,
            screens = NavigationScreens(),
            githubUser = userData
        )
    }
    private val imageLoader: ImageLoader<ImageView> = CachedImageLoader()


    private val reposAdapter: ReposAdapter by lazy {
        ReposAdapter(presenter = userPresenter.userReposListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_USER_DATA = "UserData"

        @JvmStatic
        fun newInstance(userData: GithubUserDTO) =
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
        githubUser.imageUrlFromStorage?.let { imageLoader.loadImage(it, binding.userImage) }
    }

    override fun backPressed(): Boolean = userPresenter.backPressed()
}