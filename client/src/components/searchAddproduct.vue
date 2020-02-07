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
        

        <div class="sadx">

            <v-container >
                <v-card class="zzz">
                    <br>
                    <div class="text-center">
                       <v-avatar>
                        <img src="http://3.bp.blogspot.com/-eAUofSpwfbI/VXKEcXJGFdI/AAAAAAAAAAg/bgN6NFGW1s8/s1600/256-256-251c205de6105b216e43f280285d959d.png" alt="avatar">
                       </v-avatar>
                    </div>
                    <h1 align='center' class="display-1 font-weight-regular mb-2">searchAddproduct</h1>
                    <v-card>
                        <v-row>
                            <v-spacer ></v-spacer>
                            <v-col cols="12" md="4" sm="6" >
                                <v-text-field
                                    solo
                                    label="ProductID"
                                    v-model="addproduct.proid"
                                ></v-text-field>
                            </v-col>

                           <v-col md=4>
                             <v-btn class="ml-12 mb-7" @click="findAddproduct" raised large color="primary"><v-icon left>search</v-icon>ค้นหา</v-btn>
                           </v-col>
                                <v-col md=12>
                                    <v-flex>
                                        <h5 class="fond" align = 'center' v-if="addproductCheck != ''">ProductID : {{proid}}</h5>
                                        <h5 class="fond" align = 'center' v-if="addproductCheck != ''">ProductName : {{productname}}</h5>
                                        <h5 class="fond" align = 'center' v-if="addproductCheck != ''">Description : {{description}}</h5>
                                        <h5 class="fond" align = 'center' v-if="addproductCheck != ''">Type : {{typename}}</h5>
                                        <h5 class="fond" align = 'center' v-if="addproductCheck != ''">Brand : {{brandname}}</h5>
                                        <h5 class="fond" align = 'center' v-if="addproductCheck != ''">Part : {{partname}}</h5>
                                        <h5 class="fond" align = 'center' v-if="addproductCheck != ''">Branch : {{branchname}}</h5>
                                        <h5 class="fond" align = 'center' v-if="addproductCheck != ''">Price : {{price}}</h5>
                                        <br>
                                    </v-flex>
                                </v-col>
                        </v-row>
                    </v-card>
                </v-card>
            </v-container>
        </div>
        <v-snackbar  top v-model="snackpass" :timeout="10000">{{snactexttrue}}
            <v-btn text @click="snackpass = false" >CLOSE</v-btn>
        </v-snackbar>
        <v-snackbar  top v-model="snackfail" :timeout="10000">{{snactexttrue}}
            <v-btn text @click="snackfail = false" >CLOSE</v-btn>
        </v-snackbar>
        <v-footer color="#EEEEEE">
            <v-spacer></v-spacer>
            <span>COPYRIGHT © 2020 REPAIR COMPUTER. BY TEAM06 OF SOFTWARE ENGINEERING.</span>
        </v-footer>
    </v-app>
</template>

<script>
/* eslint-disable */ 
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
      },
        valid: false,
        addproductCheck: false,
        snackpass: false,
        snackfail: false,
        snactexttrue: "",
        drawer: false,
        proid: "",
        productname: "",
        description: "",
        typename: "",
        brandname: "",
        partname: "",
        branchname: "",
        price: "",
    };
  },
  
  methods:{
    
     findAddproduct() {
          http
            .get("/addproduct/"+ this.addproduct.proid)
            .then(response => {
              console.log(response);
              if (response.data.proid != null) {
                this.proid = response.data.proid;
                this.productname = response.data.productname;
                this.description = response.data.description;
                this.typename = response.data.type.typename;
                this.brandname = response.data.brand.brandname;
                this.partname = response.data.part.partname;
                this.branchname = response.data.branch.branchname;
                this.price = response.data.price;
                this.addproductCheck = response.status;
                this.snackpass = true;
                this.snactexttrue =('ค้นหาสำเร็จ ')

              } else {
                 this.snackfail = true;
                 this.snactexttrue =('ไม่พบข้อมูล')
              }          
            })
            .catch(e => {
              console.log(e);
            });
          this.submitted = true;
        },
        clear() {
      this.$refs.form.reset();
      this.addproductCheck = false;
    },

    refreshList() {
    },
    
  },
      mounted() {
      }
  
};
</script>

<style>

.zzz{
  width: 1920px;
}
.fond{
    font-family: Kulachat ;
}
@font-face {
    font-family: 'Kulachat'; /*a name to be used later*/
    src: url('../assets/Kulachat.ttf'); /*URL to font*/
}
.sadx{
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: 865px;
    width: 1000px;
    background-color: #0000;
    opacity: 0.95;
}
</style>