package eif.viko.lt.shop.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eif.viko.lt.shop.models.Item

object ItemRepository {
    val db = mutableListOf<Item>()
    val mutableList = MutableLiveData<List<Item>>()

    fun getItems(): LiveData<List<Item>>{
        db.clear()
        loadFromAnySource()
        mutableList.value = db
        return mutableList
    }

    fun loadFromAnySource(){
        db.add(Item("Headphones", 20.9, "https://specials-images.forbesimg.com/imageserve/5e8ce586748506000636107e/960x0.jpg?fit=scale"))
        db.add(Item("Laptop MSI", 5000.9, "https://brain-images-ssl.cdn.dixons.com/3/3/10205333/u_10205333.jpg"))
        db.add(Item("Headphones", 20.9, "https://specials-images.forbesimg.com/imageserve/5e8ce586748506000636107e/960x0.jpg?fit=scale"))
        db.add(Item("Headphones", 20.9, "https://specials-images.forbesimg.com/imageserve/5e8ce586748506000636107e/960x0.jpg?fit=scale"))
    }


}