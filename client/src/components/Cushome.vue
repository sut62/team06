<template>
<v-app id="test2" class="my-auto" :style="{ backgroundImage: 'url(' + require('@/assets/d.jpg') + ')' }" >

  
 <b-container  fill-height class=" h-100 my-0 align-items-center d-flex justify-content-center mtg" >   <!-- edit to mx-auto for biggerscreen -->
  <b-container wrap class=" my-0 align-items-center d-flex justify-content-center mtg">

  <div class="ccc">
    <b-card style="height: 400px" bg-variant="secondary" border-variant="primary" dark nowrap fill-height class=" mx-auto my-auto row align-self-center mgt  align-items-center   bg-secondary align-items-stretch d-flex justify-content-center "  >
        <pre></pre>
        <pre></pre>
        <pre></pre>
        <pre></pre>
        <pre></pre>

        <h1  class="text-xl-center text-white fond align-items-center d-flex justify-content-center ic ">ระบบแจ้งซ่อมคอมพิวเตอร์ </h1>

        <pre></pre>
        <pre></pre>
        <pre></pre>
        <pre></pre>
        <pre></pre>

        <b-col md=12 class="text-center">
            <v-btn class="fond align-items-center" rounded large color="danger" >
                <router-link  to="/contact">ติดต่อสอบถาม</router-link></v-btn>

               
        </b-col>
        

    </b-card>
  </div>
    </b-container>
</b-container>



   
</v-app>
</template>
<script>

</script>

<style scoped>

.bn{
  max-height: 1090px;
  background-color: #161717;
  display: block;
}
.container{
  height: 599px;
}
.ccc{
  width: 1920px;
}
.card{
  max-height: 100%;
}
.card-body {
  max-width: 100%;
}
.fond{
    font-family: Kulachat ;
}
.mgt{
    margin-top: 400px;
}
@font-face {
    font-family: 'Kulachat'; /*a name to be used later*/
    src: url('../assets/Kulachat.ttf'); /*URL to font*/
}
</style>