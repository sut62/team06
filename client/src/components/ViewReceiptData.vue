<template>
  <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }">
 
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

   
    <v-container nowrap  class="justify-center">
      <v-form v-model="valid" ref="form">
      <v-layout  row nowrap class="justify-center">

  <v-container >
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br /><br /> <br />        
        <h1 class="display-2 font-weight-bold mb-3">Receipt Information</h1>
      </v-flex>
    </v-layout>

    <div id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpeg') + ')' }">
            <v-container>
                <v-card  >
                    <v-card-title>
                        <v-text-field
                                v-model="search"
                                append-icon="search"
                                label="Search"
                                single-line
                                hide-details
                        ></v-text-field>
                    </v-card-title>
                </v-card>
                <br>
                <v-card >
                    <v-data-table 
                            :headers="headers"
                            :items="items"
                            :search="search" 
                            
                    >
                    </v-data-table>
                </v-card>
            </v-container>
        </div>

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
       drawer: false,
       search: '',
       
      headers: [
        { text: "วันที่", value: "receiptDate" },
        { text: "หมายเลขใบเสร็จ", value: "receiptNum" },
        { text: "หมายเลขงาน", value: "adminrepairindex.repairwork" },
        { text: "ประเภท", value: "type.typename" },
        { text: "ยี่ห้อ", value: "adminrepairindex.brand.brandname" },
        { text: "อาการเสีย", value: "adminrepairindex.breakdown" },
        { text: "รหัสประจำตัวลูกค้า", value: "adminrepairindex.customer.identification" },
        { text: "ชื่อลูกค้า", value: "adminrepairindex.customer.cusName" },
        { text: "รหัสประจำตัวผู้รับ", value: "receiptCusident" },
        { text: "ชื่อผู้รับ", value: "receiptCusname" },
        { text: "เบอร์โทรผู้รับ", value: "receiptCustel" },
        { text: "พนักงาน", value: "employee.empName" },
        { text: "สาขา", value: "branch.branchname" },
        { text: "ราคารวม", value: "adminrepairindex.totalrepairprice" },
        
        
      
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