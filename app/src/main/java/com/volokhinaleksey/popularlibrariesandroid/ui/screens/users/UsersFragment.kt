package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentUsersBinding
import com.volokhinaleksey.popularlibrariesandroid.navigation.BackButtonListener
import com.volokhinaleksey.popularlibrariesandroid.navigation.NavigationScreens
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubApiHolder
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepositoryImpl
import com.volokhinaleksey.popularlibrariesandroid.repository.RoomGithubUsersCacheImpl
import com.volokhinaleksey.popularlibrariesandroid.room.GithubRoomDatabase
import com.volokhinaleksey.popularlibrariesandroid.ui.images.CachedImageLoader
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UsersAdapter
import com.volokhinaleksey.popularlibrariesandroid.utils.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        UsersPresenter(
            usersRepository = GithubUsersRepositoryImpl(
                remoteApiSource = GithubApiHolder.githubApi,
                networkStatus = AndroidNetworkStatus(requireContext()),
                localDatabase = GithubRoomDatabase.getInstance(),
                roomGithubUsersCache = RoomGithubUsersCacheImpl()
            ),
            router = App.appInstance.router,
            uiScheduler = AndroidSchedulers.mainThread(),
            screens = NavigationScreens()
        )
    }
    private val usersListAdapter by lazy {
        UsersAdapter(
            presenter = presenter.usersListPresenter,
            imageLoader = CachedImageLoader() // CoilImageLoader
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater)
        return binding.root
    }

    override fun init() {
        binding.usersListContainer.adapter = usersListAdapter
        binding.usersListContainer.layoutManager = LinearLayoutManager(requireContext())
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        usersListAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean = presenter.backPressed()


}