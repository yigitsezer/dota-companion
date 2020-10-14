package com.example.dotacompanion.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.dotacompanion.R

class HeroView : LinearLayout {
    private var heroId = 0
    private var heroImage: ImageView
    private var heroName: TextView

    constructor(context: Context?, heroId: Int) : super(context) {
        inflate(context, R.layout.hero_view, this)
        this.heroId = heroId
        heroImage = findViewById(R.id.list_hero_icon)
        heroName = findViewById(R.id.list_hero_name)
    }

    constructor(context: Context?) : super(context) {
        inflate(context, R.layout.hero_view, this)
        heroImage = findViewById(R.id.list_hero_icon)
        heroName = findViewById(R.id.list_hero_name)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        inflate(context, R.layout.hero_view, this)
        heroImage = findViewById(R.id.list_hero_icon)
        heroName = findViewById(R.id.list_hero_name)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.HeroView)
        heroImage.setImageDrawable(attributes.getDrawable(R.styleable.HeroView_list_hero_image))
        heroName.text = attributes.getString(R.styleable.HeroView_list_hero_name)
        attributes.recycle()
    }

    fun setHeroName(heroName: CharSequence?) {
        this.heroName.text = heroName
    }

    fun setHeroImage(resId: Int) {
        heroImage.setImageResource(resId)
    }

    fun setHeroId(id: Int) {
        heroId = id
        tag = id
    }

    override fun setOnClickListener(l: OnClickListener?) {
        //TODO: navigate to new fragment based on id
        super.setOnClickListener(l)
    }
}