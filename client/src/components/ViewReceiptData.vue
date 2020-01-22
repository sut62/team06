<template>
  <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }">
 

                <v-app-bar app color="blue darken-2">
                     <v-spacer></v-spacer>
                      <v-btn class="fond">
                           <router-link to="/receipt">Receipt</router-link>
                       </v-btn> 
                      <v-btn class="fond">
                          <router-link to="/home">Log out</router-link>
                      </v-btn>
                    </v-app-bar>
  
   
    <v-container nowrap  class="justify-center">
      <v-form v-model="valid" ref="form">
      <v-layout  row nowrap class="justify-center">

  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br /><br /> <br />        
        <h1 class="display-2 font-weight-bold mb-3">Receipt Information</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>

  </v-container>
           
      </v-layout>
     </v-form>
           
           
    </v-container>
    
  </v-app>
</template>

<script>
import http from "../http-common";

export default {
  name: "ViewReceiptData",
  data() {
    return {
      headers: [
        { text: "วันที่", value: "receiptDate" },
        { text: "ประเภท", value: "type.typename" },
        { text: "Product", value: "addproduct.productname" },
        { text: "product2", value: "proR2" },
        { text: "Product3", value: "proR3" },
        { text: "Product4", value: "proR4" },
        { text: "Product5", value: "proR5" },
        { text: "รหัสประจำตัวลูกค้า", value: "customer.identification" },
        { text: "ชื่อลูกค้า", value: "customer.cusName" },
        { text: "รหัสประจำตัวผู้รับ", value: "receiptCusident" },
        { text: "ชื่อผู้รับ", value: "receiptCusname" },
        { text: "เบอร์โทรผู้รับ", value: "receiptCustel" },
        { text: "พนักงาน", value: "employee.empName" },
        { text: "สาขา", value: "branch.branchname" },
        { text: "ราคา", value: "receiptPrice" }
        
      
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล receipt ใส่ combobox
    getReceipts() {
      http
        .get("/receipt")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getReceipts();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getReceipts();
  }
};
</script>
