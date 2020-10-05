package eif.viko.lt.shop.viewmodels

import androidx.lifecycle.ViewModel
import eif.viko.lt.shop.repositories.ItemRepository

class ItemViewModel : ViewModel() {
    fun getItems() = ItemRepository.getItems()
}