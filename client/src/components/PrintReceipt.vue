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
    <b-card id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }" >
    <b-row ref="form" nowrap  class="bg align-items-center d-flex justify-content-center"  align-v="center" align-h="center">
     
     
        

       <b-col md=4>
         <v-text-field
            solo
            label="หมายเลขใบเสร็จ - TCG0000000"
            v-model="receipt.receiptNum"
            :rules="[(v) => !!v || 'Item is required']"
            
          ></v-text-field></b-col>


       <b-col md=4>
         <v-btn class="ml-12 mb-7" @click="findReceipt" raised large color="primary">Show Receipt</v-btn>
         </b-col> 


          <b-col md=12 >
           <v-flex md12 xs12 lg12 xl3>
              <p class="font-weight-bold" align = 'center' v-if="receiptCheck != ''" >Receipt Number : {{receiptName}}</p>
              <p class="font-weight-bold" align = 'center' v-if="receiptCheck != ''" >ประเภท : {{typename}} , {{brandname}} , อาการเสีย : {{breakdown}}</p>
              <p class="font-weight-bold" align = 'center' v-if="receiptCheck != ''" >เลขประจำตัวชื่อลูกค้า : {{cusIdent}}</p>
              <p class="font-weight-bold" align = 'center' v-if="receiptCheck != ''" >ชื่อลูกค้า : {{cusName}}</p>
              <p class="font-weight-bold" align = 'center' v-if="receiptCheck != ''" >เลขประจำตัวผู้รับ : {{receiptCusident}}</p>
              <p class="font-weight-bold" align = 'center' v-if="receiptCheck != ''" >ชื่อผู้รับ : {{receiptCusname}}</p>
              <p class="font-weight-bold" align = 'center' v-if="receiptCheck != ''" >เบอร์โทรผู้รับ : {{receiptCustel}}</p>
              <p class="font-weight-bold" align = 'center' v-if="receiptCheck != ''" >พนักงานออกใบเสร็จ : {{empName}}</p>
              <p class="font-weight-bold" align = 'center' v-if="receiptCheck != ''" >สาขา : {{branchname}}</p>
              <p class="font-weight-bold" align = 'center' v-if="receiptCheck != ''" >ราคา : {{totalrepairprice}}</p>
               
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
    name: "receipt",
   data() {
    return {
      receipt: {
        receiptNum: "",
  
      },
        valid: false,
      receiptCheck: false,
      receiptName: "",
      receiptCusident: "",
      receiptCusname: "",
      receiptCustel: "",
      totalrepairprice: "",
      cusName: "",
      cusIdent: "",
      empName: "",
      branchname: "",
      breakdown: "",
      typename: "",
      brandname: "",
      snaktr: false,
      snactexttrue: "",
      drawer: false
    };
  },
  
  methods:{
    
     findReceipt() {
          http
            .get("/receipt/"+ this.receipt.receiptNum)
            .then(response => {
              console.log(response);
              if (response.data.receiptNum != null) {
                this.receiptName = response.data.receiptNum;
                this.receiptCusident = response.data.receiptCusident;
                this.receiptCusname = response.data.receiptCusname;
                this.receiptCustel = response.data.receiptCustel;
                this.totalrepairprice = response.data.adminrepairindex.totalrepairprice;
                this.cusName = response.data.adminrepairindex.customer.cusName;
                this.cusIdent = response.data.adminrepairindex.customer.identification;
                this.empName = response.data.employee.empName;
                this.branchname = response.data.branch.branchname;
                this.breakdown = response.data.adminrepairindex.breakdown;
                this.typename = response.data.adminrepairindex.type.typename;
                this.brandname = response.data.adminrepairindex.brand.brandname;
                this.receiptCheck = response.status;
                this.snaktr = true;
                this.snactexttrue =('ค้นหาสำเร็จ '+this.receipt.receiptNum )
               
                     
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
      this.receiptrCheck = false;
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