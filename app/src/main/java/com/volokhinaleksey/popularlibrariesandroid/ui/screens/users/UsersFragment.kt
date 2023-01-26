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
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepoImpl
import com.volokhinaleksey.popularlibrariesandroid.ui.images.CoilImageLoader
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UsersAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        UsersPresenter(
            usersRepository = GithubUsersRepoImpl(GithubApiHolder.githubApi),
            router = App.appInstance.router,
            uiScheduler = AndroidSchedulers.mainThread(),
            screens = NavigationScreens()
        )
    }
    private val usersListAdapter by lazy {
        UsersAdapter(
            presenter = presenter.usersListPresenter,
            imageLoader = CoilImageLoader()
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