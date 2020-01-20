<template>
<v-app id="test" class="my-auto" :style="{ backgroundImage: 'url(' + require('@/assets/b.jpg') + ')' }" >
      <div class="bn bg-dark">
                <v-app-bar icon="wrench" color="deep-purple accent-4" dense dark :style="{ backgroundImage: 'url(' + require('@/assets/a.jpeg') + ')' }"
                    >
                      

                      <v-toolbar-title icons="wrench" class="fond">บันทึกการซ่อมคอมพิวเตอร์ของพนักงาน</v-toolbar-title>

                      <v-spacer></v-spacer>

                      <v-btn class="fond">
                          <router-link to="/home">ออกจากระบบ</router-link>
                      </v-btn>

                  

                  
                    </v-app-bar>
        </div>

 <b-container class=" h-100 my-0 align-items-center d-flex justify-content-center" >   <!-- edit to mx-auto for biggerscreen -->
  <b-container wrap class=" h-100 my-0 align-items-center d-flex justify-content-center">
    <b-container  wrap class="  bg-dark my-0 align-items-center d-flex justify-content-center" align-v="center" align-h="center" >
  <div class="ccc">
    <b-card dark nowrap fill-height class=" my-2 mx-0 bg-secondary align-items-stretch d-flex justify-content-center " align-v="center" align-h="center" >
    <b-row ref="form" nowrap  class="bg align-items-center d-flex justify-content-center"  align-v="center" align-h="center">
     
      <b-col md=6>
         <v-select
          label="สาขาที่รับงาน"
          v-model="repaircp.branchId"
          :items="branch"
          item-text="branchname"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          class="fond"
          solo
        ></v-select>
      </b-col>
      <b-col md=6> 
        <v-select
          label="พนักงานที่ซ่อม"
          v-model="repaircp.employeeId"
          :items="employee"
          item-text="empName"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          class="fond"
          solo
        ></v-select></b-col>
       <b-col md=3>
         <v-text-field
            solo
            label="เลขบัตรลูกค้า"
            v-model="repaircp.cmailp"
            :rules="[(v) => !!v || 'Item is required']"
            class="fond"
          ></v-text-field></b-col>
       <b-col md=3>
         <v-btn class="mb-7 fond" @click="findCustomer" rounded large color="accent">เลขบัตรลูกค้า</v-btn>
         </b-col> 
      <b-col md=3>
        <v-text-field
            solo
            label="อาการเสีย"
            v-model="repaircp.breakdown"
            :rules="[(v) => !!v || 'Item is required']"
            required
            class="fond"
          ></v-text-field></b-col>
       <b-col md=3>
         <v-select
          label="ประเภทเครื่องที่ซ่อม"
          v-model="repaircp.typerp"
          :items="typemachrepair"
          item-text="typename"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          class="fond"
          solo
        ></v-select></b-col>
      <b-col md=6>
        <v-select
          label="ยี่ห้อเครื่องที่รับซ่อม"
          v-model="repaircp.brand"
          :items="brand"
          item-text="brandname"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          class="fond"
          solo
        ></v-select></b-col>
   
      <b-col md=6>
        <v-select
          label="สถานะการซ่อม"
          v-model="repaircp.status"
          :items="status"
          item-text="currentstatus"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          class="fond"
          solo
        ></v-select>
      </b-col>

     
    </b-row>
    <b-col md=12 class="text-center">
       <v-btn class="fond" rounded large @click="saverepair"  color="primary">บันทึกข้อมูล</v-btn>
      </b-col>
  </b-card>
  </div>
    </b-container>
</b-container>
</b-container>


   
</v-app>
</template>
<script>
/* eslint-disable */ 
 import http from "../http-common";
  export default {
    props: {
      source: String,
    },
    name: "RepairCP",
   data() {
    return {
      repaircp: {
        branchId: "",
        employeeId: "",
        brand:"",
        status: "",
        typerp: ""
      },
        valid: false,
    };
  },
  methods:{
    getBranchs(){
      http
      .get("/branch")
      .then(response =>{
        this.$forceUpdate();
        this.branch = response.data;
        console.log(response.data);
      })
      .catch(e =>{
        console.log(e);

      });
    },
    getEmployees(){
      http
      .get("/employee")
      .then(response =>{
        this.$forceUpdate();
        this.employee = response.data;
        console.log(response.data);
      })
      .catch(e =>{
        console.log(e);
      });
    },
    getTypemachs(){
      http
      .get("/type")
      .then(response =>{
        this.$forceUpdate();
        this.typemachrepair = response.data;
        console.log(response.data);
      })
      .catch(e=>{
        console.log(e);
      });
    },
    getBrand(){
      http
      .get("/brand")
      .then(response =>{
        this.$forceUpdate();
        this.brand = response.data;
        console.log(response.data);
      })
      .catch(e =>{
        console.log(e);
      })
    },
    getStatus(){
      http
      .get("/statusrepair")
      .then(response =>{
        this.$forceUpdate();
        this.status = response.data;
        console.log(response.data);
      })
      .catch(e =>{
        console.log(e);
      })
    },
    getParts(){
      http
      .get("/addproduct")
      .then(response =>{
        this.$forceUpdate();
        this.parts = response.data;
        console.log(response.data);
      })
      .catch(e =>{
        console.log(e);
      })
    },

     findCustomer() {
          http
            .get("/customer/"+ this.repaircp.cmailp)
            .then(response => {
              console.log(response);
              if (response.data.cusName != null) {
                this.customerName = response.data.cusName;
                this.customerCheck = response.status;
                alert('ค้นหาสำเร็จด้วยหมายเลข'+ this.repaircp.cmailp)
                
              
              } else {
                alert('ไม่พบ ID ที่ค้นหา')
           
              }          
            })
            .catch(e => {
              console.log(e);
            });
        },
    saverepair(){
      http
      .post(
        //"Adminrepairindex/{breakdown}/{branch_id}/{brand_id}/{employee_id}/{identification}/{statusrepair_id}/{typemachrepair_id}")
        "/Adminrepairindex/"+
         this.repaircp.breakdown + 
        "/" +
        this.repaircp.branchId +
        "/" +
        this.repaircp.brand +
        "/" +
        this.repaircp.employeeId +
        "/" +
        this.repaircp.cmailp +
        "/" +
        this.repaircp.status +
        "/" +
        this.repaircp.typerp,
        
        this.repaircp,
        console.log(this.repaircp)
      )
      .then(response =>{
        console.log(response);
        if(response = true){
          alert('บันทึกข้อมูลเสร็จสิ้น')
        }
      })
      .catch(e=>{
        console.log(e);
        if(e = true){
          alert('การบันทึกข้อมูลผิดพลาด')
          
        }
      })

    },

  },
   mounted(){
      this.getBranchs();
      this.getEmployees();
      this.getTypemachs();
      this.getBrand();
      this.getStatus();
      this.getParts();
      this.getPieces();
    }
  
  
};
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

@font-face {
    font-family: 'Kulachat'; /*a name to be used later*/
    src: url('../assets/Kulachat.ttf'); /*URL to font*/
}
</style>