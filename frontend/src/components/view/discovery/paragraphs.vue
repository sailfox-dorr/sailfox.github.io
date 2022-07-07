<template>
  <div class="paragraphs-container">
    <!-- 最新诗歌 -->
    <div class="news">
      <h3 class="title">
        最新诗歌
      </h3>
      <div class="items">
          <div class="paragraphs">
            <span name = "title"> 作者: {{ poem.title }}</span><br/>
            <span name = "author">{{ poem.author }} </span><br/>
            <span name = "paragraphs">{{ poem.paragraphs }} </span>
          </div>
        </div>
      </div>
    </div>

</template>

<script>
export default{

data(){
    return {
          poem :{
            title:"123",
            author:"李白",
            paragraphs:"haha "
        }
    }
}

}

</script>