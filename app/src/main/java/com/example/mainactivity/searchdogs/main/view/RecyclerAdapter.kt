package com.example.mainactivity.searchdogs.main.view

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mainactivity.R
import com.example.mainactivity.searchdogs.extensions.inflate
import com.example.mainactivity.searchdogs.main.model.Dog
import com.example.mainactivity.searchdogs.util.ImageLoader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_doggo.view.*

class RecyclerAdapter :
    ListAdapter<Dog, RecyclerAdapter.UserDateViewHolder>(UserDataAdapterListDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDateViewHolder =
        UserDateViewHolder(parent.inflate(R.layout.item_doggo))

    override fun onBindViewHolder(holder: UserDateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class UserDataAdapterListDiff : DiffUtil.ItemCallback<Dog>() {

        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem.breed == newItem.breed
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
        }
    }

    inner class UserDateViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(dog: Dog) {
            containerView.breed_name.text = dog.breed?.capitalize()
            dog.imageUrl?.let { it1 ->
                ImageLoader.loadImageWithCircularCrop(
                    containerView.context,
                    it1,
                    containerView.doggo_image
                )
            }
            if (dog.isTopDog) {
                this.containerView.card_layout.setCardBackgroundColor(Color.MAGENTA)
            } else {
                this.containerView.card_layout.setCardBackgroundColor(
                    containerView.context.getColor(
                        R.color.colorPrimary
                    )
                )
            }
        }
    }
}