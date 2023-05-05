package com.example.machinetest.ui.register

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.machinetest.R
import com.example.machinetest.data.ContactDatabse
import com.example.machinetest.data.SignUpTable
import com.example.machinetest.databinding.FragmentLoginBinding
import com.example.machinetest.databinding.FragmentRegisterBinding
import com.example.machinetest.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<RegisterViewModel>()
    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtLogin.setOnClickListener{

            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnSighUp.setOnClickListener {

            val userName = binding.userName.text.toString()
            val email = binding.email.text.toString()
            val phone = binding.phone.text.toString()
            val password = binding.password.text.toString()


            if (userName.isBlank())
            {
               showToast("User Name is required")
            }
            else if (email.isBlank())
            {
                showToast("Email is required")
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                showToast("Invalid Email ID")
            }
            else if (password.isBlank())
            {
                showToast("Password is required")
            }
            else
            {

                if (authViewModel.checkRegisterEmail(email = email))
                {
                    showToast("Email is already exists")
                }
                else
                {
                    authViewModel.signup(userName,email,phone,password)
                    tokenManager.saveToken(email)
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment2)
                }


            }

        }



    }


    fun showToast(msg : String)
    {
        Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
    }
}