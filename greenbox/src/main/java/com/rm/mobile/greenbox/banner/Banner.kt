package com.rm.mobile.greenbox.banner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.rm.mobile.greenbox.R
import com.rm.mobile.greenbox.databinding.BannerBinding
import com.rm.mobile.utils.animationIn
import com.rm.mobile.utils.animationOut
import com.rm.mobile.utils.setOnSafeClickListener
import com.rm.mobile.utils.show

class Banner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        BannerBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun show(
        style: BannerType,
        title: String?,
        subTitle: String?,
        hasStatusIcon: Boolean,
        hasCloseActionButton: Boolean,
        closeAction: (() -> Unit),
    ) {
        setupBackground(style)
        setupTitle(title)
        setupSubTitle(subTitle)
        setupIcon(hasStatusIcon, style)
        setupCloseButton(hasCloseActionButton, closeAction)
    }

    private fun setupBackground(style: BannerType) {
        with(binding.containerLayout) {
            setBackgroundColor(getBackgroundColor(style))
            clearAnimation()
            binding.containerLayout.animationIn()
        }
    }

    private fun setupTitle(title: String?) {
        with(binding.titleTxt) {
            if (!title.isNullOrEmpty()) {
                text = title
                show()
            }
        }
    }

    private fun setupSubTitle(subTitle: String?) {
        with(binding.subTitleTxt) {
            if (!subTitle.isNullOrEmpty()) {
                text = subTitle
                show()
            }
        }
    }

    private fun setupIcon(hasStatusIcon: Boolean, style: BannerType) {
        if (hasStatusIcon) {
            with(binding.iconImageView) {
                setImageResource(getStatusIconResource(style))
                setColorFilter(getStatusIconColor(style))
                show()
            }
        }
    }

    private fun getBackgroundColor(style: BannerType): Int = with(context) {
        return when (style) {
            BannerType.INFORMATION -> getColor(R.color.status_info)
            BannerType.DANGER -> getColor(R.color.status_danger)
            BannerType.SUCCESS -> getColor(R.color.status_success)
            BannerType.WARNING -> getColor(R.color.status_warning)
        }
    }

    private fun getStatusIconResource(style: BannerType): Int {
        return when (style) {
            BannerType.INFORMATION -> R.drawable.ic_error
            BannerType.DANGER -> R.drawable.ic_error
            BannerType.SUCCESS -> R.drawable.ic_error
            BannerType.WARNING -> R.drawable.ic_error
        }
    }

    private fun getStatusIconColor(style: BannerType): Int = with(context) {
        return when (style) {
            BannerType.INFORMATION -> getColor(R.color.color_info)
            BannerType.DANGER -> getColor(R.color.color_danger)
            BannerType.SUCCESS -> getColor(R.color.color_success)
            BannerType.WARNING -> getColor(R.color.color_warning)
        }
    }

    private fun setupCloseButton(hasCloseActionButton: Boolean, closeAction: (() -> Unit)?) {
        if (hasCloseActionButton) {
            with(binding.closeImageView) {
                setOnSafeClickListener {
                    closeAction?.invoke()
                    hideBanner()
                }
                show()
            }
        }
    }

    private fun hideBanner() {
        clearAnimation()
        binding.containerLayout.animationOut()
    }
}
