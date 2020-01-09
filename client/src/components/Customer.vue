<template >
  <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/bg10.jpg') + ')' }">
  <v-app-bar app color="deep-purple darken-1">
      <v-toolbar-title class="headline text-uppercase">
        <span>Repair</span>
        <span class="font-weight-light"> Computer</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
    <!-- Comment -->
    <!--<v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/customer">Add Customer</router-link>
      </v-btn>&nbsp;
      <v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/viewCustomer">Information</router-link>
      </v-btn>-->&nbsp;
      <v-btn color="grey lighten-5">
        <router-link class="mr-1" to="/home">Logout</router-link>
      </v-btn>
    </v-app-bar>  
    <div class='bcus'>
      <br />
      <br />
      <br />
      <br />
      <div class="text-center">
        <v-avatar>
          <img src="https://mpng.pngfly.com/20180920/ibe/kisspng-businessman-customer-logo-customer-service-compute-boss-svg-png-icon-free-download-455498-online-5ba3be8626cc57.5571033115374577981589.jpg" alt="avatar">
        </v-avatar>
        <br/>
      </div>
      <h1 align='center' class="display-1 font-weight-bold mb-2">Customer Profile</h1>
      <v-container nowrap  class="justify-center">  
        <v-form v-model="valid" ref="form">
          
          <v-layout  row nowrap class="justify-center">
            <v-col cols="10">
              <v-text-field
                solo
                label="ชื่อ-นามสกุล"
                 v-model="customer.cusName"
                :rules="[(v) => !!v || 'กรุณาระบุชื่อ-นามสกุล']"
                required
              ></v-text-field>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-col cols="10">
              <v-text-field
                solo
                type="number"
                label="หมายเลขบัตรประจำตัวประชาชน"
                v-model="customer.identification"
                :rules="[(v) => !!v || 'กรุณาระบุหมายเลขประจำตัวประชาชน']"
                required
              ></v-text-field>
            </v-col>
          </v-layout>    

          <v-layout  row nowrap class="justify-center">
            <v-col cols="3">
              <v-select
                label="เพศ"
                solo
                v-model="customer.sexId"
                :items="sexs"
                item-text="sex"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาระบุเพศ']"
                required
              ></v-select>
            </v-col>

            <v-col cols="3">
              <v-text-field
                solo
                type="number"
                label="อายุ"
                v-model="customer.age"
                :rules="[(v) => !!v || 'กรุณาระบุอายุ']"
                required
              ></v-text-field>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-col cols="10">
              <v-text-field
                solo
                type="date"
                label="วันเกิด"
                v-model="customer.birth"
                :rules="[(v) => !!v || 'กรุณาระบุวันเกิด']"
                required
              ></v-text-field>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-col cols="20" md="10">
              <v-textarea
                solo
                v-model="customer.address"
                name="input-7-4"
                label="รายละเอียดที่อยู่ในการติดต่อ"
                value=""
                :rules="[(v) => !!v || 'กรุณาระบุรายละเอียดที่อยู่ในการติดต่อ']"
                required
              ></v-textarea>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-col cols="3">
              <v-text-field
                solo
                label="ตำบล"
                v-model="customer.subDistrict"
                :rules="[(v) => !!v || 'กรุณาระบุตำบล']"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="3">
              <v-text-field
                solo
                label="อำเภอ"
                v-model="customer.district"
                :rules="[(v) => !!v || 'กรุณาระบุอำเภอ']"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="3">
               <v-select
                label="จังหวัด"
                solo
                v-model="customer.provinceId"
                :items="provinces"
                item-text="province"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาระบุจังหวัด']"
                required
              ></v-select>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-col cols="5">
              <v-text-field
                solo
                type="number"
                label="หมายเลขโทรศัพท์"
                v-model="customer.tel"
                :rules="[(v) => !!v || 'กรุณาระบุหมายเลขโทรศัทพ์']"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="5">
              <v-select
                label="พนักงานที่ทำรายการ"
                solo
                v-model="customer.employeeId"
                :items="employees"
                item-text="empName"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาระบุชื่อพนักงาน']"
                required
              ></v-select>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
              <v-btn  @click="saveCustomer" x-large color="success" dark>SAVE</v-btn>&nbsp;  
              <v-btn  @click="clear" x-large color="blue-grey darken-2" dark>CLEAR</v-btn>
          </v-layout>

        </v-form>         
      </v-container>
    </div>  
  </v-app>
</template>

<script>
import http from "../http-common";
  export default {
  name: "customer",
  data() {
    return {
      customer: {
        customerId: "",
        cusName: "",
        identification: "",
        sexId: "",
        age: "",
        birth: "",
        address: "",
        employeeId: "",
        subDistrictId: "",
        districts: "",
        provinceId: "",
        tel: ""
      },
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล Employee ใส่ combobox
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.$forceUpdate();
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Sex ใส่ combobox
    getSexs() {
      http
        .get("/sex")
        .then(response => {
          this.$forceUpdate();
          this.sexs = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Province ใส่ combobox
    getProvinces() {
      http
        .get("/province")
        .then(response => {
          this.$forceUpdate();
          this.provinces = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveCustomer() {
      http
      //"/customer/{sex_id}/{subdistrict_id}/{district_id}/{province_id}/{employee_id}/{cusName}/{identification}/{age}/{address}/{tel}/{birth}"
        .post(
          "/customer/" +
            this.customer.customerId +
            "/" +
            this.customer.sexId +
            "/" +
            this.customer.subDistrict +
            "/" +
            this.customer.district +
            "/" +
            this.customer.provinceId +
            "/" +
            this.customer.employeeId +
            "/" +
            this.customer.cusName +
            "/" +
            this.customer.identification +
            "/" +
            this.customer.age +
            "/" +
            this.customer.address +
            "/" +
            this.customer.tel +
            "/" +
            this.customer.birth,
          this.customer
        )
        .then(response => {
          console.log(response);
          alert("บันทึกเรียบร้อยแล้ว");
          this.$router.push("/customer");
        })
        .catch(e => {
          console.log(e);
          alert("กรุณากรอกข้อมูลให้ครบถ้วน");
        });
      this.submitted = true;
    },
    clear() {
      this.$refs.form.reset();
      this.customerCheck = false;
    },
    refreshList() {
      this.getEmployees();
      this.getProvinces();
      this.getSexs();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getEmployees();
    this.getProvinces();
    this.getSexs();
  }

};
</script>

<style>
.btncenter{
    
    display: block;
    margin-left: auto;
    margin-right: auto;
}
.bcus{
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: 1050px;
    width: 1000px; 
    background-color: #ebecf7;
    opacity: 0.9;

}
</style>