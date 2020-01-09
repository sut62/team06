<template>
  <v-app id="login" :style="{ backgroundImage: 'url(' + require('@/assets/d.jpg') + ')' }">
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
                <v-toolbar-title>Login to Health Insurance System </v-toolbar-title>
                
                <div class="flex-grow-1"></div>
               
               
              </v-toolbar>
              <v-card-text>
                <v-form v-model="valid" ref="form">
                  <v-text-field
                    label="Username"
                    prepend-icon="person"
                    v-model="login.username"
                    type="text"
                  ></v-text-field>

                  <v-text-field
                    label="Password"
                    prepend-icon="lock"
                    v-model="login.password"
                    type="password"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              
              <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn color="primary" @click="UserValidate">LOGIN</v-btn>
                <v-btn color="primary" to ="/Register">Register</v-btn>
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
    name: "login",
   data() {
    return {
      localStorageSupport: true,
      login: {
        username:'',
        password:''
        },
        valid: false
    };
  },
 
  methods:{
   /* eslint-disable */
   clear() {
      this.$refs.form.reset();
    },
        UserValidate() {
      console.log("Login!!!")
          http
        .post(
          "/validate/" +
            this.login.password +
            "/" +
            this.login.username,
          this.regis
        )
        .then(response => {
            console.log(response);
            this.message = JSON.stringify(response.data);
          if(this.message == '{"p1":"true"}'){
                alert('login success')
                this.$router.push({ path: `/customer` })
          }
          else if(this.message == '{"p2":"true"}'){
                alert('login success')
                this.$router.push({ path: `/fix` })
          }
          else if(this.message == '{"p3":"true"}'){
                alert('login success')
                this.$router.push({ path: `/contact` })
          }
          else if(this.message == '{"p4":"true"}'){
                alert('login success')
                this.$router.push({ path: `/receipt` })
          }
          else if(this.message == '{"p5":"true"}'){
                alert('login success')
                this.$router.push({ path: `/addproduct` })
          }
          else if(this.message == '{"p6":"true"}'){
                alert('login success')
                this.$router.push({ path: `/repairindex` })
          }
          else if(this.message == '{"massage":"false"}'){
                    alert('wrong password')
                    this.clear()
          }
          else if (this.message == '{"message":"test"}'){
              alert('wrong username')
              this.clear()
          }
          else if(this.message == '{"p1":"false"}'){
               alert('login false')
               this.clear()
          }
          else if(this.message == '{"p2":"false"}'){
               alert('login false')
               this.clear()
          }
          else if(this.message == '{"p3":"false"}'){
               alert('login false')
               this.clear()
          }
          else if(this.message == '{"p4":"false"}'){
               alert('login false')
               this.clear()
          }
          else if(this.message == '{"p5":"false"}'){
               alert('login false')
               this.clear()
          }
          else if(this.message == '{"p6":"false"}'){
            alert('login false')
            this.clear()
          }
          else{
         }
        })
        .catch(e => {
          console.log(e);
           if(e = true){
             localStorage.removeItem(this.login.username, 'token')
            
           
			}
        });
      this.submitted = true;
    },
    
  },
  
};
  
</script>

<style>
</style>