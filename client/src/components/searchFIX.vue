<template>
<v-app id="test" class="my-auto" :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }" >
     <nav>
            <v-toolbar
            dark 
               src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg">
            </v-toolbar>
            
            <v-navigation-drawer v-model="drawer" app
              absolute
      dark
      src="https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg"
      width="15%"
             permanent
              expand-on-hover
             >
                 <v-list>
                    <v-list-item>
                        <v-list-item-content >FIX MENU</v-list-item-content>
                        <v-icon left @click="drawer = !drawer">arrow_back</v-icon>
                    </v-list-item>
                    <v-list-item to="/fix">
                        <v-list-item-action>
                            <v-icon left>account_box</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>เพิ่มข้อมูลการแจ้งซ่อม</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                  
                    <v-list-item to="/viewfix">
                        <v-list-item-action>
                            <v-icon left>assessment</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>ตารางข้อมูลการแจ้งซ่อม</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
                  <template v-slot:append>
        <div class="pa-2">
          <v-btn block color="red darken-1" to="/home">Logout</v-btn>
        </div>
      </template>
            </v-navigation-drawer>
        </nav>


 <b-container class=" h-100 my-0 align-items-center d-flex justify-content-center" >   <!-- edit to mx-auto for biggerscreen -->
  <b-container wrap class=" h-100 my-0 align-items-center d-flex justify-content-center">
    
  <div class="ccc">
    <b-card id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }" >
    <b-row ref="form" nowrap  class="bg align-items-center d-flex justify-content-center"  align-v="center" align-h="center">
     
     
        

       <b-col md=4>
          <div class="text-center">
                        <v-avatar>
                            <img src="@/assets/fix.jpg">
                        </v-avatar>
                    </div>
         <h1 align='center' class="display-1 font-weight-regular mb-2" color="black">ค้นหา Queue</h1>
         <br>
         <v-text-field
            solo
            label="ใส่เลข Queue"
            v-model="fix.queue"
            :rules="[(v) => !!v || 'Item is required']"
          ></v-text-field></b-col>
       <b-col md=4>
         <br><br><br><br>
         <v-btn class="ml-10 mb-2" @click="findFix" raised large color="primary">Show Fix</v-btn>
         </b-col> 


          <b-col lg=12 >
           <v-flex md14 xs14 lg14 xl4>
               <p class="font-weight-bold"  v-if="fixCheck != ''" >Queue Number : {{Queue}}</p>  
                <p class="font-weight-bold"  v-if="fixCheck != ''" >ลูกค้า : {{cusName}}</p> 
                 <p class="font-weight-bold"  v-if="fixCheck != ''" >พนักงานที่รับแจ้งซ่อม : {{empName}}</p>    
                 <p class="font-weight-bold"  v-if="fixCheck != ''" >ประเถทอุปกรณ์ที่นำมาซ่อม :{{fixtype}}</p>  
                 <p class="font-weight-bold"  v-if="fixCheck != ''" >ยี่ห้อ : {{brandname}}</p> 
                 <p class="font-weight-bold"  v-if="fixCheck != ''" >สีเครื่อง : {{machinecolor}}</p>  
                 <p class="font-weight-bold"  v-if="fixCheck != ''" >ปัญหา : {{fixname}}</p>           
              </v-flex>
      </b-col>
    </b-row>

      
   </b-card>
  </div>
    </b-container>
</b-container>
<v-snackbar  v-model="snaktr" :timeout="10000">{{snactexttrue}}
   <v-btn text @click="snaktr = false" >CLOSE</v-btn>
 </v-snackbar>
    

   
</v-app>
</template>
<script>
/* eslint-disable */ 
 import http from "../http-common";
  export default {
    props: {
      source: String,
    },
    name: "fix",
   data() {
    return {
      fix: {
       queue: "",
  
      },
        valid: false,
      fixCheck: false,
      Queue: "",
      cusName: "",
      empName: "",
      fixtype: "",
      brandname: "",
      machinecolor: "",
      fixname: "",
      snaktr: false,
      snactexttrue: "",
      drawer: false
    };
  },
  
  methods:{
    
     findFix() {
          http
            .get("/fix/"+ this.fix.queue)
            .then(response => {
              console.log(response);
              if (response.data.queue != null) {
                 this.Queue = response.data.queue;
                 this.cusName = response.data.customer.cusName;
                 this.empName = response.data.createdBy.empName;
                 this.fixtype = response.data.fixtype.fixtypename;
                this.brandname = response.data.brand.brandname;
                 this.machinecolor = response.data.machinecolor.machinecolor;
                 this.fixname = response.data.fixname;
                this.fixCheck = response.status;
                this.snaktr = true;
                 this.snactexttrue =('ค้นหาสำเร็จ')
               
                     
              } else {
                 this.snaktr = true;
                 this.snactexttrue =('ไม่พบข้อมูล') 
              }          
            })
            .catch(e => {
              console.log(e);
            });
          this.submitted = true;
        },
        clear() {
      this.$refs.form.reset();
      this.fixCheck = false;
    },

    refreshList() {
    },
    
  },
      mounted() {
      }
  
};
</script>

<style>

.bn{
  max-height: 1090px;
  background-color: #161717;
  display: block;
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
@font-face {
    font-family: 'Kulachat'; /*a name to be used later*/
    src: url('../assets/Kulachat.ttf'); /*URL to font*/
}
</style>