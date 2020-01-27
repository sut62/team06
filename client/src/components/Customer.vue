<template >
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

    <div class='bcus'>
      <br />
      <div class="text-center">
        <v-avatar>
          <img src="https://mpng.pngfly.com/20180920/ibe/kisspng-businessman-customer-logo-customer-service-compute-boss-svg-png-icon-free-download-455498-online-5ba3be8626cc57.5571033115374577981589.jpg" alt="avatar">
        </v-avatar>
        <br/>
      </div>
      <h1 align='center' class="display-1 font-weight-regular mb-2">เพิ่มข้อมูลลูกค้า</h1>
        <v-form v-model="valid" ref="form">

          <v-layout row nowrap class="justify-center">
            <v-col cols="10">
              <v-text-field
                      outlined
                      label="ชื่อ-นามสกุล"
                      v-model="customer.cusName"
                      :rules="[(v) => !!v || 'กรุณาระบุชื่อ-นามสกุล']"
                      required
                      background-color="#BDBDBD"
              ></v-text-field>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-col cols="10">
              <v-text-field
                      outlined
                      type="number"
                      label="หมายเลขบัตรประจำตัวประชาชน"
                      v-model="customer.identification"
                      :rules="[(v) => !!v || 'กรุณาระบุหมายเลขประจำตัวประชาชน']"
                      required
                      background-color="#BDBDBD"
              ></v-text-field>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-col cols="3">
              <v-select
                      label="เพศ"
                      outlined
                      v-model="customer.sexId"
                      :items="sexs"
                      item-text="sex"
                      item-value="id"
                      :rules="[(v) => !!v || 'กรุณาระบุเพศ']"
                      required
                      background-color="#BDBDBD"
              ></v-select>
            </v-col>

            <v-col cols="3">
              <v-text-field
                      outlined
                      type="number"
                      label="อายุ"
                      v-model="customer.age"
                      :rules="[(v) => !!v || 'กรุณาระบุอายุ']"
                      required
                      background-color="#BDBDBD"
              ></v-text-field>
            </v-col>

            <v-col cols="12" sm="6" md="4">
              <v-menu
                      ref="menu"
                      v-model="menu"
                      :close-on-content-click="false"
                      :return-value.sync="date"
                      transition="scale-transition"
                      offset-y
                      min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-text-field
                          prepend-icon="event"
                          readonly
                          v-on="on"
                          outlined
                          label="วันเกิด"
                          v-model="date"
                          :rules="[(v) => !!v || 'กรุณาระบุวันเกิด']"
                          required
                          background-color="#BDBDBD"
                  ></v-text-field>
                </template>
                <v-date-picker v-model="date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                  <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-col cols="20" md="10">
              <v-textarea
                      outlined
                      v-model="customer.address"
                      name="input-7-4"
                      label="รายละเอียดที่อยู่ในการติดต่อ"
                      value=""
                      :rules="[(v) => !!v || 'กรุณาระบุรายละเอียดที่อยู่ในการติดต่อ']"
                      required
                      background-color="#BDBDBD"
              ></v-textarea>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-col cols="3">
              <v-text-field
                      outlined
                      label="ตำบล"
                      v-model="customer.subDistrict"
                      :rules="[(v) => !!v || 'กรุณาระบุตำบล']"
                      required
                      background-color="#BDBDBD"
              ></v-text-field>
            </v-col>

            <v-col cols="3">
              <v-text-field
                      outlined
                      label="อำเภอ"
                      v-model="customer.district"
                      :rules="[(v) => !!v || 'กรุณาระบุอำเภอ']"
                      required
                      background-color="#BDBDBD"
              ></v-text-field>
            </v-col>

            <v-col cols="3">
              <v-select
                      label="จังหวัด"
                      outlined
                      v-model="customer.provinceId"
                      :items="provinces"
                      item-text="province"
                      item-value="id"
                      :rules="[(v) => !!v || 'กรุณาระบุจังหวัด']"
                      required
                      background-color="#BDBDBD"
              ></v-select>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-col cols="5">
              <v-text-field
                      outlined
                      type="number"
                      label="หมายเลขโทรศัพท์"
                      v-model="customer.tel"
                      :rules="[(v) => !!v || 'กรุณาระบุหมายเลขโทรศัทพ์']"
                      required
                      background-color="#BDBDBD"
              ></v-text-field>
            </v-col>

            <v-col cols="5">
              <v-text-field
                      outlined
                      label="E-mail"
                      v-model="customer.email"
                      background-color="#BDBDBD"
              ></v-text-field>
            </v-col>

            <v-col cols="5">
              <v-select
                      label="พนักงานที่ทำรายการ"
                      outlined
                      v-model="customer.employeeId"
                      :items="employees"
                      item-text="empName"
                      item-value="id"
                      :rules="[(v) => !!v || 'กรุณาระบุชื่อพนักงาน']"
                      background-color="#BDBDBD"
                      required
              ></v-select>
            </v-col>
          </v-layout>

          <v-layout  row nowrap class="justify-center">
            <v-btn  @click="saveCustomer" x-large color="success" dark><v-icon left>save</v-icon>บันทึก</v-btn>&nbsp;
            <v-btn  @click="clear" x-large color="blue-grey darken-2" dark><v-icon left>clear</v-icon>ล้างข้อมูล</v-btn>
          </v-layout>
        </v-form>
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
  import http from "../http-common";
  export default {
    name: "customer",
    data() {
      return {
        drawer: false,
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
          tel: "",
          email:""
        },
        snackpass: false,
        snackfail: false,
        snactexttrue: "",
        date: new Date().toISOString().substr(0, 10),
        menu: false
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
                //"/customer/{sex_id}/{subdistrict_id}/{district_id}/{province_id}/{employee_id}/{cusName}/{identification}/{age}/{address}/{tel}"
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
                        this.date +
                        "/" +
                        this.customer.email,
                        this.customer
                )
                .then(response => {
                  console.log(response);
                  this.snackpass = true;
                  this.snactexttrue ="บันทึกข้อมูลเสร็จสิ้น"
                  this.$refs.form.reset();
                  this.$router.push("/customer");
                })
                .catch(e => {
                  console.log(e);
                  this.snackfail = true;
                  this.snactexttrue ="กรุณากรอกข้อมูลให้ครบถ้วน"
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
    height: 1015px;
    width: 1000px;
    background-color: #ebecf7;
    opacity: 0.95;
  }
  .fond {
    font-family: Kulachat;
  }
</style>