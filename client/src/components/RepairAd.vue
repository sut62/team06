<template>
<v-app id="test" class="my-auto" :style="{ backgroundImage: 'url(' + require('@/assets/b.jpg') + ')' }" >
      <div class="bn bg-dark">
                <v-app-bar color="deep-purple accent-4" dense dark :style="{ backgroundImage: 'url(' + require('@/assets/a.jpeg') + ')' }"
                    >
                      <v-app-bar-nav-icon></v-app-bar-nav-icon>

                      <v-toolbar-title>Page title</v-toolbar-title>

                      <v-spacer></v-spacer>

                      <v-btn icon>
                        <v-icon>mdi-heart</v-icon>
                      </v-btn>

                      <v-btn icon>
                        <v-icon>mdi-magnify</v-icon>
                      </v-btn>

                      <v-menu left bottom
                      >
                        <template v-slot:activator="{ on }">
                          <v-btn icon v-on="on">
                            <v-icon>mdi-dots-vertical</v-icon>
                          </v-btn>
                        </template>

                        <v-list>
                          <v-list-item
                            v-for="n in 5"
                            :key="n"
                            @click="() => {}"
                          >
                            <v-list-item-title>Option {{ n }}</v-list-item-title>
                          </v-list-item>
                        </v-list>
                      </v-menu>
                    </v-app-bar>
        </div>
 <b-container class=" my-0 align-items-center d-flex justify-content-center" >   <!-- edit to mx-auto for biggerscreen -->
  <b-container wrap class=" my-0 align-items-center d-flex justify-content-center">
    <b-container  wrap class="bg-dark my-0 align-items-center d-flex justify-content-center" align-v="center" align-h="center" >
  <div class="ccc">
    <b-card dark  wrap fill-height class=" my-2 mx-0 bg-secondary align-items-stretch d-flex justify-content-center " align-v="center" align-h="center" >
    <b-row wrap  class="bg align-items-center d-flex justify-content-center"  align-v="center" align-h="center">
      <b-col md=6><b-form-select v-model="repaircp.branch" title="สาขาที่รับงาน"><option value="" selected hidden disabled>Please select</option></b-form-select></b-col>
      <b-col md=6><b-form-select v-model="repaircp.employee" title="สาขาที่รับงาน"></b-form-select></b-col>
       <b-col md=3><b-form-select v-model="repaircp.employee" title="สาขาที่รับงาน"></b-form-select></b-col>
 
      <b-col md=3><b-form-select v-model="repaircp.branch" title="สาขาที่รับงาน"></b-form-select></b-col>
       <b-col md=3><b-form-select v-model="repaircp.employee" title="สาขาที่รับงาน"></b-form-select></b-col>
      <b-col md=3><b-form-select v-model="repaircp.branch" title="สาขาที่รับงาน"></b-form-select></b-col>
   
      <b-col md=4><b-form-select v-model="repaircp.employee" title="สาขาที่รับงาน"></b-form-select></b-col>
      <b-col md=4><b-form-select v-model="repaircp.employee" title="สาขาที่รับงาน"></b-form-select></b-col>
      <b-col md=4><b-form-select v-model="repaircp.employee" title="สาขาที่รับงาน"></b-form-select></b-col>
    </b-row>
  </b-card>
  </div>
    </b-container>
</b-container>
</b-container>

   
</v-app>
</template>
<script>
/* eslint-disable */ 
 import http from "../http-common";
  export default {
    props: {
      source: String,
    },
    name: "RepairCP",
   data() {
    return {
      repaircp: {
        branch: "",
        employee: "",
      },

    };
  },
 
  
  
};
</script>

<style>

.bn{
  max-height: 1090px;
  background-color: #161717;
  display: block;
}
.container{
  height: 599px;
}
.ccc{
  width: 1920px;
}
.card{
  max-height: 100%;
}
.card-body {
  max-width: 100%;
}
</style>