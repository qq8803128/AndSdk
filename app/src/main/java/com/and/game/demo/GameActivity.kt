package com.and.game.demo

import android.app.Activity
import android.os.Bundle
import droid.mobile.games.common.alert.AlertLayout.dp
import droid.mobile.games.common.alert.AlertLoading
import droid.mobile.games.common.alert.AlertMsg
import droid.mobile.games.common.alert.AlertProgress
import droid.mobile.games.common.floater.FloaterToast
import droid.mobile.games.common.log.LogRecord
import droid.mobile.games.thirdpart.picasso.Picasso
import kotlinx.android.synthetic.main.activity_game.*
import java.util.logging.Logger

class GameActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        alertLoading.setOnClickListener {
            AlertLoading(this@GameActivity)
                    .setTitle("游戏初始化中")
                    .setDimAmount(0.1f)
                    .showAlert()
        }

        alertMsg.setOnClickListener {
            AlertMsg(this@GameActivity)
                    .setTitle("提示")
                    .setContent("确认退出游戏吗?")
                    .setPositiveButton("再玩一下",{ d,i ->
                        d.cancel()
                    })
                    .setNegativeButton("确定退出",{d,i ->
                        d.dismiss()
                    })
                    .setBackPressable(true)
                    .setBackPressedOnTouchOutside(true)
                    .addOnCancelListener {
                        LogRecord.e("cancel")
                    }
                    .addOnDismissListener {
                        LogRecord.e("dismiss")
                    }
                    .setDimAmount(0.1f)
                    .showAlert()
        }

        alertToast.setOnClickListener {
            FloaterToast.create(this@GameActivity)
                    .show()

            AlertProgress(this@GameActivity)
                    .showAlert()
        }

        Picasso.with(this)
                .load("https://upload-images.jianshu.io/upload_images/2333435-60a567e942f313a8.png?imageMogr2/auto-orient/strip|imageView2/2/w/654/format/webp")
                .resize(dp(100f), dp(100f))
                .centerCrop()
                .into(imageView)
    }
}
