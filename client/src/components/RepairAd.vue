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


  <b-container md=6 class=" h-100 align-items-center d-flex my-12 ">
  
    <b-card  md=12 dark nowrap fill-height class="my-2 mx-0 bg-secondary align-items-center d-flex justify-content-center " style="width:600px  height:2200px" >
    <b-row md=12 ref="form" nowrap  class="bg align-items-center d-flex justify-content-center">
     
      <b-col md=12>
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
      <b-col md=12> 
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
       <b-col md=12>
         <v-text-field
            solo
            label="เลขบัตรลูกค้า"
            v-model="repaircp.cmailp"
            :rules="[(v) => !!v || 'Item is required']"
            class="fond"
          ></v-text-field></b-col>
       
      <b-col md=12>
        <v-text-field
            solo
            label="อาการเสีย"
            v-model="repaircp.breakdown"
            :rules="[(v) => !!v || 'Item is required']"
            required
            class="fond"
          ></v-text-field></b-col>
       <b-col md=12>
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
      <b-col md=12>
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
   
      <b-col md=12>
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
 
  </b-card>
 

      <b-container md=12 class="mx-12 h-100">
        <b-card  md=12 dark nowrap fill-height class=" h-100 my-2 mx-0 bg-secondary align-items-center d-flex justify-content-center  " style="width:500px;"  >
        
         
         <v-btn  md=6 class="mb-7 fond" @click="findCustomer" rounded large color="accent">เลขบัตรลูกค้า</v-btn>
        
          <v-btn md=6 class="mb-7 fond" @click="saverepair" rounded large color="accent">บันทึก</v-btn>
        
          <v-select md=8
              v-model="line.usepart"
              label="อะไหล่ที่ใช้ซ่อม"
              solo
            />       
         <v-btn md=3 rounded color="success">เพิ่ม</v-btn>
         <v-btn md=3 rounded color="red darken-4">ลบ</v-btn>
         <v-spacer></v-spacer>
          <br/>
       <v-data-table md=12 :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
       
       </v-data-table>
        </b-card>
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
        typerp: "",
        piece:""
      },
        headers: [
        { text: "สาขา", value: "branch.branchname" },
        { text: "พนักงาน", value: "createdBy.empName" },
        { text: "อาการเสีย", value: "breakdown" },
        { text: "ประเภทเครื่อง", value: "type.typename" },
        { text: "ยี่ห้อ", value: "brand.brandname" },
        { text: "อะไหล่ที่ใช้", value: "partfix[0].useforrepair" },
        { text: "สถานะ", value: "statusrepair.currentstatus" },
       
      
      ],
        items : [],
        valid: false,
        line: [],
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
  
      getPieces(){
      http
      .get("/partpiece")
      .then(response =>{
        this.$forceUpdate();
        this.piece = response.data;
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
        //"Adminrepairindex/{breakdown}/{addproduct_id}/{branch_id}/{brand_id}/{employee_id}/{identification}/{statusrepair_id}/{typemachrepair_id}/{partpiecesnumberforrepair_id}")
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
        this.repaircp.typerp +
        "/" +
        this.repaircp.piece,
        
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
    getAdminrepair(){
      http
      .get("/Adminrepairindex")
      .then(response =>{
        this.items = response.data;
        console.log(response.data.partfix);
        console.log(this.items);
      })
      .catch(e =>{
        console.log(e);
      });
    },
  },
   mounted(){
      this.getBranchs();
      this.getEmployees();
      this.getTypemachs();
      this.getBrand();
      this.getStatus();
      this.getAdminrepair();
      this.getParts();
      this.getPartNs();
    }
  
  
};
</script>

<style scoped>
.fond{
    font-family: Kulachat ;
}
.ccc{
  max-width: 50%;
  max-height: 100%;
}
.ccl{
  max-width: 50%;
  max-height: 100%;
}
@font-face {
    font-family: 'Kulachat'; /*a name to be used later*/
    src: url('../assets/Kulachat.ttf'); /*URL to font*/
}
</style>