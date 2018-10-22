package android.rezkyauliapratama.com.mmppdc.screens.dashboard

import android.app.Activity
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Build
import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.mmppdc.screens.common.views.BaseObservableViewMvc
import android.rezkyauliapratama.com.mmppdc.screens.pdc.history.HistoryFragment
import android.rezkyauliapratama.com.mmppdc.screens.pdc.waiting.PdcFragment
import android.rezkyauliapratama.com.mmppdc.utils.DimensionConverter
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.view.*
import android.widget.LinearLayout
import com.app.infideap.stylishwidget.view.ATextView

class MainViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) :
        BaseObservableViewMvc<MainViewMvc.Listener>(), MainViewMvc, NavigationView.OnNavigationItemSelectedListener{


    var binding : ActivityMainBinding = DataBindingUtil.inflate(inflater, R.layout.activity_main,parent,false)


    val fragments: MutableList<Fragment> = mutableListOf()
    private lateinit var fragment: Fragment


    private lateinit var tabAdapter: LfPagerAdapter


    lateinit var arrowDrawable: DrawerArrowDrawable
    lateinit var toggle: ActionBarDrawerToggle
    private var id = R.id.nav_home

    init{

        dataBinding = binding
        initTab()
        initViewPager()
        initDrawableMenu()

    }

    fun logOut() {
        val builder = getContext()?.let {
            AlertDialog.Builder(it)
                    .setMessage(R.string.logout_confirmation)

                    .setPositiveButton(R.string.yes) { _, _ ->
                        for(listener in listeners){
                            listener.onLogout()
                        }
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.cancel() }
        }

        val dialog = builder?.create()
        dialog?.show()
    }

    private fun initDrawableMenu() {


        toggle = object : ActionBarDrawerToggle(
                getContext() as Activity, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            var defaultElevation = binding.drawerLayout.drawerElevation
            var defaultWidth = binding.appBar.width
            var defaultHeight = binding.appBar.height


            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                if (drawerView === binding.navView) {
                    updateOffset(slideOffset)
                    arrowDrawable.setProgress(slideOffset)
                    binding.drawerLayout.drawerElevation = 0F
                } else
                    binding.drawerLayout.drawerElevation = defaultElevation
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.navView.elevation = 0F
        }

        arrowDrawable = toggle.getDrawerArrowDrawable()
        //        arrowDrawable.setBarLength(10);

        getContext()?.apply{
            val dimensionConverter = DimensionConverter()
            arrowDrawable.barLength = dimensionConverter.stringToDimension("24dp", resources.displayMetrics)
            arrowDrawable.barThickness = dimensionConverter.stringToDimension("2dp", resources.displayMetrics)
            arrowDrawable.gapSize = dimensionConverter.stringToDimension("8dp", resources.displayMetrics)
            arrowDrawable.color = ContextCompat.getColor(this, R.color.colorBlack_1000)

        }

        binding.drawerLayout.setDrawerListener(toggle)
        toggle.syncState()


        binding.navView.setNavigationItemSelectedListener(this)
        binding.drawerLayout.setScrimColor(Color.TRANSPARENT)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        showPage(id)

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showPage(id: Int) {
        var title = ""
        if (id == R.id.nav_home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.appBar.elevation = 0F
            }
            title = "Home"
        } else if (id == R.id.nav_setting) {
            title = "Setting"
        } else if (id == R.id.nav_logout){
            logOut()
            return
        }

        val item = binding.navView.menu.findItem(id)
        if (item.groupId == R.id.group_nav) {
            binding.tvTitle.text = title
            for(listener in listeners){
                listener.onDrawerMenuInteraction(id)
            }
        }

    }
    private fun updateOffset(slideOffset: Float) {
        binding.navView.alpha = slideOffset
        binding.coordinatorLayout.x = (binding.navView.width + 10f) * slideOffset
        val defaultHeight = (binding.navView.parent as ViewGroup).height
        val params = binding.coordinatorLayout.layoutParams
        val diffHeight = defaultHeight * (0.1f * slideOffset)

        if (slideOffset == 0f)
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
        else
            params.height = (defaultHeight - diffHeight).toInt()

        binding.coordinatorLayout.layoutParams = params
        ViewCompat.setElevation(binding.coordinatorLayout, 20 * slideOffset)

    }



    private fun initTab() {
        val tabs = arrayOf(
                binding.content?.tabLayout?.newTab()?.setText("PDC Waiting"),
                binding.content?.tabLayout?.newTab()?.setText("History")

        )

        for (tab in tabs) {
            val layout = LinearLayout(getContext())
            layout.orientation = LinearLayout.VERTICAL
            layout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layout.weightSum = 1f
            val newTab = ATextView(getContext())
            newTab.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            newTab.gravity = Gravity.CENTER
            newTab.maxLines = 1
            newTab.text = tab?.text

            getContext()?.let { ContextCompat.getColor(it, R.color.colorWhite) }?.let { newTab.setTextColor(it) }

            layout.addView(newTab)

            tab?.customView = layout
            tab?.let { binding.content?.tabLayout?.addTab(it) }
        }

        binding.content?.tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                fragment = fragments[tab.position]
                binding.content?.viewPager?.currentItem = tab.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun initViewPager() {
        fragments.add(PdcFragment.newInstance())
        fragments.add(HistoryFragment.newInstance())

        fragment = fragments[0]
        this.tabAdapter = LfPagerAdapter((getContext() as FragmentActivity).supportFragmentManager, fragments)

        binding.content?.viewPager?.offscreenPageLimit = 3
        binding.content?.viewPager?.adapter = tabAdapter
        binding.content?.viewPager?.isPagingEnabled = false
        binding.content?.viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.content?.tabLayout))
    }

    class LfPagerAdapter (fm: FragmentManager, private val fragments:MutableList<Fragment>): FragmentStatePagerAdapter(fm)
    {

        private val NUMITEMS = 2



        override fun getCount(): Int {
            return NUMITEMS
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                1 -> fragments[1]
                else -> fragments[0]
            }
        }


    }

}