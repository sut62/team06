<template>
  <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/addpro.jpeg') + ')' }">
  <v-app-bar app color="#C62828">
      <v-toolbar-title class="headline text-uppercase">
        <span >VIEW</span>
        <span class="font-weight-light"> PRODUCT</span>
      </v-toolbar-title>
      
      <v-spacer></v-spacer>
      <v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/addproduct">ADD PRODUCT </router-link>
      </v-btn>
      <v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/viewAddproduct">INFORMATION</router-link>
      </v-btn>
      <v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/home">LOGOUT</router-link>
      </v-btn>
    </v-app-bar>
                     
    <br>
    <div class="text-center">
    <br>
<br>
<br>
    <v-avatar>
      <img src="https://image.flaticon.com/icons/png/512/187/187503.png" alt="avatar">
    </v-avatar>
  </div>
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
                <v-data-table
                        :headers="headers"
                        :items="items"
                        :search="search"
                ></v-data-table>
            </v-card>
        </v-container>
        
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


