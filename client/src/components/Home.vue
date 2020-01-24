<template>
    <v-app id="back"  :style="{ backgroundImage: 'url(' + require('@/assets/L2.jpg') + ')' }">
        <v-container class="hbg" id="home"  :style="{ backgroundImage: 'url(' + require('@/assets/L0.png') + ')' }">
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <v-row no-gutters class="mx-lg-auto">
                <v-col>
                    <v-hover
                            v-slot:default="{ hover }"
                            open-delay="200"
                    >
                        <v-card
                                :elevation="hover ? 16 : 2"
                                class="mx-lg-auto"
                                height="180"
                                max-width="180"
                                to="/loginfix"
                                id="fix"  :style="{ backgroundImage: 'url(' + require('@/assets/fix.jpg') + ')' }"
                        >
                        </v-card>
                    </v-hover>
                </v-col>
                <v-col>
                    <v-hover
                            v-slot:default="{ hover }"
                            open-delay="200"
                    >
                        <v-card
                                :elevation="hover ? 16 : 2"
                                class="mx-lg-auto"
                                height="180"
                                max-width="180"
                                to="/loginadmin"
                                id="repairindex"  :style="{ backgroundImage: 'url(' + require('@/assets/repairindex.jpg') + ')' }"
                        >
                        </v-card>
                    </v-hover>
                </v-col>
                <v-col>
                    <v-hover
                            v-slot:default="{ hover }"
                            open-delay="200"
                    >
                        <v-card
                                :elevation="hover ? 16 : 2"
                                class="mx-lg-auto"
                                height="180"
                                max-width="180"
                                to="/loginreceipt"
                                id="receipt"  :style="{ backgroundImage: 'url(' + require('@/assets/receipt.jpg') + ')' }"
                        >
                        </v-card>
                    </v-hover>
                </v-col>
                <v-col>
                    <v-hover
                            v-slot:default="{ hover }"
                            open-delay="200"
                    >
                        <v-card
                                :elevation="hover ? 16 : 2"
                                class="mx-lg-auto"
                                height="180"
                                max-width="180"
                                to="/loginaddproduct"
                                id="addproduct"  :style="{ backgroundImage: 'url(' + require('@/assets/addproduct.jpg') + ')' }"
                        >
                        </v-card>
                    </v-hover>
                </v-col>
                <v-col>
                    <v-hover
                            v-slot:default="{ hover }"
                            open-delay="200"
                    >
                        <v-card
                                :elevation="hover ? 16 : 2"
                                class="mx-lg-auto"
                                height="180"
                                max-width="180"
                                to="/logincustomer"
                                id="customer"  :style="{ backgroundImage: 'url(' + require('@/assets/customer.jpg') + ')' }"
                        >
                        </v-card>
                    </v-hover>
                </v-col>
                <v-col>
                    <v-hover
                            v-slot:default="{ hover }"
                            open-delay="200"
                    >
                        <v-card
                                :elevation="hover ? 16 : 2"
                                class="mx-lg-auto"
                                height="180"
                                max-width="180"
                                to="/logincontact"
                                id="contact"  :style="{ backgroundImage: 'url(' + require('@/assets/contact.jpg') + ')' }"
                        >
                        </v-card>
                    </v-hover>
                </v-col>

            </v-row>
        </v-container>
        <v-footer color="#EEEEEE">
            <v-spacer></v-spacer>
            <span>COPYRIGHT © 2020 REPAIR COMPUTER. BY TEAM06 OF SOFTWARE ENGINEERING.</span>
        </v-footer>
    </v-app>
</template>
<script>

</script>

<style>
    .hbg{
        display: block;
        margin-left: auto;
        margin-right: auto;
        height: 1000px;
        width: 1000px;
        background-color: #0000;
        opacity: 0.9;
    }
    .bgc {
        background-color: aqua;
    }
</style>