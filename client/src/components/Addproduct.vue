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
                <v-btn flat color='primary' to="/home">
                    <span>Sign Out </span>
                    <v-icon right>exit_to_app</v-icon>
                </v-btn>
            </v-toolbar>

            <v-navigation-drawer v-model="drawer" app
              absolute
      dark
      src="https://wallpaperplay.com/walls/full/0/3/d/163882.jpg"
      
      width="15%"
      height="100%"
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
        
    <br><br>
    <div class="text-center">
    <v-avatar>
      <img src="http://3.bp.blogspot.com/-eAUofSpwfbI/VXKEcXJGFdI/AAAAAAAAAAg/bgN6NFGW1s8/s1600/256-256-251c205de6105b216e43f280285d959d.png" alt="avatar">
    </v-avatar>
  </div>
    
      <h1 align='center' class="display-1 font-weight-bold mb-2">เพิ่มอะไหล่คอมพิวเตอร์</h1>
    
    <v-container nowrap  class="justify-center">
      <v-form v-model="valid" ref="form">

      <v-layout  row  class="justify-center">
      <v-col cols="5">
          <v-text-field
            solo
            label="ProductID"
            v-model="addproduct.proid"
            :items="proid"
            :rules="[(v) => !!v || 'ProductID']"
            required
          ></v-text-field>
        </v-col>
        </v-layout>
      
      <v-layout  row  class="justify-center">
      <v-col cols="5">
          <v-text-field
            solo
            label="Product"
            v-model="addproduct.productname"
            :items="productname"
            :rules="[(v) => !!v || 'Product']"
            required
          ></v-text-field>
        </v-col>
        </v-layout>

        <v-layout  row  class="justify-center">
      <v-col cols="5">
          <v-text-field
            solo
            label="Description"
            v-model="addproduct.description"
            :items="description"
            :rules="[(v) => !!v || 'Description']"
            required
          ></v-text-field>
        </v-col>
        </v-layout>

            <v-layout  row  class="justify-center">
            <v-col cols="5">             
                  <v-select
                  solo
                    style="width: 300px"
                    label="Type"
                    v-model="addproduct.typeId"
                    :items="type"
                    item-text="typename"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                  ></v-select>
               </v-col>
            </v-layout>

           <v-layout  row  class="justify-center">
            <v-col cols="5">             
                  <v-select
                  solo
                    style="width: 300px"
                    label="Brand"
                    v-model="addproduct.brandId"
                    :items="brand"
                    item-text="brandname"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                  ></v-select>
               </v-col>
            </v-layout>
      
            <v-layout  row  class="justify-center">
            <v-col cols="5">               
                  <v-select
                  solo
                    style="width: 300px"
                    label="Part"
                    v-model="addproduct.partId"
                    :items="part"
                    item-text="partname"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                  ></v-select>
            </v-col>
            </v-layout>
               
      
             <v-layout  row  class="justify-center">
            <v-col cols="5">    
                  <v-select
                  solo
                    style="width: 300px"
                    label="Branch"
                    v-model="addproduct.branchId"
                    :items="branch"
                    item-text="branchname"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                  ></v-select>
            </v-col>
            </v-layout>

            <v-layout  row  class="justify-center">
             <v-col cols="5">
            <v-text-field
            solo
            label="Price"
            v-model="addproduct.price"
            :items="price"
            :rules="[(v) => !!v || 'Price']"
            required
            ></v-text-field>
              </v-col>
           </v-layout>
            
              
              
             <v-layout  row nowrap class="justify-center">
             <v-btn @click="saveAddproducts" :class="{ red: !valid, green: valid }">save</v-btn> &nbsp;
             <v-btn @click="clear" :class="{ white: !valid, white: valid }">clear</v-btn>
          </v-layout>
            
      </v-form>   
    </v-container>
    <v-snackbar  v-model="snaktr" :timeout="10000">{{snactexttrue}}
        <v-btn text @click="snaktr = false" >CLOSE</v-btn>
      </v-snackbar>
    </div>  
  </v-app>
</template>


<script>
import http from "../http-common";
  export default {
    props: {
      source: String,
    },
    name: "addproduct",
   data() {
    return {
      addproduct: {
        proid: "",
        productname: "",
        description: "",
        typeId: "",
        brandId: "",
        partId: "",
        branchId: "",
        price: "",

        
      },
      snaktr: false,
        snactexttrue: ""
    };
  },
  methods:{
   /* eslint-disable */
      getAddproducts(){
        http
        .get("/addproduct")
        .then(response =>{
          this.$forceUpdate();
          this.addproduct = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        });
      },
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
        })
      },
      getBrands(){
        http
        .get("/brand")
        .then(response =>{
          this.$forceUpdate();
          this.brand = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        })
      },
      getParts(){
        http
        .get("/part")
        .then(response =>{
          this.$forceUpdate();
          this.part = response.data;
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
          this.$forceUpdate();
          this.branch = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e);
        })
      },
      
      
    saveAddproducts() {
          http
        .post(
          // ("/addproduct/{productname}/{description}/{brand_id}/{part_id}/{branch_id}")
          "http://localhost:9000/addproduct/" +
            this.addproduct.proid +
            "/" +
            this.addproduct.productname +
            "/" +
            this.addproduct.description +
            "/" +
            this.addproduct.typeId +
            "/" +
            this.addproduct.brandId +
            "/" +
            this.addproduct.partId +
            "/" +
            this.addproduct.branchId +
            "/" +
            this.addproduct.price ,
           
          this.addproduct
        )
        .then(response => {
                  console.log(response);
                  this.snaktr = true;
                  this.snactexttrue ="บันทึกข้อมูลเสร็จสิ้น"
                  this.$refs.form.reset();
                  this.$router.push("/addproduct");
                })
                .catch(e => {
                  console.log(e);
                  this.snaktr = true;
                  this.snactexttrue ="กรุณากรอกข้อมูลให้ครบถ้วน"
                });
        this.submitted = true;
      },
      clear() {
        this.$refs.form.reset();
        this.addproductCheck = false;
      },
    refreshList() {
      this.getAddproducts();
      this.getTypes();
      this.getBrands();
      this.getParts();
      this.getBranchs();
    },
    
  },
      mounted() {
      this.getAddproducts();
      this.getTypes();
      this.getBrands();
      this.getParts();
      this.getBranchs();
      }
  
};
</script>


<style>
.btncenter{
    
    display: block;
    margin-left: auto;
    margin-right: auto;
}
.addpro{
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: 1100px;
    width: 800px; 
    background-color: #EEEEEE;
    opacity: 1;
    -moz-box-shadow:inset 0 0 10px #000000;
   -webkit-box-shadow:inset 0 0 10px #000000;
   box-shadow:inset 0 0 10px #000000;
}
</style>




