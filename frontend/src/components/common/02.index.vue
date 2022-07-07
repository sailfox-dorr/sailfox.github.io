<template>
  <div class="index-container">
    <!-- 导航区域 -->
    <div class="nav" >
      <ul>
        <li>
          <router-link to="/poets">
            <span class="iconfont icon-find-music"></span>
            诗人
          </router-link>
        </li>
        <li>
          <router-link to="/poem">
            <span class="iconfont icon-music-list"></span>
            诗歌
          </router-link>
        </li>
        <li>
          <router-link to="/paragraphs">
            <span class="iconfont icon-music"></span>
            排行榜
          </router-link>
        </li>
        <li>
          <router-link to="/mvs">
            <span class="iconfont icon-mv"></span>
            用户中心
          </router-link>
        </li>
      </ul>
    </div>
    <!-- 主体区域 -->
    <div class="main">
      <router-view></router-view>
    </div>
    <!-- 播放标签 -->
    <div class="player">
      <!-- autoplay 自动播放 -->
      <audio :src='musicUrl' controls autoplay></audio>
    </div>
  </div>
</template>

<script>
export default {
  name: 'index',
  data() {
    return {
      musicUrl:"http://m7.music.126.net/20200303182550/8aa5971b1630d1527a922ccd2b97f392/ymusic/035d/0109/520e/478f86cc9f6c6539f7c8ed3e06c1bf8e.mp3"
    };
  }
};
</script>

<style >

</style>
