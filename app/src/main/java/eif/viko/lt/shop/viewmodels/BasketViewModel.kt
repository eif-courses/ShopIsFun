package eif.viko.lt.shop.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import eif.viko.lt.shop.models.Item
import eif.viko.lt.shop.repositories.BasketRepository


const val TAG = "ADD TO BASKET"

class BasketViewModel : ViewModel() {

    private val repository = BasketRepository
    private val basketItems : MutableLiveData<List<Item>> = MutableLiveData()

    fun addToBasket(item: Item){
        repository.addToBasket(item).addOnSuccessListener {
            Log.w(TAG, "Item added to basket!")
        }.addOnFailureListener{
            Log.e(TAG, "Item added to basket!")
        }
    }

    fun removeItemFromBasket(item: Item){
        repository.removeItemFromBasket(item).addOnFailureListener{
            Log.e(TAG,"Failed to delete Item!")
        }
    }

    fun getItemsInBasket():LiveData<List<Item>>{
        repository.getItemsInBasket().addSnapshotListener { value, error ->
            val tempList:MutableList<Item> = mutableListOf()
            value!!.forEach { doc ->
                val item = doc.toObject(Item::class.java)
                tempList.add(item)
            }
            basketItems.value = tempList
        }
        return basketItems
    }
}