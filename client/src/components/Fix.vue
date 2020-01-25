<template>
  <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/a.jpeg') + ')' }">
  
  <v-app-bar app color="blue darken-2">
      <v-toolbar-title class="headline text-uppercase">
        <span>ระบบบันทึกการแจ้งซ่อมคอมพิวเตอร์</span>     
     </v-toolbar-title>
      <v-spacer></v-spacer>

 
      <v-btn color="grey lighten-5">
       <router-link class="mr-1" to="/viewfix"> Fix Data </router-link>
          </v-btn>&nbsp; &nbsp;  
      <v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/home">LOGOUT</router-link>
      </v-btn>
    </v-app-bar>
    
    

    <div class='fix'>                   
    <br>
    <div class="text-center">
      <br>
      <br>
    <v-avatar>
      <img src="https://cdn.icon-icons.com/icons2/1090/PNG/512/settings_78352.png" alt="avatar">
    </v-avatar>
  </div>
      <h1 align='center'>แจ้งซ่อม</h1>
    
    <v-container nowrap  class="justify-center">
      <v-form v-model="valid" ref="form">
          

        

            <v-layout  row  class="justify-center">
            <v-col cols="5">             
                  <v-select
                  solo
                    style="width: 300px"
                    label="รายชื่อพนักงาน"
                    v-model="fix.employeeId"
                    :items="employee"
                    item-text="empName"
                    item-value="id"
                    :rules="[(v) => !!v || 'รายชื่อพนักงาน']"
                  ></v-select>
               </v-col>
            </v-layout>

           <v-layout  row  class="justify-center">
            <v-col cols="5">             
                  <v-select
                  solo
                    style="width: 300px"
                    label="รายชื่อลูกค้า"
                    v-model="fix.customerId"
                    :items="customer"
                    item-text="cusName"
                    item-value="id"
                    :rules="[(v) => !!v || 'รายชื่อลูกค้า']"
                  ></v-select>
               </v-col>
            </v-layout>
      
            <v-layout  row  class="justify-center">
            <v-col cols="5">               
                  <v-select
                  solo
                    style="width: 300px"
                    label="ประเภทอุปกรณ์"
                    v-model="fix.fixtypeId"
                    :items="fixtype"
                    item-text="fixtypename"
                    item-value="id"
                    :rules="[(v) => !!v || 'เลือกอุปกรณ์']"
                  ></v-select>
            </v-col>
            </v-layout>

            <v-layout  row  class="justify-center">
            <v-col cols="5">               
                  <v-select
                  solo
                    style="width: 300px"
                    label="Machinecolor"
                    v-model="fix.machinecolorId"
                    :items="machinecolor"
                    item-text="machinecolor"
                    item-value="id"
                    :rules="[(v) => !!v || 'เลือกสีเครื่อง']"
                  ></v-select>
            </v-col>
            </v-layout>
            

            <v-layout  row  class="justify-center">
            <v-col cols="5">             
                  <v-select
                  solo
                    style="width: 300px"
                    label="Brand"
                    v-model="fix.brandId"
                    :items="brand"
                    item-text="brandname"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                  ></v-select>
               </v-col>
            </v-layout>

            <v-layout  row  class="justify-center">
             <v-col cols="5">
               <v-text-field
               solo
               style="width: 300px"
               label="Queue"
               v-model="fix.queue"
               :items="queue"
               :rules="[(v) => !!v || 'กรุณากรอกคิวงาน']"
                required
               ></v-text-field>
               </v-col>
             </v-layout>

             <v-layout  row  class="justify-center">
      <v-row justify="center">
                          <v-col cols="5">
                            <v-textarea
                              solo
                              style="width: 300px"
                              v-model="fix.fixname"
                              
                              name="input-7-4"
                              label="กรุณากรอกปัญหาที่พบ.."
                              value=""
                            ></v-textarea>
                          </v-col>
                        </v-row>
        </v-layout>
               
      
             
              
              
              <v-layout  row  class="justify-center">
              
                <v-row justify="center">             
                 <v-layout  row nowrap class="justify-center">
              <v-btn  @click="saveFixs" x-large color="success" dark>SAVE</v-btn>&nbsp;  
              <v-btn  @click="clear" x-large color="blue-grey darken-2" dark>CLEAR</v-btn>
          </v-layout>
            </v-row>
           
            </v-layout>

            
      </v-form>   

    </v-container>
    <v-snackbar  v-model="snaktr" :timeout="10000">{{snactexttrue}}
        <v-btn text @click="snaktr = false" >CLOSE</v-btn>
      </v-snackbar>
      
    </div> 
    <div>
      <v-toolbar flat short color="#EEEEEE">
        <v-spacer></v-spacer>
        <span>COPYRIGHT © 2020 REPAIR COMPUTER. BY TEAM06 OF SOFTWARE ENGINEERING.</span>
      </v-toolbar>
    </div> 
  </v-app>
</template>


<script>
import http from "../http-common";
  export default {
    props: {
      source: String,
    },
    name: "fix",
   data() {
    return {
      fix: {      
        employeeId: "",
        customerId: "",
        fixtypeId: "",
        machinecolorId: "",
        brandId: "",
        queue: "",
        fixname: "",
        

        
      },
       snaktr: false,
        snactexttrue: ""
    };
  },
  methods:{
   /* eslint-disable */
  
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
        })
      },
      getCustomers(){
        http
        .get("/customer")
        .then(response =>{
          this.$forceUpdate();
          this.customer = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        })
      },
      getFixtypes(){
        http
        .get("/fixtype")
        .then(response =>{
          this.$forceUpdate();
          this.fixtype = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        })
      },
      getMachinecolors(){
        http
        .get("/machinecolor")
        .then(response =>{
          this.$forceUpdate();
          this.machinecolor = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        });
      },
      getBrands(){
        http
        .get("/brand")
        .then(response =>{
          this.$forceUpdate();
          this.brand = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        });
      },
          getFixs(){
        http
        .get("/fix")
        .then(response =>{
          this.$forceUpdate();
          this.fix = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        });
      },
      
    saveFixs() {
          http
        .post(
          // ("/addproduct/{productname}/{description}/{brand_id}/{part_id}/{branch_id}")
          "http://localhost:9000/fix/" +
            this.fix.employeeId +
            "/" +      
            this.fix.customerId +
            "/" +
            this.fix.fixtypeId +
            "/" +
            this.fix.machinecolorId +
            "/" +
            this.fix.brandId +
            "/" +
            this.fix.queue +
            "/" +
            this.fix.fixname ,

           
          this.fix
        )
         .then(response => {
                  console.log(response);
                  this.snaktr = true;
                  this.snactexttrue ="บันทึกข้อมูลเสร็จสิ้น"
                  this.$refs.form.reset();
                  this.$router.push("/fix");
                })
                .catch(e => {
                  console.log(e);
                  this.snaktr = true;
                  this.snactexttrue ="กรุณากรอกข้อมูลให้ครบถ้วน"
                });
        this.submitted = true;
      },
      clear() {
        this.$refs.form.reset();
        this.fixCheck = false;
      
    },
    refreshList() {      
      this.getEmployees();
      this.getCustomers();
      this.getFixtypes();
      this.getMachinecolors();
      this.getBrands();
      this.getFixs();
      
    },
    
  },
      mounted() {
      this.getEmployees();
      this.getCustomers();
      this.getFixtypes();
      this.getMachinecolors();
      this.getBrands();
      this.getFixs();
      
      }
  
};
</script>


<style>
.btncenter{
    
    display: block;
    margin-left: auto;
    margin-right: auto;
}
.fix{
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: 1075px;
    width: 800px; 
    background-color: #ebecf7;
    opacity: 5;
    -moz-box-shadow:inset 0 0 10px #000000;
   -webkit-box-shadow:inset 0 0 10px #000000;
   box-shadow:inset 0 0 10px #000000;
}

</style>




