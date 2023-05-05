package com.example.machinetest.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.machinetest.R
import com.example.machinetest.data.ContactDatabse
import com.example.machinetest.data.SignUpTable
import com.example.machinetest.databinding.FragmentLoginBinding
import com.example.machinetest.ui.register.RegisterViewModel
import com.example.machinetest.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<LoginViewModel>()
    @Inject
    lateinit var tokenManager: TokenManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (tokenManager.getToken() !=null)
        {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.txtRegister.setOnClickListener {

            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }

        binding.btnSighIn.setOnClickListener {

            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (email.isBlank())
            {
                showToast("Email is required")
            }
            else if (password.isBlank())
            {
                showToast("Password is required")
            }
            else
            {

                if (authViewModel.checkLogin(email, password))
                {

                    tokenManager.saveToken(email)
                    showToast("Login Successfully")
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                else
                {
                    showToast("Invalid Credentials")
                }

            }

        }

    }

    fun showToast(msg : String)
    {
        Toast.makeText(requireContext(),msg, Toast.LENGTH_SHORT).show()
    }



}