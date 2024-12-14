package com.inforcap.exampleapiresttmdb.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.inforcap.exampleapiresttmdb.R
import com.inforcap.exampleapiresttmdb.databinding.ActivityDetailsBinding
import com.inforcap.exampleapiresttmdb.model.FigureDetailEntity
import com.squareup.picasso.Picasso


class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras?.getBundle("BUNDLE")
        val detail = bundle?.getParcelable<FigureDetailEntity>("DETAIL")

        detail?.run {
            Glide.with(applicationContext)
                .load(logo)
                .fitCenter()
                .error(R.drawable.baseline_error_24)
                .into(binding.imageViewStore)
        }
    }


}