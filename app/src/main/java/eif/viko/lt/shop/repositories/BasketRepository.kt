package eif.viko.lt.shop.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import eif.viko.lt.shop.models.Item

object BasketRepository {

    var db = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    fun addToBasket(item: Item):Task<Void>{
        val doc = db.collection("users").document(user!!.uid).collection("basket").document(item.uuid)
        return doc.set(item)
    }

    fun getItemsInBasket(): CollectionReference {
        return db.collection("users/${user!!.uid}/basket")
    }

    fun removeItemFromBasket(item: Item): Task<Void>{
        val doc = db.collection("users/${user!!.uid}/basket/").document(item.uuid)
        return doc.delete()
    }
}