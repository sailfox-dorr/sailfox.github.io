<template>
  <div class="discovery-container">
    <!-- 最新诗歌 -->
    <div class="news">
      <h3 class="title">
        最新诗歌
      </h3>
      <div class="items">
        <div class="item" v-for="(item, index) in poems" :key="index">
          <div class="img-wrap">
            <div class="song-name" @click="readPoem(index)">{{ item.title }}
            </div>
          </div>
        </div>
        <!-- <div class="poem-detail" >
          <div class="poem_detail1" >
            <h2>{poem.paragraphs}</h2>
          </div>
        </div> -->
      </div>
    </div>

        <!-- 分页器
      total 总条数
      current-page 当前页
      page-size 每页多少条数据
      current-change 页码改变事件
     -->
    <el-pagination
      @current-change="handleCurrentChange"
      background
      layout="prev, pager, next"
      :total="total"
      :current-page="page"
      :page-size="10"
    ></el-pagination>

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
        list: [],
        // 最新音乐
        poems: [],
        // 推荐mv
        mvs:[],
        poem: {
          name:"",
          title:"",
          paragraphs:"",
          decades:"" 
        }
      }
    },
    created() {

      // 查询诗人
      axios({
        url: 'http://localhost:18071/poem/queryPoemByKeyword?keyWord=苏轼&pageNum=1&pageSize=5',
        method: 'get'
      }).then(res => {
        this.poems = res.data.data.list
      })
    },
    methods: {
      readPoem(index){
        console.log(1111)
         this.poem = this.poems[index]
      },
            // 页码改变事件
      handleCurrentChange(val) {
        // console.log(`当前页: ${val}`)
        // 保存页码
        this.page = val
        // 重新获取数据
        this.listData()
      }
    
    }
  }
</script>

<style></style>
