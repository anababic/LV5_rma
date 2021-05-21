package com.example.lv5_babicana_zad1

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity(){
    private lateinit var mSound:SoundPool
    var mSoundLoaded:Boolean=false;
    var mSoundsRep:HashMap<Int,Int> = HashMap()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFromRaw();

    }

    fun onClick(v: View?) {
        Toast.makeText(this, mSoundLoaded.toString(), Toast.LENGTH_SHORT)
        if(this.mSoundLoaded==false) return
        when(v!!.getId()){
            R.id.birds -> playSound(R.raw.birds)
            R.id.lion -> playSound(R.raw.lion)
        }

    }
    private fun loadFromRaw() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mSound = SoundPool.Builder().setMaxStreams(10).build()
        } else {
            this.mSound = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        }

        this.mSound.setOnLoadCompleteListener { _, _, _ -> mSoundLoaded = true }
        this.mSoundsRep[R.raw.birds] = this.mSound.load(this, R.raw.birds, 1)
        this.mSoundsRep[R.raw.lion] = this.mSound.load(this, R.raw.lion, 1)
    }
    fun playSound(selectedSound: Int) {
        Toast.makeText(this, mSoundLoaded.toString(), Toast.LENGTH_SHORT)

        val soundID = this.mSoundsRep[selectedSound] ?: 0
        this.mSound.play(soundID, 1f, 1f, 1, 0, 1f)
    }

}