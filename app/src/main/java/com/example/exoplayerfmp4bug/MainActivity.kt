package com.example.exoplayerfmp4bug

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.datasource.cache.NoOpCacheEvictor
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class MainActivity : AppCompatActivity() {

    private val playerView by lazy { findViewById<PlayerView>(R.id.player) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        playerView.player = createPlayer()
    }

    override fun onResume() {
        super.onResume()
        playerView.player?.play()
    }

    override fun onPause() {
        super.onPause()
        playerView.player?.pause()
    }

    private fun createPlayer(): Player {
        val player = ExoPlayer.Builder(this).build()

        // This works correctly.
//        player.setMediaItem(
//            MediaItem.fromUri(
//                Uri.Builder()
//                    .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
//                    .path(Integer.toString(R.raw.fmp4file))
//                    .build()
//            )
//        )

        // This doesn't work correctly.
        // I tested this out by running `python3 -m http.server` from the raw directory.
        // Just replace with your host ip addr.
        player.setMediaItem(
            MediaItem.fromUri("http://192.168.100.100:8000/fmp4file.mp4")
        )

        player.repeatMode = Player.REPEAT_MODE_ONE
        player.prepare()
        return player
    }

}