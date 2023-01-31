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
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val usersPresenter by moxyPresenter {
        App.appInstance.appComponent.injectUsersPresenter()
    }
    private val usersListAdapter by lazy {
        App.appInstance.appComponent.injectUsersAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater)
        return binding.root
    }

    /**
     * Initial initialization list in the Recycler view
     */

    override fun init() {
        binding.usersListContainer.adapter = usersListAdapter
        binding.usersListContainer.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * Updating the list in the Recycler view
     */

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        usersListAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = UsersFragment()
    }

    override fun backPressed(): Boolean = usersPresenter.backPressed()
}