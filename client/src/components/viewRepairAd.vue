<template>
    <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/b.jpg') + ')' }" >
        <nav>
            <v-toolbar color="#00BCD4" dark>
                <v-app-bar-nav-icon @click.native.stop="drawer = !drawer"></v-app-bar-nav-icon>
                &nbsp; &nbsp;&nbsp; &nbsp;
                <v-toolbar-title>
                    <span> Adminrepair</span>
                    
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn flat color="grey" to="/home">
                    <span>Sign Out </span>
                    <v-icon right>exit_to_app</v-icon>
                </v-btn>
            </v-toolbar>

            <v-navigation-drawer v-model="drawer" app height="100%"
              absolute
      dark
    color="#00BCD4"
      width="15%"
             permanent
              expand-on-hover
             >
                <v-list>
                    <v-list-item>
                        <v-list-item-content >adminrepair MENU</v-list-item-content>
                        <v-icon left @click="drawer = !drawer">arrow_back</v-icon>
                    </v-list-item>
                    <v-list-item to="/repairindex">
                        <v-list-item-action>
                            <v-icon left>account_box</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>adminrepair</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item to="/viewrepairindex">
                        <v-list-item-action>
                            <v-icon left>assessment</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>View Addproducts</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
        </nav>
        <div class="addpro">
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
                <v-card color="#7C4DFF">
                    <v-data-table
                            :headers="headers"
                            :items="items"
                            :search="search"
                            
                    >
                    </v-data-table>
                </v-card>
            </v-container>
        </div><div>
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
        name: "viewrepairindex",
        data() {
            return {
                drawer: false,
                search: '',
           headers: [
        { text: "รหัสงาน", value: "repairwork" },
        { text: "สาขา", value: "branch.branchname" },
        { text: "พนักงาน", value: "createdBy.empName" },
        { text: "อาการเสีย", value: "breakdown" },
        { text: "ประเภทเครื่อง", value: "type.typename" },
        { text: "ยี่ห้อ", value: "brand.brandname" },
        { text: "สถานะ", value: "statusrepair.currentstatus" },
        {text: "ราคาทั้งหมด", value: "totalrepairprice" },
       
      
      ],
        items : [],
            };
        },
        methods: {
            /* eslint-disable no-console */
            // ดึงข้อมูล Addproduct ใส่ combobox
            getAdminrep() {
                http
                    .get("/Adminrepairindex")
                    .then(response => {
                        this.items = response.data;
                        console.log(this.items);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            refreshList() {
                this.getAdminrep();
            }
            /* eslint-disable no-console */
        },
        mounted() {
            this.getAdminrep();
        }
    };
</script>


<style>
    .addpro{
        display: block;
        margin-left: auto;
        margin-right: auto;
        height: 950px;
        width: 1000px;
        background-color: #0000;
        opacity: 0.9;
    }
</style>