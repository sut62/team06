<template>
  <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/a.jpeg') + ')' }">
  <v-app-bar app color="blue darken-2">
      <v-toolbar-title class="headline text-uppercase">
        <span>ระบบบันทึกการแจ้งซ่อมคอมพิวเตอร์</span>     
     </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/home">LOGOUT</router-link>
      </v-btn>
    </v-app-bar>

    <div class='bgn'>                   
    <br>
    <div class="text-center">
      <br>
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
          <v-text-field
            solo
            label="กรอกปัญหาที่พบ"
            v-model="fix.fixname"
            :items="fixname"
            :rules="[(v) => !!v || 'กรอกรายละเอียดเกี่ยวกับปัญหา']"
            required
          ></v-text-field>
        </v-col>
        </v-layout>

        

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
            <v-col cols="2">   
                <v-row justify="center">
              <v-col cols="12">  
                <v-btn @click="saveFixs" :class="{ red: !valid, green: valid }">save</v-btn>
                
              </v-col>
            </v-row>
            </v-col>
            </v-layout>

            
      </v-form>   
    </v-container>
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
       
        fixname: "",
        employeeId: "",
        customerId: "",
        fixtypeId: "",
        

        
      },
      valid: false,
      drawer: false
    };
  },
  methods:{
   /* eslint-disable */
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
      
      
      
    saveFixs() {
          http
        .post(
          // ("/addproduct/{productname}/{description}/{brand_id}/{part_id}/{branch_id}")
          "http://localhost:9000/fix/" +
            this.fix.fixname +
            "/" +
            this.fix.employeeId +
            "/" +
            this.fix.customerId +
            "/" +
            this.fix.fixtypeId +
            
           
          this.fix
        )
        .then(response => {
          console.log(response);
          if(response = true){
            alert('บันทึกข้อมูลเสร็จสิ้น')
          } 
          this.$refs.form.reset();
          
        })
        .catch(e => {
          console.log(e);
           if(e = true){
            alert('กรุณากรอกข้อมูลให้ครบถ้วน')

            
            
          }
        });
      this.submitted = true;
    },
    refreshList() {
      this.getFixs();
      this.getEmployees();
      this.getCustomers();
      this.getFixtypes();
      
    },
    
  },
      mounted() {
      this.getFixs();
      this.getEmployees();
      this.getCustomers();
      this.getFixtypes();
      
      }
  
};
</script>


<style>
.btncenter{
    
    display: block;
    margin-left: auto;
    margin-right: auto;
}
.bgn{
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: 300px;
    width: 400px; 
    background-color: #ebecf7;
    opacity: 5;
    -moz-box-shadow:inset 0 0 10px #000000;
   -webkit-box-shadow:inset 0 0 10px #000000;
   box-shadow:inset 0 0 10px #000000;
}

</style>




