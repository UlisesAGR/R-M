package com.rm.mobile.ui.container.characters.adapter.characters

import androidx.recyclerview.widget.RecyclerView
import com.rm.mobile.databinding.ItemCharacterBinding
import com.rm.mobile.domain.model.Character
import com.rm.mobile.greenbox.R
import com.rm.mobile.utils.StatusCharacter
import com.rm.mobile.utils.loadImage
import com.rm.mobile.utils.toStatus

class CharactersViewHolder(
    private val binding: ItemCharacterBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private val context = binding.root.context

    fun render(
        item: Character?,
        onItemSelected: (Character) -> Unit,
    ) = with(binding) {
        item?.let { character ->
            character.apply {
                characterImageView.loadImage(image)
                nameTextView.text = name
                specieTextView.text = specie
                statusTextView.text = status
                setStatus(status.toStatus())
            }
            root.setOnClickListener {
                onItemSelected(character)
            }
        }
    }

    private fun setStatus(status: StatusCharacter) {
        binding.statusImageView.setColorFilter(
            context.getColor(
                when (status) {
                    StatusCharacter.ALIVE -> R.color.widgets_color
                    StatusCharacter.DEAD -> R.color.widgets_red
                    StatusCharacter.UNKNOWN -> R.color.widgets_white
                }
            )
        )
    }
}
