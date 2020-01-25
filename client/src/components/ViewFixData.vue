<template>
    <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/a.jpeg') + ')' }">
        <nav>
            
            <v-toolbar class="colorna">      
            </v-toolbar>
            <v-navigation-drawer v-model="drawer" app
              absolute
      dark
      src="https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg"
      width="15%"
             permanent
              expand-on-hover
             >
      <br>  
      <v-layout  row  class="justify-center">
            <v-col cols="10">  
                <v-toolbar-title>
                    <span>Repair Computer</span>
                </v-toolbar-title>
  <v-avatar>
      <img  class="justify-center" 
        src="https://lh3.googleusercontent.com/a-/AAuE7mDw30XiGeKEThWib0jmGWIJ9HioyGgm9Gni8LX2BQ=s96-cc-rg"
       
      >
    </v-avatar>
    <v-list-item-content>
              <v-list-item-title class="title">Woramet Meksuwan</v-list-item-title>
              <v-list-item-subtitle>Computer Engineer</v-list-item-subtitle>
            </v-list-item-content>
          </v-col>
            </v-layout>
    <v-divider></v-divider>

                  <v-list >
                    <v-list-item>
                        <v-list-item-content>FIX MENU</v-list-item-content>
                    </v-list-item>
                    <v-list-item to="/fix">
                        <v-list-item-action>
                            <v-icon left>home</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>Add Fix</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>

                    <v-list-item to="/viewfix">
                        <v-list-item-action>
                            <v-icon left>dashboard</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>View Fix</v-list-item-title>
                        </v-list-item-content>
               
                    </v-list-item>
                </v-list>
       <template v-slot:append>
        <div class="pa-2">
          <v-btn block color="red darken-1" to="/home">Logout</v-btn>
        </div>
      </template>
            </v-navigation-drawer>
        </nav>
            <div class="fixdata">
            <v-container>
                <v-card>
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
                <v-card color="#7C4DFF" >
                    
                    <v-data-table
                            :headers="headers"
                            :items="items"
                            :search="search"
                    >  
                   
                    </v-data-table>   
                </v-card>
                <br>
            </v-container>
        </div>
    
        <div class='Pop'>
      <v-toolbar flat short color="#EEEEEE">
        <v-spacer></v-spacer>
        <span>COPYRIGHT © 2020 REPAIR COMPUTER. BY TEAM06 OF SOFTWARE ENGINEERING.</span>
      </v-toolbar>
    </div>

    </v-app>
</template>

<script>
 /* eslint-disable */
import http from "../http-common";
import Axios from 'axios';
export default {
  name: "ViewFixData",
  data() {
    return {
       
      drawer: true,
      search: '',
      
      headers: [
          { text: "Queue", value: "queue" },
        { text: "พนักงาน", value: "employee.empName" },
        { text: "ลูกค้า", value: "customer.cusName" },
        { text: "Machinecolor", value: "machinecolor.machinecolor" },
        { text: "ประเภทอุปกรณ์ที่นำมาซ่อม", value: "fixtype.fixtypename" },
        { text: "Brand", value: "brand.brandname" },
        { text: "ปัญหา", value: "fixname" }
        
        ],
       items: []
         };
        },
        methods: {
            /* eslint-disable no-console */
            // ดึงข้อมูล Customer ใส่ combobox
            getFixs() {
                http
                    .get("/fix")
                    .then(response => {
                        this.items = response.data;
                        console.log(this.items);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            refreshList() {
                this.getFixs();
            }
            /* eslint-disable no-console */
        },
        mounted() {
            this.getFixs();
            
        }
    };
</script>

<style>
.btncenters{
    
    display: block;
    margin-left: auto;
    margin-right: auto;
    background-color: #ebecf7;
}
.fixdata{
    display: block;
    margin-left: auto;
    margin-right: auto;
    height:  865px;
    width: 1200px; 
    background-color: #ebecf7;
    opacity: 5;
    -moz-box-shadow:inset 0 0 10px #000000;
   -webkit-box-shadow:inset 0 0 10px #000000;
   box-shadow:inset 0 0 10px #000000;
   
}

</style>

<style>.vbgs{
        margin-left: auto;
        margin-right: auto;
        height: 1075px;
        width: 800px; 
        opacity: 0.9;
    }
    .colorna{
  background: #792dbb;
  background: -webkit-linear-gradient(-135deg, #010155, rgb(38, 105, 228));
  background: -o-linear-gradient(-135deg, #020261,  rgb(38, 105, 228));
  background: -moz-linear-gradient(-135deg, #010169,  rgb(38, 105, 228));
  background: linear-gradient(-135deg, #020277,  rgb(38, 105, 228));
    }
</style>

<style>.Pop{
        margin-left: auto;
        margin-right: auto;
        width: 2040px; 
        opacity: 1;
    }
    .colorna{
  background: #792dbb;
  background: -webkit-linear-gradient(-135deg, #010155, rgb(38, 105, 228));
  background: -o-linear-gradient(-135deg, #020261,  rgb(38, 105, 228));
  background: -moz-linear-gradient(-135deg, #010169,  rgb(38, 105, 228));
  background: linear-gradient(-135deg, #020277,  rgb(38, 105, 228));
    }
</style>