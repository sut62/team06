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
        <br />
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
        { text: "Date", value: "receiptDate" },
        { text: "Type", value: "type.typename" },
        { text: "Part", value: "part.partname" },
        { text: "Part2", value: "partR2" },
        { text: "Part3", value: "partR3" },
        { text: "Part4", value: "partR4" },
        { text: "Part5", value: "partR5" },
        { text: "Customer", value: "customer.cusName" },
        { text: "Employee", value: "employee.empName" },
        { text: "Branch", value: "branch.branchname" },
        { text: "Price", value: "receiptPrice" }
        
      
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
