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
              width="15%"
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
 












       <br>
       <br>
       <br>
       <br>
       <div class='bgn'>
          <v-container>
            <v-layout text-center wrap>
              <v-flex mb-4>
                <div class="text-center">
                  <v-avatar>
                    <img src="http://icons.iconarchive.com/icons/graphicloads/100-flat-2/256/email-icon.png" alt="avatar">
                  </v-avatar>
                </div>
                  <h1 class="display-2 font-weight-bold mb-3">ติดต่อสอบถาม</h1>
              </v-flex>
            </v-layout>

          <v-row justify="center">
          <v-col cols="15">
          <v-form v-model="valid" ref="form">
          <v-row justify="center"> 
            <v-col cols="8">
              <v-text-field
                solo
                label="เลขบัตรประชาชน"
                v-model="contact.customerIdent"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
              <p v-if="customerCheck != ''">ชื่อ : {{customerName}}<br>
  
              </p>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findCustomer" depressed large color="primary">ค้นหา</v-btn>
              </div>
            </v-col>
           </v-row>  

                         <v-row justify="center">
                          <v-col cols="10">
                           <v-select
                             label="เลือกช่องทางการติดต่อกลับ"
                             solo
                             v-model="contact.communicationId"
                            :items="communications"
                            item-text="communicationName"
                            item-value="id"
                            :rules="[(v) => !!v || 'Item is required']"
                            required
                          ></v-select>
                          </v-col>
                        </v-row>

                        <v-row justify="center">
                          <v-col cols="20" sm="6" md="3">
                            <v-text-field
                              label="กรอก เบอร์โทรศัพท์"
                              solo
                              v-model="contact.phone"
                            ></v-text-field>
                          </v-col>

                          <v-col cols="12" sm="6" md="7">
                           <v-text-field
                            label="กรอก อีเมล"
                            solo
                            v-model="contact.email"
                          ></v-text-field>
                          </v-col>
                        </v-row>
           
                        <v-row justify="center">
                          <v-col cols="10">
                           <v-select
                            label="เลือกสาขาที่ต้องการติดต่อ"
                            solo
                            v-model="contact.branchId"
                            :items="branchs"
                            item-text="branchname"
                            item-value="id"
                            :rules="[(v) => !!v || 'Item is required']"
                            required
                           ></v-select>
                          </v-col>
                        </v-row> 

                        <v-row justify="center">
                          <v-col cols="10">
                           <v-select
                            label="เลือกเรื่องที่ต้องการติดต่อ"
                            solo
                            v-model="contact.headingId"
                            :items="headings"
                            item-text="headingName"
                            item-value="id"
                            :rules="[(v) => !!v || 'Item is required']"
                             required
                           ></v-select>
                          </v-col>
                        </v-row> 

                        <v-row justify="center">
                          <v-col cols="20" md="10">
                            <v-textarea
                              solo
                              v-model="contact.detail"
                              name="input-7-4"
                              label="กรอก รายละเอียดที่ต้องการติดต่อ"
                              value=""
                            ></v-textarea>
                          </v-col>
                        </v-row>

                        <v-row justify="center">
                          <v-btn @click="saveContact" :class="{ red: !valid, primary: valid }">submit</v-btn>
                        </v-row>
                        <br>
                      </v-form>
                    </v-col>
                  <br>
                  <br>
                  <br>
                  <br>
                  </v-row>
                </v-container>
                
                      <v-snackbar  v-model="snaktr" :timeout="10000">{{snactexttrue}}
                      <v-btn text @click="snaktr = false" >CLOSE</v-btn>
                     </v-snackbar>

              </div>
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
  name: "contact",
  data() {
    return {
      contact: {

        customerIdent: "",
        communicationId: "",
        email:"",
        phone:"",
        branchId: "",
        headingId: "",
        detail: ""

        
      },
        snaktr: false,
        snactexttrue: ""
    };
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Communication ใส่ combobox
    getCommunication() {
      http
        .get("/communication")
        .then(response => {
          this.$forceUpdate();
          this.communications = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Branch ใส่ combobox
    getBranch() {
      http
        .get("/branch")
        .then(response => {
          this.$forceUpdate();
          this.branchs = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Heading ใส่ combobox
    getHeading() {
      http
        .get("/heading")
        .then(response => {
          this.$forceUpdate();
          this.headings = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function ค้นหาลูกค้าด้วย ID
    findCustomer() {
      http
        .get("/customer/" + this.contact.customerIdent)
        .then(response => {
          console.log(response);
          console.log(response.data.cusName)
          if (response.data.cusName != null) {
            this.customerName = response.data.cusName;
            this.customerCheck = response.status;
            this.snaktr = true;
            this.snactexttrue =('ค้นหาสำเร็จ'+this.contact.customerIdent )


          } else {
              this.snaktr = true;
              this.snactexttrue =('ไม่พบ ID ที่ค้นหา' ) 
              this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    // function เมื่อกดปุ่ม submit
    saveContact() {
      console.log(this.contact.customerId);
      console.log(this.contact.communicationId);
      console.log(this.contact.branchId);
      console.log(this.contact.headingId);
      http
        .post(
          //("/contact/{identification}/{communication_id}/{email}/{phone}/{branch_id}/{heading_id}/{detail}")
          "/contact/" +
          this.contact.customerIdent +
          "/" +
          this.contact.communicationId +
          "/" +
          this.contact.email+
          "/" +
          this.contact.phone+
          "/" +
          this.contact.branchId +     
          "/" +
          this.contact.headingId+
          "/" +
          this.contact.detail,
        this.contact,
           console.log(this.contact),
        )
                .then(response => {
                  console.log(response);
                  this.snaktr = true;
                  this.snactexttrue ="บันทึกข้อมูลเสร็จสิ้น"
                  this.$refs.form.reset();
                  this.$router.push("/contact");
                   
                })
                .catch(e => {
                  console.log(e);
                  this.snaktr = true;
                  this.snactexttrue ="กรุณากรอกข้อมูลให้ถูกต้องครบถ้วน"
                });
        this.submitted = true;
        window.location.reload();
       
    },
          clear() {
        this.$refs.form.reset();
        this.contactCheck = null;
      },

    refreshList() {
      this.getCommunication();
      this.getBranch();
      this.getHeading();
 
    }
    
    /* eslint-enable no-console */
  },
  mounted() {
      this.getCommunication();
      this.getBranch();
      this.getHeading();
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
  background: -webkit-linear-gradient(-135deg, #000031, rgb(38, 105, 228));
  background: -o-linear-gradient(-135deg, #000031,  rgb(38, 105, 228));
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

     .btncenter{
    
    display: block;
    margin-left: auto;
    margin-right: auto;
}
.bgn{
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: 1000px;
    width: 800px; 
    background-color: #ebecf7;
    opacity: 5;
    -moz-box-shadow:inset 0 0 10px #000000;
   -webkit-box-shadow:inset 0 0 10px #000000;
   box-shadow:inset 0 0 10px #000000;
}
</style>
