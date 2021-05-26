package com.logan.tmobileapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.logan.tmobileapp.databinding.ImageTitleDescriptionItemBinding
import com.logan.tmobileapp.databinding.TitleDescriptionItemBinding
import com.logan.tmobileapp.databinding.TitleItemBinding
import com.logan.tmobileapp.extras.loadImage
import com.logan.tmobileapp.models.CardDTO

class CardAdapter(

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var cardsList: List<CardDTO>

    private inner class TextViewHolder(private val binding: TitleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardDTO) = with(binding) {
            titleTv.text = card.card.value
            titleTv.textSize = card.card.attributes.font.size.toFloat()
            titleTv.setTextColor(Color.parseColor(card.card.attributes.textColor))
        }
    }

    private inner class TitleViewHolder(private val binding: TitleDescriptionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardDTO) = with(binding) {
            descriptionTitleTv.text = card.card.title.value
            descriptionTitleTv.textSize = card.card.title.attributes.font.size.toFloat()
            descriptionTitleTv.setTextColor(Color.parseColor(card.card.title.attributes.textColor))

            descriptionDescriptionTv.text = card.card.description.value
            descriptionDescriptionTv.textSize = card.card.description.attributes.font.size.toFloat()
            descriptionDescriptionTv.setTextColor(Color.parseColor(card.card.description.attributes.textColor))
        }
    }

    private inner class ImageViewHolder(private val binding: ImageTitleDescriptionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardDTO) = with(binding) {
            imageIv.layoutParams.height = card.card.image.size.height
            imageIv.layoutParams.width = card.card.image.size.width
            imageIv.loadImage(card.card.image.url)

            imageTitleTv.text = card.card.title.value
            imageTitleTv.textSize = card.card.title.attributes.font.size.toFloat()
            imageTitleTv.setTextColor(Color.parseColor(card.card.title.attributes.textColor))

            imageDescriptionTv.text = card.card.description.value
            imageDescriptionTv.textSize =
                card.card.description.attributes.font.size.toFloat()
            imageDescriptionTv.setTextColor(Color.parseColor(card.card.description.attributes.textColor))
        }
    }

    private var results: List<CardDTO> = listOf()

    fun loadData(cards: List<CardDTO>) {
        results = cards
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> TextViewHolder(
                TitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ).apply {
                bind(cardsList[adapterPosition])
            }
            1 -> TitleViewHolder(
                TitleDescriptionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ).apply {
                bind(cardsList[adapterPosition])
            }
            2 -> ImageViewHolder(
                ImageTitleDescriptionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ).apply {
                bind(cardsList[adapterPosition])
            }
            else -> throw error("Error in onCreateViewHolder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> (holder as TextViewHolder)
            1 -> (holder as TitleViewHolder)
            2 -> (holder as ImageViewHolder)
        }
    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

    override fun getItemViewType(position: Int): Int {
        val index = cardsList[position]
        return when (index.cardType) {
            "text" -> 0
            "title_description" -> 1
            "image_title_description" -> 2
            else -> error("Error in getItemViewType")
        }
    }
}