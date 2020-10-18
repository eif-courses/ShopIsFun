package eif.viko.lt.shop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import eif.viko.lt.shop.adapters.ItemListAdapter
import eif.viko.lt.shop.R
import eif.viko.lt.shop.viewmodels.ItemViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var itemViewModel: ItemViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        val itemListAdapter: ItemListAdapter by lazy { ItemListAdapter() }
        // populated list with items
        itemListAdapter.submitList(itemViewModel.getItems().value)

        item_recycleView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(context)
            // set the custom adapter to the RecyclerView
            adapter = itemListAdapter
        }
    }

}