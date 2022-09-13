package com.example.leeloologin.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.example.leeloologin.R
import com.example.leeloologin.R.layout
import com.example.leeloologin.adapter.GridViewAdapter
import com.example.leeloologin.adapter.ItemListAdapter
import com.example.leeloologin.databinding.ActivityDashboardBinding
import com.example.leeloologin.model.GridViewModel
import com.example.leeloologin.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback
import org.imaginativeworld.oopsnointernet.dialogs.signal.NoInternetDialogSignal
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class DashboardActivity : AppCompatActivity() {


    private val imageList = ArrayList<SlideModel>() // Create image list

    private var noInternetDialog: NoInternetDialogSignal? = null

    private val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }


    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModel>
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout.activity_dashboard)

        getSlidingBanner()

        getUserList()

        getGridView()
    }

    private fun getGridView() {
        // initializing variables of grid view with their ids.
        courseGRV = findViewById(R.id.gridView)
        courseList = ArrayList<GridViewModel>()

        // on below line we are adding data to
        // our course list with image and course name.
        courseList = courseList + GridViewModel("Manage Positions", R.drawable.ic_launcher_foreground)
        courseList = courseList + GridViewModel("Leaderboard", R.drawable.ic_launcher_foreground)
        courseList = courseList + GridViewModel("Competitions", R.drawable.ic_launcher_foreground)
        courseList = courseList + GridViewModel("Blog", R.drawable.ic_launcher_foreground)

        // on below line we are initializing our course adapter
        // and passing course list and context.
        val courseAdapter = GridViewAdapter(courseList = courseList, this)

        // on below line we are setting adapter to our grid view.
        courseGRV.adapter = courseAdapter

        // on below line we are adding on item
        // click listener for our grid view.
        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // inside on click method we are simply displaying
            // a toast message with course name.

            when (courseList[position].courseName) {
                "Manage Positions" -> {
                    Toast.makeText(
                        applicationContext, " Manage Positions selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                "Leaderboard" -> {
                    Toast.makeText(
                        applicationContext, " Leaderboard selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                "Competitions" -> {
                    Toast.makeText(
                        applicationContext, "Competitions selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                "Blog" -> {
                    Toast.makeText(
                        applicationContext, "Blog selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getUserList() {
        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        val userListAdapter = ItemListAdapter {}
        binding.rvUserList.adapter = userListAdapter
        GlobalScope.launch(Dispatchers.IO) {
            userListAdapter.submitList(database.loginresponseDao().getAll())
        }
    }

    private fun getSlidingBanner() {
        // imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

//        imageList.add(SlideModel("https://www.filmibeat.com/ph-big/2022/06/samantha-akkineni_165535698700.jpg"))
//        imageList.add(SlideModel("https://www.filmibeat.com/ph-big/2022/03/samantha-akkineni_164758107210.jpg"))
//        imageList.add(SlideModel("https://www.filmibeat.com/ph-big/2022/03/samantha-akkineni_164758107200.jpg"))
//
//        val imageSlider = findViewById<ImageSlider>(id.image_slider)
//        imageSlider.setImageList(imageList)

        val carousel = binding.carousel

        carousel.registerLifecycle(lifecycle)

        val list = mutableListOf<CarouselItem>()

        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1611974789855-9c2a0a7236a3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2370&q=80"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1583752028088-91e3e9880b46?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1674&q=80"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1620266757065-5814239881fd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1744&q=80"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1620808244394-f4b654ff7d8b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1744&q=80"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://previews.123rf.com/images/dalebor/dalebor1804/dalebor180400020/100405169-green-bull-and-red-bear-the-concept-of-stock-market-trading-.jpg"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1642790551116-18e150f248e3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1633&q=80"
            )
        )

        carousel.setData(list)

    }

    override fun onResume() {
        super.onResume()
        NoInternetDialogSignal.Builder(
            this,
            lifecycle
        ).apply {
            dialogProperties.apply {
                connectionCallback = object : ConnectionCallback { // Optional
                    override fun hasActiveConnection(hasActiveConnection: Boolean) {
                        // ...

                    }
                }

                cancelable = false // Optional
                noInternetConnectionTitle = "No Internet" // Optional
                noInternetConnectionMessage =
                    "Check your Internet connection and try again." // Optional
                showInternetOnButtons = true // Optional
                pleaseTurnOnText = "Please turn on" // Optional
                wifiOnButtonText = "Wifi" // Optional
                mobileDataOnButtonText = "Mobile data" // Optional

                onAirplaneModeTitle = "No Internet" // Optional
                onAirplaneModeMessage = "You have turned on the airplane mode." // Optional
                pleaseTurnOffText = "Please turn off" // Optional
                airplaneModeOffButtonText = "Airplane mode" // Optional
                showAirplaneModeOffButtons = true // Optional
            }
        }.build()
    }

    override fun onPause() {
        super.onPause()
        noInternetDialog?.destroy()
    }

}