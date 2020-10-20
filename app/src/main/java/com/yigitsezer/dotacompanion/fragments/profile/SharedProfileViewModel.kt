package com.yigitsezer.dotacompanion.fragments.profile

import androidx.lifecycle.ViewModel

/**
 * This ViewModel is shared between PrimaryProfileFragment and FetchedProfileFragment
 */
class SharedProfileViewModel : ViewModel() {
    private var text: CharSequence? = null

    fun setText(input: CharSequence) {
        text = input.toString()
    }

    fun getText(): CharSequence? {
        return text
    }
}