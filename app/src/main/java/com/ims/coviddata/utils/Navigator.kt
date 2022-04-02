package com.ims.coviddata.utils

import android.app.Activity
import android.content.Intent
import com.ims.coviddata.BookMarkActivity

class Navigator {

    companion object {

        fun navigateToBookMark(activity: Activity) {
            val intent = Intent(activity, BookMarkActivity::class.java)
            activity.startActivity(intent)
        }

    }
}