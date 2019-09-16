package com.example.navigation_sample

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class DecidingFragment: Fragment() {
    // Hack:
    // Since the initialization could lead to crash if it is called before `onCreateView`,
    // we use lazy block to delay the initialization until needed.
    private val dialog by lazy {
        AlertDialog.Builder(requireActivity())
            .setTitle("TITLE")
            .setMessage("MESSAGE")
            .setCancelable(false) // Ensure the dialog is not cancelable
            .setPositiveButton("OK") { _, _ -> navigateSuccess() }
            .setNegativeButton("NOT OK") { _, _ -> navigateFailure() }
            .create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_deciding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog.show()
    }

    override fun onDestroyView() {
        dialog.dismiss() // Destroying dialog to avoid memory leak
        super.onDestroyView()
    }

    private fun navigateSuccess() {
        findNavController().navigate(R.id.action_decidingFragment_to_processingFragment)
    }

    private fun navigateFailure() {
        val accounts = listOf("message1", "message2", "message3").toTypedArray()
        val action = DecidingFragmentDirections.actionDecidingFragmentToFailureFragment(accounts = accounts)
        findNavController().navigate(action)
    }
}
