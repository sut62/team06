<template>
  <v-app id="inspire" :style="{ backgroundImage: 'url(' + require('@/assets/a.jpeg') + ')' }">
    <v-content>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col
            cols="12"
            sm="8"
            md="4"
          >
            <v-card class="elevation-12">
              <v-toolbar
                color="primary"
                dark
                flat
              >
                <v-toolbar-title>Register to Health Insurance System </v-toolbar-title>
                
                <div class="flex-grow-1"></div>
               
               
              </v-toolbar>
              <v-card-text>
                <v-form v-model="valid" ref="form">
                  <v-text-field
                    label="Username"
                    prepend-icon="person"
                    v-model="regis.username"
                    type="text"
                  ></v-text-field>

                  <v-text-field
                    label="Password"
                    prepend-icon="lock"
                    v-model="regis.password"
                    type="password"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              
              <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn color="primary" @click="saveRegister">Register</v-btn>
                <v-btn color="primary" to ="/login">LOGIN</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import http from "../http-common";
  export default {
   
    props: {
      source: String,
    },
    name: "regis",
   data() {
    return {
      regis: {
        username:'',
        password:'',
     
        
      },
      valid: false,

    };
  },
  methods:{
   /* eslint-disable */
  clear() {
      this.$refs.form.reset();
    },

    saveRegister() {
      console.log("Regis!!!")
          http
        .post(
          "/adduser/" +
            this.regis.password +
            "/" +
            this.regis.username,
          this.regis
        )
        .then(response => {
          console.log(response);
          console.log(this.regis.username);
          if(response.data.profileid != null){
            alert('บันทึกข้อมูลเสร็จสิ้น')
           this.$router.push({ path: `/login` })
          } 
      
        })
        .catch(e => {
          console.log(e);
           if(e = true){
            alert('การบันทึกข้อมูลผิดพลาด กรุณาตรวจสอบusename และ password')
            console.log(this.regis.username);
            this.clear()
          }
        });
      this.submitted = true;
    },
    
  },
  
};
  
</script>

<style>
</style>