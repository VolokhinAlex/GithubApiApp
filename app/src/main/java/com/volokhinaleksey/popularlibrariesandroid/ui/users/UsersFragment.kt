package com.volokhinaleksey.popularlibrariesandroid.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.navigation.BackButtonListener
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentUsersBinding
import com.volokhinaleksey.popularlibrariesandroid.repository.GithubUsersRepoImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepoImpl(),
            App.appInstance.router
        )
    }
    private val usersListAdapter by lazy {
        UsersAdapter(presenter = presenter.usersListPresenter)
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

    override fun updateList() {
        usersListAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean = presenter.backPressed()


}