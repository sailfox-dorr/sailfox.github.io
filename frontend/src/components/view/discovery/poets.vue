<template>
  <div class="discovery-container">
    <!-- 推荐歌单 -->
    <div class="recommend">
      <h3 class="title">
        诗人清单
      </h3>
      <div class="items">
        <div class="item" v-for="(item, index) in poets" :key="index">
          <p class="name">{{ item.name }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  // 导入 axios
  import axios from 'axios'
  export default {
    name: 'discovery',
    data() {
      return {
        // 轮播图
        banners: [],
        // 推荐歌单
        poets: [],
        // 最新音乐
        songs: [],
        // 推荐mv
        mvs:[]
      }
    },
    created() {
      // console.log('created')
      // 轮播图接口
      axios({
        url: 'https://autumnfish.cn/banner',
        method: 'get'
      }).then(res => {
        // console.log(res)
        this.banners = res.data.banners.slice(0, 4)
      })

      // 查询诗人
      axios({
        url: 'http://localhost:18071/poet/queryPoetByKeyWords?keyword=苏&limit=50',
        method: 'get'
      }).then(res => {
        // console.log(res)

        console.log(res)
        this.poets = res.data.data.list
        console.log(this.poets)
      })
    },
    methods: {
      playMusic(id) {
        // console.log(id)
        axios({
          url: 'https://autumnfish.cn/song/url',
          method: 'get',
          params: {
            id // id:id
          }
        }).then(res => {
          // console.log(res)
          let url = res.data.data[0].url
          // console.log(this.$parent.musicUrl)
          // 设置给父组件的 播放地址
          this.$parent.musicUrl = url
        })
      },
       // 去mv详情页
      toMV(id){
        this.$router.push(`/mv?q=${id}`)
      },
      // 去歌单详情页
      toPlaylist(id){
        // 跳转并携带数据即可
        this.$router.push(`/playlist?q=${id}`)
      }
    }
  }
</script>

<style></style>
