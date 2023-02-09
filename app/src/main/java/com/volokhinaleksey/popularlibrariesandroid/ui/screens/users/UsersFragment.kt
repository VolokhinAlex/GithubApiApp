package com.volokhinaleksey.popularlibrariesandroid.ui.screens.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.volokhinaleksey.popularlibrariesandroid.app.App
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentUsersBinding
import com.volokhinaleksey.popularlibrariesandroid.di.components.UsersSubcomponent
import com.volokhinaleksey.popularlibrariesandroid.navigation.BackButtonListener
import com.volokhinaleksey.popularlibrariesandroid.ui.screens.users.adapter.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private var usersSubcomponent: UsersSubcomponent? = null

    private val usersPresenter by moxyPresenter {
        usersSubcomponent = App.appInstance.initUsersSubcomponent()
        usersSubcomponent!!.injectUsersPresenter()
    }
    private val usersListAdapter by lazy {
        UsersAdapter(userListPresenter = usersPresenter.usersListPresenter).apply {
            App.appInstance.usersSubcomponent?.injectUsersAdapter(usersAdapter = this)
        }
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

    /**
     * Showing the progress bar during loading data
     */

    override fun loadingState() {
        binding.progressBar.visibility = View.VISIBLE
    }

    /**
     * Hiding the progress bar when data is loaded
     */

    override fun successState() {
        binding.progressBar.visibility = View.GONE
    }

    /**
     * Error display when loading data
     */

    override fun errorState(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.errorMessage.text = message
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