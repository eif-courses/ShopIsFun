package eif.viko.lt.shop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import eif.viko.lt.shop.adapters.ItemListAdapter
import eif.viko.lt.shop.R
import eif.viko.lt.shop.models.Item
import eif.viko.lt.shop.viewmodels.BasketViewModel
import eif.viko.lt.shop.viewmodels.ItemViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.random.Random

class HomeFragment : Fragment(R.layout.fragment_home), ItemListAdapter.Interaction {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var basketViewModel: BasketViewModel
    val user = FirebaseAuth.getInstance().currentUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        basketViewModel = ViewModelProvider(this).get(BasketViewModel::class.java)


        basketViewModel.addToBasket(
            Item("${Random(System.currentTimeMillis()).nextInt(9999999)}",
                "Samsung EVO 850 Plus",
                200.50,
                "https://kainos-img.dgn.lt/photos2_25_35790823/img.jpg"
        ))


        val itemListAdapter: ItemListAdapter by lazy { ItemListAdapter(this) }
        // populated list with items

        if(user != null){
          basketViewModel.getItemsInBasket().observe(viewLifecycleOwner, Observer {
              itemListAdapter.submitList(it)
            //  itemListAdapter.notifyDataSetChanged()
          })
        }

        item_recycleView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(context)
            // set the custom adapter to the RecyclerView
            adapter = itemListAdapter
        }
    }

    override fun clickOnItem(item: Item) {
        showAlertDialog(item)
       // basketViewModel.removeItemFromBasket(item)
        //Toast.makeText(context, "Item clicked: ${item.name}, price: ${item.price}", Toast.LENGTH_LONG).show()

    }
    private fun showAlertDialog(item: Item) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Basket item deletion")
        alertDialog.setMessage("Do you wanna remove ${item.name} from basket?")
        alertDialog.setPositiveButton(
            "yes"
        ) { _, _ ->
            basketViewModel.removeItemFromBasket(item)
            Toast.makeText(context, "Item ${item.name} successfully removed from basket!.", Toast.LENGTH_LONG).show()
        }
        alertDialog.setNegativeButton(
            "No"
        ) { _, _ -> }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

}