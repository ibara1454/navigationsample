package com.example.navigation_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.*

class ProcessingFragment: Fragment(), CoroutineScope by MainScope() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_processing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("in processing fragment")
        launch {
            delay(2_000L)
            findNavController().navigate(R.id.action_processingFragment_to_decidingFragment, null, navOptions {
                popUpTo(R.id.processingFragment) {
                    inclusive = true
                }
            })
        }
    }

    override fun onDestroyView() {
        println("out processing fragment")
        cancel()
        super.onDestroyView()
    }
}
