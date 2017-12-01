package mekotlinapps.dnyaneshwar.`in`.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*
import org.jetbrains.anko.toast

/**
 * Created by Dnyaneshwar Dalvi on 30/11/17.
 */
class SecondScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        toast(getString(R.string.thank_you))

        playAgain.setOnClickListener {
            startActivity(Intent(this, FirstScreen::class.java))
            finish()
        }
    }
}
