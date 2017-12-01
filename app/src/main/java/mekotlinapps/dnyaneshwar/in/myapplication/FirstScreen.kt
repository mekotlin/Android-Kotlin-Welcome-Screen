package mekotlinapps.dnyaneshwar.`in`.myapplication

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class FirstScreen : AppCompatActivity() {

    var layout_: IntArray = intArrayOf(R.layout.screen_one, R.layout.screen_two, R.layout.screen_three, R.layout.screen_four, R.layout.screen_five)
    var mAdapter: CustomViewpagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        mAdapter = CustomViewpagerAdapter(this)
        viewPager.adapter = mAdapter

        addDot(0)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                addDot(position)

                if (position == layout_!!.size - 1) {
                    btnNext.text = getString(R.string.start)
                    btnSkip.visibility = View.GONE
                } else {
                    btnNext.text = getString(R.string.next)
                    btnSkip.visibility = View.VISIBLE
                }
            }

        })

        btnSkip.setOnClickListener { lounchHomeScreen() }

        btnNext.setOnClickListener {
            var currentItem = getItem(+1)

            if (currentItem < layout_.size) {
                viewPager.setCurrentItem(currentItem)
            } else {
                lounchHomeScreen()
            }
        }
    }

    private fun lounchHomeScreen() {
        startActivity(Intent(this, SecondScreen::class.java))
        finish()
    }

    private fun getItem(i: Int): Int {
        return viewPager.currentItem + i
    }

    inner class CustomViewpagerAdapter(val context: Context) : PagerAdapter() {

        var layoutInflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {

            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
            val view = layoutInflater?.inflate(layout_!![position], container, false)
            container?.addView(view)

            return view!!
        }

        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return layout_!!.size
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            val view: View = `object` as View
            container?.removeView(view)
        }
    }

    fun addDot(currentPage: Int) {

        var dots = arrayOfNulls<TextView>(layout_.size)
        var colorActive = resources.getIntArray(R.array.colot_active)
        var colorInactive = resources.getIntArray(R.array.color_inactive)

        layout_dots.removeAllViews()

        for (i in 0 until dots.size) {

            dots[i] = TextView(applicationContext)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                dots[i]!!.setText(Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY))
            } else {
                dots[i]!!.setText(Html.fromHtml("&#8226;"))
            }
            dots[i]!!.textSize = 37F
            dots[i]!!.setTextColor(colorInactive[currentPage])

            layout_dots.addView(dots[i])

            if (dots.size > 0) {
                dots[currentPage]?.setTextColor(colorActive[currentPage])
            }
        }
    }
}
