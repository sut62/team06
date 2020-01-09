<template>
  <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/addpro.jpeg') + ')' }">
  <v-app-bar app color="#C62828">
      <v-toolbar-title class="headline text-uppercase">
        <span >ADD</span>
        <span class="font-weight-light"> PRODUCT</span>
      </v-toolbar-title>
      
      <v-spacer></v-spacer>
      <!--<v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/">ADD PRODUCT </router-link>
      </v-btn>
      <v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/view">INFORMATION</router-link>
      </v-btn>-->
      <v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/home">LOGOUT</router-link>
      </v-btn>
    </v-app-bar>
    <div class='addpro'>                   
    <br>
    <div class="text-center">
    <br>
<br>
<br>
    <v-avatar>
      <img src="https://image.flaticon.com/icons/png/512/187/187503.png" alt="avatar">
    </v-avatar>
  </div>
  
      <h1 align='center'>เพิ่มอะไหล่คอมพิวเตอร์</h1>
    
    <v-container nowrap  class="justify-center">
      <v-form v-model="valid" ref="form">
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
            <v-col cols="3">   
                <v-row justify="center">
              <v-col cols="12">  
                <v-btn @click="saveAddproducts" :class="{ red: !valid, green: valid }">save</v-btn>
                <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
              </v-col>
            </v-row>
            </v-col>
            </v-layout>

            
      </v-form>   
    </v-container>
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
       
        productname: "",
        description: "",
        typeId: "",
        brandId: "",
        partId: "",
        branchId: "",

        
      },
      valid: false,
      drawer: false
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
            this.addproduct.branchId ,
           
          this.addproduct
        )
        .then(response => {
          console.log(response);
          if(response = true){
            alert('บันทึกข้อมูลเสร็จสิ้น')
          } 
          this.$refs.form.reset();
          
        })
        .catch(e => {
          console.log(e);
           if(e = true){
            alert('กรุณากรอกข้อมูลให้ครบถ้วน')

            
            
          }
        });
      this.submitted = true;
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
    height: 900px;
    width: 800px; 
    background-color: #E0E0E0;
    opacity: 0.9;
    -moz-box-shadow:inset 0 0 10px #000000;
   -webkit-box-shadow:inset 0 0 10px #000000;
   box-shadow:inset 0 0 10px #000000;
}
</style>




