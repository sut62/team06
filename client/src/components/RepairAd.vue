<template>
<v-app id="test" class="my-auto" :style="{ backgroundImage: 'url(' + require('@/assets/b.jpg') + ')' }" >
      <nav>
            <v-toolbar  color="#00BCD4" dark>
                <v-app-bar-nav-icon @click.native.stop="drawer = !drawer"></v-app-bar-nav-icon>
                &nbsp; &nbsp;&nbsp; &nbsp;
                <v-toolbar-title>
                    <span> Repair</span>
                    <span>Computer</span>
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn flat color="grey" to="/home">
                    <span>Sign Out </span>
                    <v-icon right>exit_to_app</v-icon>
                </v-btn>
            </v-toolbar>

            <v-navigation-drawer v-model="drawer" app
              absolute
     color="#00BCD4"
     height="100%"
      width="15%"
             permanent
              expand-on-hover
             >
                <v-list>
                    <v-list-item>
                        <v-list-item-content >adminrepair MENU</v-list-item-content>
                        <v-icon left @click="drawer = !drawer">arrow_back</v-icon>
                    </v-list-item>
                    <v-list-item to="/repairindex">
                        <v-list-item-action>
                            <v-icon left>account_box</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>Adminrepairindex</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item to="/viewrepairindex">
                        <v-list-item-action>
                            <v-icon left>assessment</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>View AdminRepair</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
        </nav>

  <b-container md=6 class=" h-100 align-items-center d-flex my-12 ">
  
    <b-card  md=12 dark nowrap fill-height class="my-2 mx-0 bg-secondary align-items-center d-flex justify-content-center " style="width:600px  height:2200px" >
    <b-row md=12 ref="form" nowrap  class="bg align-items-center d-flex justify-content-center">
     
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
       <b-col md=6>
         <v-text-field
            solo
            label="เลขบัตรลูกค้า"
            v-model="repaircp.cmailp"
            :rules="[(v) => !!v || 'Item is required']"
            class="fond"
          ></v-text-field></b-col>
       <b-col md=6>
         <v-text-field
            solo
            label="รหัสงาน"
            v-model="repaircp.rpwork"
            :rules="[(v) => !!v || 'Item is required']"
            class="fond"
          ></v-text-field></b-col>
      <b-col md=6>
        <v-text-field
            solo
            label="อาการเสีย"
            v-model="repaircp.breakdown"
            :rules="[(v) => !!v || 'Item is required']"
            required
            class="fond"
          ></v-text-field></b-col>
       <b-col md=6>
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
      <b-col md=6>
          <v-text-field md=12
              v-model="repaircp.partfix"
              label="ราคารวม"
              class="fond"
              solo
            />   
      </b-col>
    </b-row>
      <b-row class="align-items-center d-flex justify-content-center">
       <v-btn  md=6 class="mb-7 fond" @click="findCustomer" rounded large color="accent">ค้นหาเลขบัตรลูกค้า</v-btn>
        
          <v-btn md=6 class="mb-7 fond" @click="saverepair" rounded large color="accent">บันทึก</v-btn>
      </b-row>
  </b-card>
 

   
  </b-container>


    <v-snackbar  v-model="snaktr" :timeout="10000">{{snactexttrue}}
                      <v-btn text @click="snaktr = false" >CLOSE</v-btn>
                     </v-snackbar>
   <v-toolbar flat short color="#EEEEEE">
        <v-spacer></v-spacer>
        <span>COPYRIGHT © 2020 REPAIR COMPUTER. BY TEAM06 OF SOFTWARE ENGINEERING.</span>
      </v-toolbar>
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
        partfix:"",
        status: "",
        typerp: "",

      },
        valid: false,
        useforrepair:[],
        pf : null,
          snaktr: false,

        snactexttrue: ""
    };
  },

  methods:{
    splittxt(){
    return this.useforrepair.split(":").join();
    console.log(this.useforrepair);
     
    },
    addElement: function(){
      this.useforrepair.push({
        partfixs:''
      });
    },
    removeElement: function(index){
    
        this.useforrepair.splice(index,1);
      

    },
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
        this.partfix = response.data;
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
                   this.snaktr = true;
                  this.snactexttrue ="ค้นหาสำเร็จด้วยหมายเลข"+this.repaircp.cmailp
                
              
              } else {
                   this.snaktr = true;
                  this.snactexttrue ="ไม่พบ ID ที่ค้นหา"
           
              }          
            })
            .catch(e => {
              console.log(e);
            });
        },
    saverepair(){
      http
      .post(
        //"/Adminrepairindex/{breakdown}/{branch_id}/{brand_id}/{employee_id}/{identification}/{statusrepair_id}/{type_id}/{useforrepair}")
        "/Adminrepairindex/"+
         this.repaircp.breakdown + 
        "/" +
        this.repaircp.partfix +
        "/" +
        this.repaircp.rpwork +
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
        this.pf,
        this.repaircp,
        console.log(this.repaircp),
      
        
      )
      .then(response =>{
        console.log(response);
        if(response = true){
            this.snaktr = true;
            this.snactexttrue ="บันทึกข้อมูลเสร็จสิ้น"
            if(this.snaktr = true){
            this.$router.push('/viewrepairindex')
            }
            
        }
      })
      .catch(e=>{
        console.log(e);
        if(e = true){
             this.snaktr = true;
            this.snactexttrue ="บันทึกข้อมูลไม่สำเร็จ"
          
        }
      })

    },
    getAdminrepair(){
      http
      .get("/Adminrepairindex")
      .then(response =>{
        this.items = response.data;
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