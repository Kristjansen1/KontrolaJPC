package com.example.kontrolajpc.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi

class Haptic {
    @RequiresApi(Build.VERSION_CODES.Q)
    fun vibrate(vibrator: Context) {
        val effect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK)
        val v = vibrator.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        v.vibrate(effect)
    }

}