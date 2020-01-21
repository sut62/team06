<template>
<v-app id="test" class="my-auto" :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }" >
      <div class="bn bg-dark">
                <v-app-bar icon="wrench" color="deep-purple accent-4" dense dark :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }"
                    >
                      

                      <v-toolbar-title icons="wrench" class="fond">Receipt System</v-toolbar-title>

                      <v-spacer></v-spacer>

                      <v-btn class="fond">
                           <router-link to="/viewreceipt">View Receipt</router-link>
                       </v-btn> 
                      <v-btn class="fond">
                          <router-link to="/home">Log out</router-link>
                      </v-btn>
                       

                  

                  
                    </v-app-bar>
        </div>

 <b-container class=" h-100 my-0 align-items-center d-flex justify-content-center" >   <!-- edit to mx-auto for biggerscreen -->
  <b-container wrap class=" h-100 my-0 align-items-center d-flex justify-content-center">
    
  <div class="ccc">
    <b-card dark nowrap fill-height class=" my-2 mx-0 bg-info align-items-stretch d-flex justify-content-center " align-v="center" align-h="center" >
    <b-row ref="form" nowrap  class="bg align-items-center d-flex justify-content-center"  align-v="center" align-h="center">
     
     
       <b-col md=4>
         <v-text-field
            solo
            label="ค้นหาIDลูกค้า"
            v-model="receipt.customerIdent"
            :rules="[(v) => !!v || 'Item is required']"
            
          ></v-text-field></b-col>


       <b-col md=4>
         <v-btn class="ml-12 mb-7" @click="findCustomer" raised large color="primary">SEARCH</v-btn>
         </b-col> 

          <b-col md=4>
           <v-flex md12 xs12 lg12 xl3>
              <p class="font-weight-bold" align = 'center' v-if="customerCheck != ''" >Customer Name : {{customerName}}</p>
              </v-flex>
      </b-col>
      

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



      <b-col md=4>
        <v-select
          label="อะไหล่ที่เปลี่ยนชิ้นที่1"
          v-model="receipt.proId"
          :items="addproduct"
          item-text="productname"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          solo
        ></v-select>
      </b-col>


       <b-col md=4>
         <v-btn class="ml-12 mb-7" @click="findPrice" depressed large color="primary">ตรวจสอบราคา</v-btn>
         </b-col> 

           <b-col md=4>
           <v-flex md12 xs12 lg12 xl3>
              <p class="font-weight-bold" align = 'center' v-if="pCheck != ''" >Part Price : {{pPrice}}</p>
              </v-flex>
             </b-col>



       <b-col md=4>
        <v-select
          label="อะไหล่ที่เปลี่ยนชิ้นที่2"
          v-model="receipt.proId2"
          :items="addproduct"
          item-text="productname"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          solo
        ></v-select>
      </b-col>


       <b-col md=4>
         <v-btn class="ml-12 mb-7" @click="findPrice2" depressed large color="primary">ตรวจสอบราคา</v-btn>
         </b-col> 

          <b-col md=4>
      <v-flex md12 xs12 lg12 xl3>
              <p class="font-weight-bold" align = 'center' v-if="p2Check != ''" >Part Price : {{p2Price}}</p>
              </v-flex>
      </b-col>


        <b-col md=4>
        <v-select
          label="อะไหล่ที่เปลี่ยนชิ้นที่3"
          v-model="receipt.proId3"
          :items="addproduct"
          item-text="productname"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          solo
        ></v-select>
      </b-col>


       <b-col md=4>
         <v-btn class="ml-12 mb-7" @click="findPrice3" depressed large color="primary">ตรวจสอบราคา</v-btn>
         </b-col> 

          <b-col md=4>
       <v-flex md12 xs12 lg12 xl3>
              <p class="font-weight-bold" align = 'center' v-if="p3Check != ''" >Part Price : {{p3Price}}</p>
              </v-flex>
        </b-col>


        <b-col md=4>
        <v-select
          label="อะไหล่ที่เปลี่ยนชิ้นที่4"
          v-model="receipt.proId4"
          :items="addproduct"
          item-text="productname"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          solo
        ></v-select>
      </b-col>


       <b-col md=4>
         <v-btn class="ml-12 mb-7" @click="findPrice4" depressed large color="primary">ตรวจสอบราคา</v-btn>
         </b-col> 

           <b-col md=4>
      <v-flex md12 xs12 lg12 xl3>
              <p class="font-weight-bold" align = 'center' v-if="p4Check != ''" >Part Price : {{p4Price}}</p>
              </v-flex>
      </b-col>


        <b-col md=4>
        <v-select
          label="อะไหล่ที่เปลี่ยนชิ้นที่5"
          v-model="receipt.proId5"
          :items="addproduct"
          item-text="productname"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          solo
        ></v-select>
      </b-col>



       <b-col md=4>
         <v-btn class="ml-12 mb-7" @click="findPrice5" depressed large color="primary">ตรวจสอบราคา</v-btn>
         </b-col> 

         <b-col md=4>
         <v-flex md12 xs12 lg12 xl3>
              <p class="font-weight-bold" align = 'center' v-if="p5Check != ''" >Part Price : {{p5Price}}</p>
              </v-flex>
        </b-col>

       
         <b-col md=4>
         <v-flex md12 xs12 lg12 xl3>
              <p class="font-weight-bold" align = 'center' v-if="p4Check != ''" >Total Price : {{result}}</p>
              </v-flex>
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
        customerIdent: "",
        typeId: "",
        proId:"",
        proId2:"",
        proId3:"",
        proId4:"",
        proId5:"",
        employeeId:"",
        branchId: "",
        receiptPrice: "",
  
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
  computed: {
    result(){
      return parseInt(this.pPrice)+parseInt(this.p2Price)+parseInt(this.p3Price)+parseInt(this.p4Price)+parseInt(this.p5Price)
    }
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
    getProducts(){
        http
        .get("/addproduct")
        .then(response =>{
          this.$forceUpdate();
          this.addproduct = response.data;
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
                this.snaktr = true;
                 this.snactexttrue =('ค้นหาสำเร็จ'+this.receipt.customerIdent )
               
                
              
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
      this.customerCheck = false;
    },

     findPrice() {
          http
            .get("/addproduct/"+ this.receipt.proId)         
            .then(response => {
              if (response.data.id != null) {
                this.pPrice = response.data.price;
                this.pCheck = response.status;
                console.log(response.data.proId);
                console.log(response.status);
                
              
              } else {
                 this.snaktr = true;
                 this.snactexttrue =('ไม่พบข้อมูล') 
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
            .get("/addproduct/"+ this.receipt.proId2)         
            .then(response => {
              if (response.data.id != null) {
                this.p2Price = response.data.price;
                this.p2Check = response.status;
                console.log(response.data.proId2);
                console.log(response.status);
                
              
              } else {
                this.snaktr = true;
                this.snactexttrue =('ไม่พบข้อมูล') 
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
            .get("/addproduct/"+ this.receipt.proId3)         
            .then(response => {
              if (response.data.id != null) {
                this.p3Price = response.data.price;
                this.p3Check = response.status;
                console.log(response.data.proId3);
                console.log(response.status);
                
              
              } else {
                this.snaktr = true;
                this.snactexttrue =('ไม่พบข้อมูล')
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
            .get("/addproduct/"+ this.receipt.proId4)         
            .then(response => {
              if (response.data.id != null) {
                this.p4Price = response.data.price;
                this.p4Check = response.status;
                console.log(response.data.proId4);
                console.log(response.status);
                
              
              } else {
                this.snaktr = true;
                this.snactexttrue =('ไม่พบข้อมูล')
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
            .get("/addproduct/"+ this.receipt.proId5)         
            .then(response => {
              if (response.data.id != null) {
                this.p5Price = response.data.price;
                this.p5Check = response.status;
                console.log(response.data.proId5);
                console.log(response.status);
                console.log(this.result)
              
              } else {
                this.snaktr = true;
                this.snactexttrue =('ไม่พบข้อมูล')
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
        // /receipt/{identification}/{type_id}/{pro_id}/{proeR2}/{proR3}/{proR4}/{proR5}/{employee_id}/{branch_id}/{receiptPrice}")
          "/receipt/" +
            this.receipt.customerIdent +
            "/" +
            this.receipt.typeId +
            "/" +
            this.receipt.proId +
            "/" +
            this.receipt.proId2 +
            "/" +
            this.receipt.proId3 +
            "/" +
            this.receipt.proId4 +
            "/" +
            this.receipt.proId5 +
            "/" +
            this.receipt.employeeId +
            "/" +
            this.receipt.branchId +
            "/" +
            this.result ,
           
          this.receipt,
          console.log(this.receipt),
           
        )
       .then(response => {
          console.log(response);
          this.$router.push("/viewreceipt");
          this.snaktr = true;
          this.snactexttrue =('บันทึกข้อมูลเสร็จสิ้น')
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
      this.getProducts();
      this.saveReceipts();
    },
    
  },
      mounted() {
      this.getTypes();
      this.getEmployees();
      this.getBranchs();
      this.getProducts();
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