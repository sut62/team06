<template>
<v-app id="test" class="my-auto" :style="{ backgroundImage: 'url(' + require('@/assets/BGcontact.jpg') + ')' }" >


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
                            width="18%"
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

                                  <v-list-item to="/searchcontact">
                                      <v-list-item-action>
                                          <v-icon left>search</v-icon>
                                      </v-list-item-action>
                                      <v-list-item-content>
                                          <v-list-item-title>ค้นหาข้อมูลการติดต่อสอบถาม</v-list-item-title>
                                      </v-list-item-content>
                                  </v-list-item>
                              </v-list>
                          </v-navigation-drawer>
                      </nav>
 


  <b-container class=" h-100 my-0 align-items-center d-flex justify-content-center" >   <!-- edit to mx-auto for biggerscreen -->
  <b-container wrap class=" h-100 my-0 align-items-center d-flex justify-content-center">
    
  <div class="bgcrad">
    <b-card id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/customer.png') + ')' }" >
    <b-row ref="form" nowrap  class="bg align-items-center d-flex justify-content-center"  align-v="center" align-h="center">
     
     
        

      <b-col md=4>
            <div class="text-center">
                 
            </div>
            <h1 align='center' class="display-1 font-weight-regular mb-2" color="black">ค้นหาคำถาม</h1>
            <br>
                <v-text-field
                    solo
                    label="กรอกรหัส Contactcode"
                    v-model="contact.contactcode"
                    :rules="[(v) => !!v || 'Item is required']"
                ></v-text-field>
      </b-col>

       <b-col md=4>
         <br><br><br><br>
         <v-btn class="ml-10 mb-2" @click="findContact" raised large color="primary">แสดงคำถาม</v-btn>
         </b-col> 


          <b-col lg=12 >
           <v-flex md14 xs14 lg14 xl4>
               <p class="font-weight-bold"  v-if="contactCheck != ''" >Contactcode : {{Contactcode}}</p>  
               <p class="font-weight-bold"  v-if="contactCheck != ''" >ช่องทางการติดต่อกลับ : {{communicationName}}</p>
               <p class="font-weight-bold"  v-if="contactCheck != ''" >ลูกค้า : {{cusName}}</p> 
               <p class="font-weight-bold"  v-if="contactCheck != ''" >เบอร์โทรศัพท์ : {{phone}} </p>
               <p class="font-weight-bold"  v-if="contactCheck != ''" >Email : {{email}}</p> 
               <p class="font-weight-bold"  v-if="contactCheck != ''" >สาขาที่ต้องการติดต่อ : {{branchname}}</p> 
               <p class="font-weight-bold"  v-if="contactCheck != ''" >เรื่อง : {{headingName}}</p> 
               <p class="font-weight-bold"  v-if="contactCheck != ''" >รายละเอียด : {{detail}}</p> 
                        
              </v-flex>
      </b-col>
    </b-row>

      
   </b-card>
  </div>
    </b-container>
</b-container>
<v-snackbar  v-model="snaktr" :timeout="10000">{{snactexttrue}}
   <v-btn text @click="snaktr = false" >CLOSE</v-btn>
 </v-snackbar>
    

   
</v-app>
</template>
<script>
/* eslint-disable */ 
 import http from "../http-common";
  export default {
    props: {
      source: String,
    },
    name: "contactcode",
   data() {
    return {
      contact: {
       contactcode: "",
  
      },
        valid: false,
      contactCheck: false,
      contactcode: "",
 
      snaktr: false,
      snactexttrue: "",
      drawer: false
    };
  },
  
  methods:{
    
     findContact() {
          http
            .get("/contact/"+ this.contact.contactcode)
            .then(response => {
              console.log(response);
              if (response.data.contactcode != null) {
                    this.Contactcode = response.data.contactcode;
                    this.communicationName = response.data.communication.communicationName;
                    this.cusName = response.data.createdBy.cusName;
                    this.phone = response.data.phone;
                    this.email = response.data.email;
                    this.branchname = response.data.branch.branchname;
                    this.headingName = response.data.heading.headingName;
                    this.detail = response.data.detail;
                    this.contactCheck = response.status;
                    this.snaktr = true;
                    this.snactexttrue =('ค้นหาสำเร็จ')
                  
              } else {
                 this.snaktr = true;
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
      this.contactCheck = false;
    },

    refreshList() {
    },
    
  },
      mounted() {
      }
  
};
</script>

<style>

.bn{
  max-height: 1090px;
  background-color: #161717;
  display: block;
}
.bgcrad{
  width: 1920px;
  background-color: #d1d1d1;
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