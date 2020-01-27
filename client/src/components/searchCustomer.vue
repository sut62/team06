<template>
    <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/bg10.jpg') + ')' }">
        <nav>
            <v-toolbar color="#7C4DFF">
                <v-app-bar-nav-icon @click.native.stop="drawer = !drawer"></v-app-bar-nav-icon>
                <v-toolbar-title>
                    <span class="font-weight-light">CUSTOMER SYSTEM</span>
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn flat color="#EDE7F6" to="/home">
                    <span>Sign Out</span>
                    <v-icon right>exit_to_app</v-icon>
                </v-btn>
            </v-toolbar>

            <v-navigation-drawer color="#EDE7F6" v-model="drawer" app>
                <v-list>
                    <v-list-item>
                        <v-list-item-content class="font-weight-regular">ระบบจัดเก็บข้อมูลลูกค้า</v-list-item-content>
                        <v-icon left @click="drawer = !drawer">arrow_back</v-icon>
                    </v-list-item>
                    <v-list-item to="/customer">
                        <v-list-item-action>
                            <v-icon left>account_box</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title class="font-weight-regular">เพิ่มข้อมูลลูกค้า</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item to="/searchCustomer">
                        <v-list-item-action>
                            <v-icon left>search</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title class="font-weight-regular">ค้นหาข้อมูลลูกค้า</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item to="/viewCustomer">
                        <v-list-item-action>
                            <v-icon left>assessment</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title class="font-weight-regular">ดูข้อมูลลูกค้าทั้งหมด</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
        </nav>

        <div class="bsus">

            <v-container >
                <v-card class="ccc">
                    <br>
                    <div class="text-center">
                        <v-avatar>
                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsEXXyDZ8f1D32PqH3W--4E58hQIkAZZQqxAu6RUP7_UdXEQuZ&s" alt="avatar">
                        </v-avatar>
                    </div>
                    <h1 align='center' class="display-1 font-weight-regular mb-2">ค้นหาข้อมูลลูกค้า</h1>
                    <v-card>
                        <v-row>
                            <v-spacer ></v-spacer>
                            <v-col cols="12" md="4" sm="6" >
                                <v-text-field
                                    solo
                                    label="หมายเลขบัตรประชาชน"
                                    v-model="customer.identification"
                                ></v-text-field>
                            </v-col>

                           <v-col md=4>
                             <v-btn class="ml-12 mb-7" @click="findCustomer" raised large color="primary"><v-icon left>search</v-icon>ค้นหา</v-btn>
                           </v-col>
                                <v-col md=12>
                                    <v-flex>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">ชื่อ-นามสกุล : {{cusName}}</h5>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">หมายเลขบัตรประจำตัวประชาชน : {{identification}}</h5>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">เพศ : {{sex}}</h5>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">อายุ : {{age}}</h5>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">ที่อยู่ : {{address}}</h5>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">ตำบล : {{subDistrict}}</h5>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">อำเภอ : {{district}}</h5>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">จังหวัด : {{province}}</h5>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">เบอร์โทรศัพท์ : {{tel}}</h5>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">E-mail : {{email}}</h5>
                                        <h5 class="fond" align = 'center' v-if="customerCheck != ''">บันทึกโดย : {{empName}}</h5>
                                        <br>
                                    </v-flex>
                                </v-col>
                        </v-row>
                    </v-card>
                </v-card>
            </v-container>
        </div>
        <v-snackbar  color="#64DD17" top v-model="snackpass" :timeout="10000">{{snactexttrue}}
            <v-btn text @click="snackpass = false" >CLOSE</v-btn>
        </v-snackbar>
        <v-snackbar  color="#D50000" top v-model="snackfail" :timeout="10000">{{snactexttrue}}
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
    name: "customer",
   data() {
    return {
      customer: {
          identification: "",
      },
        valid: false,
        customerCheck: false,
        snackpass: false,
        snackfail: false,
        snactexttrue: "",
        drawer: false,
        identification: "",
        cusName: "",
        age: "",
        birth: "",
        address: "",
        empName: "",
        subDistrictId: "",
        districtsId: "",
        province: "",
        tel: "",
        email:"",
        sex: "",
    };
  },
  
  methods:{
    
     findCustomer() {
          http
            .get("/customer/"+ this.customer.identification)
            .then(response => {
              console.log(response);
              if (response.data.identification != null) {
                this.cusName = response.data.cusName;
                this.identification = response.data.identification;
                this.sex = response.data.customerSex.sex;
                this.age = response.data.age;
                this.birth = response.data.birth;
                this.address = response.data.address;
                this.subDistrict = response.data.subDistrict;
                this.district = response.data.district;
                this.province = response.data.customerProvince.province;
                this.tel = response.data.tel;
                this.email = response.data.email;
                this.empName = response.data.createdBy.empName;
                this.customerCheck = response.status;
                this.snackpass = true;
                this.snactexttrue =('ค้นหาสำเร็จ ')

              } else {
                 this.snackfail = true;
                 this.snactexttrue =('ไม่พบข้อมูลลูกค้า')
              }          
            })
            .catch(e => {
              console.log(e);
            });
          this.submitted = true;
        },
        clear() {
      this.$refs.form.reset();
      this.customerCheck = false;
    },

    refreshList() {
    },
    
  },
      mounted() {
      }
  
};
</script>

<style>

.ccc{
  width: 1920px;
}
.fond{
    font-family: Kulachat ;
}
@font-face {
    font-family: 'Kulachat'; /*a name to be used later*/
    src: url('../assets/Kulachat.ttf'); /*URL to font*/
}
.bsus{
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: 865px;
    width: 1000px;
    background-color: #0000;
    opacity: 0.95;
}
</style>