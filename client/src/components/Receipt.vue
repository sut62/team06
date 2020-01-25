<template>
<v-app id="test" class="my-auto" :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }" >
       <nav>
            <v-toolbar  color="#42A5F5" dark>
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
     color="#42A5F5"
     height="100%"
      width="15%"
             permanent
              expand-on-hover
             >
              <v-list>
                    <v-list-item>
                        <v-list-item-content >Receipt MENU</v-list-item-content>
                        <v-icon left @click="drawer = !drawer">arrow_back</v-icon>
                    </v-list-item>
                    <v-list-item to="/receipt">
                        <v-list-item-action>
                            <v-icon left>account_box</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>Receipt</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                      <v-list-item to="/viewrepairindex">
                        <v-list-item-action>
                            <v-icon left>assessment</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>View Adminrepair</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item to="/viewreceipt">
                        <v-list-item-action>
                            <v-icon left>assessment</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>View Receipt</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item to="/printreceipt">
                        <v-list-item-action>
                            <v-icon left>assessment</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>Print Receipt</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
        </nav>


 <b-container class=" h-100 my-0 align-items-center d-flex justify-content-center" >   <!-- edit to mx-auto for biggerscreen -->
  <b-container wrap class=" h-100 my-0 align-items-center d-flex justify-content-center">
    
  <div class="ccc">
    <b-card id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }">
    <b-row ref="form" nowrap  class="bg align-items-center d-flex justify-content-center"  align-v="center" align-h="center">
     
     
         <v-col cols="9">
              <v-text-field
                      outlined
                       solo
                      label="หมายเลขใบเสร็จ - TCG0000000"
                      v-model="receipt.receiptNum"
                      :rules="[(v) => !!v || 'Item is required']"
                      required
              ></v-text-field>
            </v-col>



             <b-col md=4>
         <v-text-field
            solo
            label="หมายเลขการซ่อม"
            v-model="receipt.repairwork"
            :rules="[(v) => !!v || 'Item is required']"
            
          ></v-text-field></b-col>


       <b-col md=4>
         <v-btn class="ml-12 mb-7" @click="findRepairwork" raised large color="primary">SEARCH</v-btn>
         </b-col> 


          <b-col md=4>
           <v-flex md12 xs12 lg12 xl3>
               <p class="grey lighten-5 font-weight-bold" align = 'center' v-if="adminrepairCheck != ''" >Customer Name : {{cusName}}</p>
               <p class="grey lighten-5 font-weight-bold" align = 'center' v-if="adminrepairCheck != ''" >Customer ID : {{cusIdent}}</p>
              </v-flex>
      </b-col>
     
            <v-col cols="4">
              <v-text-field
                      outlined
                       solo
                      type="number"
                      label="เลขประจำตัวผู้รับ 13 หลัก"
                      v-model="receipt.receiptCusident"
                      :rules="[(v) => !!v || 'Item is required']"
                      required
              ></v-text-field>
            </v-col>
      
            <v-col cols="4">
              <v-text-field
                      outlined
                       solo
                      label="ชื่อผู้รับ"
                      v-model="receipt.receiptCusname"
                      :rules="[(v) => !!v || 'Item is required']"
                      required
              ></v-text-field>
            </v-col>

            
            <v-col cols="4">
              <v-text-field
                      outlined
                       solo
                      type="number"
                      label="เบอร์โทรผู้รับ"
                      v-model="receipt.receiptCustel"
                      :rules="[(v) => !!v || 'Item is required']"
                      required
              ></v-text-field>
            </v-col>
        
      
      

       <b-col md=4>
        <v-select
          label="ประเภทอุปกรณ์"
          v-model="receipt.typeId"
          :items="type"
          item-text="typename"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          solo
        ></v-select>
      </b-col>

      <b-col md=4>
        <v-select
          label="พนักงาน"
          v-model="receipt.employeeId"
          :items="employee"
          item-text="empName"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          solo
        ></v-select>
      </b-col>

      <b-col md=4>
        <v-select
          label="สาขา"
          v-model="receipt.branchId"
          :items="branch"
          item-text="branchname"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          solo
        ></v-select>
      </b-col>

        

    </b-row>

    <b-col md=12 class="text-center"> 
               <v-btn @click="saveReceipts" depressed large color="green darken-2">save</v-btn>
      </b-col>
      
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
    name: "receipt",
   data() {
    return {
      receipt: {
        receiptNum: "",
        receiptCusname: "",
        receiptCusident: "",
        receiptCustel: "",
        repairwork: "",
        typeId: "",
        employeeId:"",
        branchId: "",
  
      },
        valid: false,
      adminrepairCheck: "",
      cusName: "",
      cusIdent: "",
      snaktr: false,
      snactexttrue: "",
      drawer: false
    };
  },
  

  methods:{
    getTypes(){
        http
        .get("/type")
        .then(response =>{
          this.$forceUpdate();
          this.type = response.data;
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
    getBranchs(){
        http
        .get("/branch")
        .then(response =>{
          this.branch = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        })
      },
    
    
     
      findRepairwork() {
          http
            .get("/Adminrepairindex/"+ this.receipt.repairwork)
            .then(response => {
              console.log(response);
              if (response.data.repairwork != null) {
                this.cusName = response.data.customer.cusName;
                this.cusIdent = response.data.customer.identification
                this.adminrepairCheck = response.status;
                this.snaktr = true;
                this.snactexttrue =('ค้นหาสำเร็จ '+this.receipt.repairwork )
               
                     
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
      this.adminrepairCheck = false;
    },

    
    
     saveReceipts(){
          http
        .post(
        // ("/receipt/{receiptNum}/{adminrepairNum}/{receiptCusident}/{receiptCusname}/{receiptCustel}/{type_id}/{employee_id}/{branch_id}")
          "/receipt/" +
            this.receipt.receiptNum +
            "/" +
            this.receipt.repairwork +
            "/" +
            this.receipt.receiptCusident +
            "/" +
            this.receipt.receiptCusname +
            "/" +
            this.receipt.receiptCustel +
            "/" +
            this.receipt.typeId +
            "/" +
            this.receipt.employeeId +
            "/" +
            this.receipt.branchId,
          
           
            this.receipt,
          console.log(this.receipt),
           
        )
       .then(response => {
          console.log(response);
          this.snaktr = true;
          this.snactexttrue =('บันทึกข้อมูลเสร็จสิ้น')

        
        })
        
        .catch(e => {
          console.log(e);
          if(e = true){
           this.snaktr = true;
           this.snactexttrue ="กรุณาใส่ข้อมูลให้ถูกต้อง"
          }
        });
      this.submitted = true;
     
    },
    clear() {
      this.$refs.form.reset();
      this.customerCheck = false;
    },

 
    refreshList() {
      this.getTypes();
      this.getEmployees();
      this.getBranchs();
      this.saveReceipts();
    },
    
  },
      mounted() {
      this.getTypes();
      this.getEmployees();
      this.getBranchs();
      this.saveReceipts();
      }
  
};
</script>

<style>

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