<template>
    <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/bg10.jpg') + ')' }">
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
                            item-text="branchName"
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
              </div>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
    </v-app>
</template>


<script>
import http from "../http-common";
// eslint-disable-next-line no-unused-vars
import DatePicker from 'vuetify'
export default {
  name: "contact",
  components: {
    // eslint-disable-next-line vue/no-unused-components
    DatePicker
  },
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
      valid: false,
      customerCheck: false,
      customerName: ""
    };
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Communication ใส่ combobox
    getCommunication() {
      http
        .get("/communication")
        .then(response => {
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
          if (response.data != null) {
            this.customerName = response.data.cusName;
            this.customerCheck = response.status;
          } else {
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
          //this.$router.push("/appointment");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
      window.location.reload(); 

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
 .btncenter{
    
    display: block;
    margin-left: auto;
    margin-right: auto;
}
.bgn{
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: 900px;
    width: 800px; 
    background-color: #ebecf7;
    opacity: 5;
    -moz-box-shadow:inset 0 0 10px #000000;
   -webkit-box-shadow:inset 0 0 10px #000000;
   box-shadow:inset 0 0 10px #000000;
}
</style>