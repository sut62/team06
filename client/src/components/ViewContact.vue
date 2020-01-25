<template>
<v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/BGcontact.jpg') + ')' }">

        <nav>
            <v-toolbar class="bar">
                <v-app-bar-nav-icon @click.native.stop="drawer = !drawer"></v-app-bar-nav-icon>
                &nbsp; &nbsp;&nbsp; &nbsp;
                <v-toolbar-title dark>
                    <span> <font color="#FFFFFF"> CONTACT </font></span>
                    
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn flat color="#EDE7F6" to="/home">
                    <span>Sign Out</span>
                    <v-icon right>exit_to_app</v-icon>
                </v-btn>
            </v-toolbar>

            <v-navigation-drawer v-model="drawer" app
              absolute
              dark
              class="colornav"
              width="13%"
              height="100%"

              permanent     
              expand-on-hover
            >
                <v-list>
                    <v-list-item>
                        <v-list-item-content >MENU</v-list-item-content>
                        <v-icon left @click="drawer = !drawer">arrow_back</v-icon>
                    </v-list-item>
                    <v-list-item to="/contact">
                        <v-list-item-action>
                            <v-icon left>email</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>ติดต่อสอบถาม</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item to="/ViewContact">
                        <v-list-item-action>
                            <v-icon left>list</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>รายการคำถาม</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
        </nav>
 
 








  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3 color=white "><font color=ebecf7>ข้อมูลที่ติดต่อสอบถาม</font></h1>
      </v-flex>
    </v-layout>

    <v-row >
      <v-col cols="18" >
        <v-data-table :headers="headers"
                      :items="items" 
                      :items-per-page="10"
                       class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>

                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
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
  name: "ViewContact",
  data() {
    return {
      headers: [
        { text: "ชื่อ", value: "createdBy.cusName" },
        { text: "ช่องทางการติดต่อกลับ", value: "communication.communicationName" },
        { text: "เบอร์โทรศัพท์", value: "phone" },
        { text: "อีเมลล์", value: "email" },
        { text: "สาขาที่ต้องการติดต่อ", value: "branch.branchname" },
        { text: "เรื่อง", value: "heading.headingName" },
        { text: "รายละเอียด", value: "detail" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล Customer ใส่ combobox
    getContacts() {
      http
        .get("/contact")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getContacts();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getContacts();
  }
};
</script>






<style>
    .vbg{
        margin-left: auto;
        margin-right: auto;
        height: 800px;
        width: 1200px;
        opacity: 0.9;
    }

    .colornav{
  background: #9053c7;
  background: -webkit-linear-gradient(-135deg, #000031, rgb(6, 65, 173));
  background: -o-linear-gradient(-135deg, #000031,  rgb(6, 65, 173));
  background: -moz-linear-gradient(-135deg, #000031,  rgb(38, 105, 228));
  background: linear-gradient(-135deg, #000031,  rgb(38, 105, 228));
    }
    .bar{
    background: #9053c7;
  background: -webkit-linear-gradient(-135deg,rgb(38, 105, 228),#000031);
  background: -o-linear-gradient(-135deg,rgb(38, 105, 228),#000031);
  background: -moz-linear-gradient(-135deg,rgb(38, 105, 228),#000031);
  background: linear-gradient(-135deg,rgb(38, 105, 228),#000031);
    }


</style>

