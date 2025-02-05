# ExoPlayer RepeatMode Bug

When playing an Fmp4 in repeatMode != REPEAT_MODE_OFF the item fully completes playback but
halts at the end and seeks a few frames backwards. Expectation is that it starts playback from
the beginning. What's really interesting is that it works out perfectly fine when playing the
video from the raw resource directory. But if you try and play that from a server (just something
simple like `python3 -m http.server`) it starts to exhibit the issue. If I re-encode the video
file as a regular mp4 then it also acts as expected.

There's a video in the `raw` directory that you can choose to run the http file server from.