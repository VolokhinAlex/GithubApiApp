package com.volokhinaleksey.popularlibrariesandroid.ui.user_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.volokhinaleksey.popularlibrariesandroid.databinding.FragmentUserBinding
import com.volokhinaleksey.popularlibrariesandroid.model.GithubUser

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private var userData: GithubUser? = null

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
            binding.tvLogin.text = user.login
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
        fun newInstance(userData: GithubUser?) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER_DATA, userData)
                }
            }
    }
}