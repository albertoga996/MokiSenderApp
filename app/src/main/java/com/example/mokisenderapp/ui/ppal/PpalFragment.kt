package com.example.mokisenderapp.ui.ppal

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mokisenderapp.R
import com.example.mokisenderapp.data.APIResult
import com.example.mokisenderapp.databinding.FragmentPpalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PpalFragment : Fragment(R.layout.fragment_ppal) {

    private lateinit var binding: FragmentPpalBinding

    private val ppalViewModel: PpalViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPpalBinding.bind(view)

        initialize()
        initObservers()
    }

    private fun initialize() {
        binding.btnSend.isEnabled = false
        binding.btnFetch.setOnClickListener {
            ppalViewModel.getFact()
        }
        binding.btnSend.setOnClickListener {
            Intent().also { intent ->
                intent.action = "com.example.broadcast.moki.MY_BROADCAST"
                intent.putExtra(
                    "fact",
                    (ppalViewModel.factInfo.value as APIResult.Success).data.fact
                )
                context?.let {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        it.sendBroadcast(intent/*, "com.example.broadcast.moki.mypermission"*/)
                    } else {
                        sendImplicitBroadcast(requireNotNull(context), intent)
                    }
                }
            }
        }
    }

    private fun initObservers() {
        ppalViewModel.factInfo.observe(viewLifecycleOwner, {
            if (it is APIResult.Success) {
                binding.btnSend.isEnabled = true
            }
        })
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun sendImplicitBroadcast(context: Context, i: Intent) {
        val pm = context.packageManager
        val matches = pm.queryBroadcastReceivers(i, 0)

        for (resolveInfo in matches) {
            val explicit = Intent(i)
            val cn = ComponentName(
                resolveInfo.activityInfo.applicationInfo.packageName,
                resolveInfo.activityInfo.name
            );

            explicit.component = cn
            context.sendBroadcast(explicit)
        }
    }

}