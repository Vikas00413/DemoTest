package com.demo.demotext.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.appstreet.assignment.util.toast
import com.demo.demotext.R
import com.demo.demotext.viewmodel.GetHotDataViewModel
import com.demo.model.response.RedditMainResponse

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel: GetHotDataViewModel =
            ViewModelProviders.of(this).get(GetHotDataViewModel::class.java)
        viewModel.getHotData()
        viewModel.response!!.observe(this, Observer { datawrapper ->
            if (datawrapper != null) {
                if (datawrapper.isShowLoader) {
                    //progressBar.visibility = View.VISIBLE

                } else {
                    // progressBar.visibility = View.GONE

                }
                if (datawrapper.data != null) {
                    var response: RedditMainResponse = datawrapper.data!!
                    if (response.data!!.children!!.isNotEmpty()) {

                        AppUtil.showLogMessage("e", "size", "" + response.data!!.children!!.size)

                    } else {

                    }

                } else {
                    datawrapper.error?.let {
                        toast(datawrapper.error!!.error!!)
                    }
                }


            }


        })
    }
}
