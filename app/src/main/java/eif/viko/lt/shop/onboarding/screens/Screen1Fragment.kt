package eif.viko.lt.shop.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import eif.viko.lt.shop.R
import kotlinx.android.synthetic.main.fragment_screen1.view.*


class Screen1Fragment : Fragment(R.layout.fragment_screen1) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPagerOnboarding)
        view.screen1_btn.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }

}