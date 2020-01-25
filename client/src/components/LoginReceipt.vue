<template>
  <v-app id="login"  :style="{ backgroundImage: 'url(' + require('@/assets/BGLOG.jpg') + ')' }">
    <v-content>
      <v-container
              fluid
              class="hbg"
              id="customer"  :style="{ backgroundImage: 'url(' + require('@/assets/lreceipt.png') + ')' }"
      >
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        <v-row
                align="center"
                justify="center"
        >
          <v-col
                  cols="12"
                  sm="8"
                  md="6"
          >
            <v-text-field
                    label="Username"
                    prepend-icon="person"
                    v-model="login.username"
                    type="text"
                    solo
            ></v-text-field>

            <v-text-field
                    label="Password"
                    prepend-icon="lock"
                    v-model="login.password"
                    type="password"
                    solo
            ></v-text-field>
            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn color="#64DD17" @click="UserValidate">LOGIN</v-btn>
              <v-btn color="primary" to ="/Home">Back</v-btn>
            </v-card-actions>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
    <v-snackbar  top v-model="snaktr" :timeout="10000">{{snactexttrue}}
      <v-btn text @click="snaktr = false" >CLOSE</v-btn>
    </v-snackbar>
    <v-snackbar  top v-model="snaktn" :timeout="10000">{{snactexttrue}}
      <v-btn text @click="snaktn = false" to="/receipt">OK</v-btn>
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
        valid: false,
        snaktr: false,
        snaktn: false,
        snactexttrue: "",
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
                  if(this.message == '{"receipt":"true"}'){
                    this.snaktn = true;
                    this.snactexttrue ="login success"
                  }
                  else if(this.message == '{"massage":"false"}'){
                    this.snaktr = true;
                    this.snactexttrue ="wrong password"
                    this.clear()
                  }
                  else if (this.message == '{"message":"test"}'){
                    this.snaktr = true;
                    this.snactexttrue ="wrong username"
                    this.clear()
                  }
                  else if(this.message == '{"receipt":"false"}'){
                    this.snaktr = true;
                    this.snactexttrue ="login fail"
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
  .lbg{
    display: block;
    margin-left: auto;
    margin-right: auto;
    height: 100%;
    width: 100%;
    background-color: #0000;
    opacity: 0.9;
  }
</style>