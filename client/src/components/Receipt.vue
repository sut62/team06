<template>
    <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }">

     <v-app-bar app color="blue darken-2">
         <v-btn color="grey lighten-5">
          <router-link class="mr-1" to="/viewreceipt">View Receipt</router-link>
        </v-btn>  
          <v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/">Logout</router-link>
      </v-btn>
   </v-app-bar>
  



    <div class="bgn">
    <v-container nowrap  class="justify-center">
      <v-form v-model="valid" ref="form">
      <v-layout  row nowrap class="justify-center">
        
          <v-flex md12 xs12 lg12 xl3>
              <v-layout text-center wrap>
               <v-flex mb-4>
         <br />
              <h1 class="display-2 font-weight-bold mb-3">Repair Receipt</h1>
         </v-flex>
            </v-layout>
        </v-flex> 
         
         <div class="btncenter">
            <div class="justify-center">
              <v-flex md12 xs12 lg12 xl3>
                  <v-text-field
                      class="justify-center"
                      label="ค้นหาIDลูกค้า"
                      style="width:300px"
                      solo
                      v-model="receipt.customerIdent"
                  ></v-text-field>
              </v-flex>
            </div>

              
             <v-flex md12 xs12 lg12 xl3>
              <p align = 'center' v-if="customerCheck != ''" >Customer Name : {{customerName}}</p>
              </v-flex>
              
            
             <div class="btncenter">
              <v-flex md12 xs12 lg12 xl3>
                  <v-btn class="justify-center" @click="findCustomer" depressed large color="primary">Search</v-btn>
              </v-flex>
              <br>
              </div>

            
           
               <b-col md=6>
                  <v-select
                    label="Repair Type"
                    solo
                    v-model="receipt.typeId"
                    :items="type"
                    item-text="typename"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                  ></v-select>
               </b-col>
            

                 <v-flex md6 xs12 lg12 xl3>
                 <v-select
                 style="width: 300px"
                  label="Part1"
                  solo
                  v-model="receipt.partId"
                  :items="part"
                  item-text="partname"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                </v-flex>

                  
             <v-flex md12 xs12 lg12 xl3>
              <p align = 'center' v-if="pCheck != ''" >Part Price : {{pPrice}}</p>
              </v-flex>
              
            
             <div class="btncenter">
              <v-flex md12 xs12 lg12 xl3>
                  <v-btn class="justify-center" @click="findPrice" depressed large color="primary">ตรวจสอบราคา</v-btn>
              </v-flex>
              <br>
              </div>

                <v-flex md6 xs12 lg12 xl3>
                 <v-select
                 style="width: 300px"
                  label="Part2"
                  solo
                  v-model="receipt.partId2"
                  :items="part"
                  item-text="partname"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                </v-flex>

                  
             <v-flex md12 xs12 lg12 xl3>
              <p align = 'center' v-if="p2Check != ''" >Part Price : {{p2Price}}</p>
              </v-flex>
              
            
             <div class="btncenter">
              <v-flex md12 xs12 lg12 xl3>
                  <v-btn class="justify-center" @click="findPrice2" depressed large color="primary">ตรวจสอบราคา</v-btn>
              </v-flex>
              <br>
              </div>

                <v-flex md6 xs12 lg12 xl3>
                 <v-select
                 style="width: 300px"
                  label="Part3"
                  solo
                  v-model="receipt.partId3"
                  :items="part"
                  item-text="partname"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                </v-flex>

                 <v-flex md12 xs12 lg12 xl3>
              <p align = 'center' v-if="p3Check != ''" >Part Price : {{p3Price}}</p>
              </v-flex>
              
            
             <div class="btncenter">
              <v-flex md12 xs12 lg12 xl3>
                  <v-btn class="justify-center" @click="findPrice3" depressed large color="primary">ตรวจสอบราคา</v-btn>
              </v-flex>
              <br>
              </div>

                <v-flex md6 xs12 lg12 xl3>
                 <v-select
                 style="width: 300px"
                  label="Part4"
                  solo
                  v-model="receipt.partId4"
                  :items="part"
                  item-text="partname"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                </v-flex>

                 <v-flex md12 xs12 lg12 xl3>
              <p align = 'center' v-if="p4Check != ''" >Part Price : {{p4Price}}</p>
              </v-flex>
              
            
             <div class="btncenter">
              <v-flex md12 xs12 lg12 xl3>
                  <v-btn class="justify-center" @click="findPrice4" depressed large color="primary">ตรวจสอบราคา</v-btn>
              </v-flex>
              <br>
              </div>

                <v-flex md6 xs12 lg12 xl3>
                 <v-select
                 style="width: 300px"
                  label="Part5"
                  solo
                  v-model="receipt.partId5"
                  :items="part"
                  item-text="partname"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                </v-flex>

                 <v-flex md12 xs12 lg12 xl3>
              <p align = 'center' v-if="p5Check != ''" >Part Price : {{p5Price}}</p>
              </v-flex>
              
            
             <div class="btncenter">
              <v-flex md12 xs12 lg12 xl3>
                  <v-btn class="justify-center" @click="findPrice5" depressed large color="primary">ตรวจสอบราคา</v-btn>
              </v-flex>
              <br>
              </div>

            


       
               <v-flex md6 xs12 lg12 xl3>
                 <v-select
                  style="width: 300px"
                  label="Employee"
                  solo
                  v-model="receipt.employeeId"
                  :items="employee"
                  item-text="empName"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
               </v-flex>
          
                <v-flex md6 xs12 lg12 xl3>
                 <v-select
                 style="width: 300px"
                  label="Branch"
                  solo
                  v-model="receipt.branchId"
                  :items="branch"
                  item-text="branchname"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                </v-flex>

                 
               
               <v-col cols="17">
              <v-text-field
                solo
                label= "กรุณาระบุยอดเงิน"
                v-model="receipt.receiptPrice"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>

                <v-row justify="center">
              <v-col cols="12">  
                <v-btn @click="saveReceipts" :class="{ red: !valid, green: valid }">save</v-btn>
                <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
              </v-col>
            </v-row>
         </div>
              
             
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
    name: "receipt",
   data() {
    return {
      receipt: {
       
        customerIdent: "",
        typeId:"",
        employeeId: "",
        branchId: "",
        partId: "",
        partId2: "",
        partId3: "",
        partId4: "",
        partId5: "",

        
      },
      valid: false,
      customerCheck: false,
      customerName: "",
      pCheck: false,
      pPrice: "",
      p2Check: false,
      p2Price: "",
      p3Check: false,
      p3Price: "",
      p4Check: false,
      p4Price: "",
      p5Check: false,
      p5Price: "",
      drawer: false
    };
  },
  methods:{
   /* eslint-disable */
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
      getParts(){
        http
        .get("/part")
        .then(response =>{
          this.$forceUpdate();
          this.part = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        })
      },
      
     findCustomer() {
          http
            .get("/customer/"+ this.receipt.customerIdent)
            .then(response => {
              console.log(response);
              if (response.data.cusName != null) {
                this.customerName = response.data.cusName;
                this.customerCheck = response.status;
                alert('ค้นหาสำเร็จ')
                
              
              } else {
                alert('ไม่พบข้อมูล')
                this.clear()
              }          
            })
            .catch(e => {
              console.log(e);
            });
          this.submitted = true;
        },
        clear() {
      this.$refs.form.reset();
      this.customerCheck = false;
    },
    
    findPrice() {
          http
            .get("/part/"+ this.receipt.partId)         
            .then(response => {
              if (response.data.id != null) {
                this.pPrice = response.data.partprice;
                this.pCheck = response.status;
                console.log(response.data.partId);
                console.log(response.status);
                
              
              } else {
                alert('ไม่พบข้อมูล')
                this.clear()
              }          
            })
            .catch(e => {
              console.log(e);
            });
          this.submitted = true;
        },
        clear() {
      this.$refs.form.reset();
      this.pCheck = false;
    },
    findPrice2() {
          http
            .get("/part/"+ this.receipt.partId2)         
            .then(response => {
              if (response.data.id != null) {
                this.p2Price = response.data.partprice;
                this.p2Check = response.status;
                console.log(response.data.partId2);
                console.log(response.status);
                
              
              } else {
                alert('ไม่พบข้อมูล')
                this.clear()
              }          
            })
            .catch(e => {
              console.log(e);
            });
          this.submitted = true;
        },
        clear() {
      this.$refs.form.reset();
      this.p2Check = false;
    },
    findPrice3() {
          http
            .get("/part/"+ this.receipt.partId3)         
            .then(response => {
              if (response.data.id != null) {
                this.p3Price = response.data.partprice;
                this.p3Check = response.status;
                console.log(response.data.partId3);
                console.log(response.status);
                
              
              } else {
                alert('ไม่พบข้อมูล')
                this.clear()
              }          
            })
            .catch(e => {
              console.log(e);
            });
          this.submitted = true;
        },
        clear() {
      this.$refs.form.reset();
      this.p3Check = false;
    },
    findPrice4() {
          http
            .get("/part/"+ this.receipt.partId4)         
            .then(response => {
              if (response.data.id != null) {
                this.p4Price = response.data.partprice;
                this.p4Check = response.status;
                console.log(response.data.partId4);
                console.log(response.status);
                
              
              } else {
                alert('ไม่พบข้อมูล')
                this.clear()
              }          
            })
            .catch(e => {
              console.log(e);
            });
          this.submitted = true;
        },
        clear() {
      this.$refs.form.reset();
      this.p4Check = false;
    },
    findPrice5() {
          http
            .get("/part/"+ this.receipt.partId5)         
            .then(response => {
              if (response.data.id != null) {
                this.p5Price = response.data.partprice;
                this.p5Check = response.status;
                console.log(response.data.partId5);
                console.log(response.status);
                
              
              } else {
                alert('ไม่พบข้อมูล')
                this.clear()
              }          
            })
            .catch(e => {
              console.log(e);
            });
          this.submitted = true;
        },
        clear() {
      this.$refs.form.reset();
      this.p5Check = false;
    },


    saveReceipts() {
          http
        .post(
          // {identification}/{type_id}/{part_id}/{partR2}/{partR3}/{partR4}/{partR5}/{employee_id}/{branch_id}/{receiptPrice}
          "/receipt/" +
            this.receipt.customerIdent +
            "/" +
            this.receipt.typeId +
            "/" +
            this.receipt.partId +
            "/" +
            this.receipt.partId2 +
            "/" +
            this.receipt.partId3 +
            "/" +
            this.receipt.partId4 +
            "/" +
            this.receipt.partId5 +
            "/" +
            this.receipt.employeeId +
            "/" +
            this.receipt.branchId +
            "/" +
            this.receipt.receiptPrice ,
           
          this.receipt,
          console.log(this.receipt),
        )
       .then(response => {
          console.log(response);
          this.$router.push("/viewreceipt");
          alert('บันทึกข้อมูลเสร็จสิ้น')
        })
        .catch(e => {
          console.log(e);
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
      this.getParts();
      this.saveReceipts();
    },
    
  },
      mounted() {
      this.getTypes();
      this.getEmployees();
      this.getBranchs();
      this.getParts();
      this.saveReceipts();
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
    height: 1080px;
    width: 1000px; 
    background-color: #00B0FF;
    opacity: 0.9;
    -moz-box-shadow:inset 0 0 10px #000000;
   -webkit-box-shadow:inset 0 0 10px #000000;
   box-shadow:inset 0 0 10px #000000;
}
</style>