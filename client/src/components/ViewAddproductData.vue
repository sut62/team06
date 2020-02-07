<template>
    <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/addpro.jpeg') + ')' }">
        <nav>
            <v-toolbar color="#C62828" dark>
                <v-app-bar-nav-icon @click.native.stop="drawer = !drawer"></v-app-bar-nav-icon>
                &nbsp; &nbsp;&nbsp; &nbsp;
                <v-toolbar-title>
                    <span> Repair</span>
                    <span>Computer</span>
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn flat color="primary" to="/home">
                    <span>Sign Out </span>
                    <v-icon right>exit_to_app</v-icon>
                </v-btn>
            </v-toolbar>

            <v-navigation-drawer v-model="drawer" app
              absolute
      dark
      src="https://wallpaperplay.com/walls/full/0/3/d/163882.jpg"
      width="15%"
             permanent
              expand-on-hover
             >
                <v-list>
                    <v-list-item>
                        <v-list-item-content >ADDPRODUCT MENU</v-list-item-content>
                        <v-icon left @click="drawer = !drawer">arrow_back</v-icon>
                    </v-list-item>
                    <v-list-item to="/addproduct">
                        <v-list-item-action>
                            <v-icon left>account_box</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>Add Product</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item to="/searchAddproduct">
                        <v-list-item-action>
                            <v-icon left>search</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>Search Product</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item to="/viewAddproduct">
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
        name: "ViewAddproduct",
        data() {
            return {
                drawer: false,
                search: '',
                headers: [
                    { text: 'Product ID', value: 'proid' },
                    { text: 'Product Name', value: 'productname' },
                    { text: 'Description', value: 'description' },
                    { text: 'Type', value: 'type.typename' },
                    { text: 'Brand', value: 'brand.brandname' },
                    { text: 'Part', value: 'part.partname' },
                    { text: 'Branch', value: 'branch.branchname' },
                    { text: 'Price', value: 'price' },
                    
                ],
                items: []
            };
        },
        methods: {
            /* eslint-disable no-console */
            // ดึงข้อมูล Addproduct ใส่ combobox
            getAddproducts() {
                http
                    .get("/addproduct")
                    .then(response => {
                        this.items = response.data;
                        console.log(this.items);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            refreshList() {
                this.getAddproducts();
            }
            /* eslint-disable no-console */
        },
        mounted() {
            this.getAddproducts();
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