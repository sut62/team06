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
        <br>
        <div class="cbg">
            <v-card class="sbg" color="#EDE7F6">
                <v-card-title>
                    <v-text-field
                            v-model="search"
                            label="ค้นหา"
                            single-line
                            hide-details
                            solo
                            append-icon="search"
                    ></v-text-field>
                </v-card-title>
            </v-card>
            <br>
            <v-card>
                <v-data-table
                        :headers="headers"
                        :items="items"
                        :search="search"
                        :dense="items"
                >
                </v-data-table>
            </v-card>
        </div>
        <v-footer color="#EEEEEE">
            <v-spacer></v-spacer>
            <span>COPYRIGHT © 2020 REPAIR COMPUTER. BY TEAM06 OF SOFTWARE ENGINEERING.</span>
        </v-footer>
    </v-app>
</template>

<script>
    import http from "../http-common";

    export default {
        name: "ViewCustomer",
        data() {
            return {
                drawer: false,
                search: '',
                headers: [
                    { text: 'หมายเลขบัตรประจำตัวประชาชน', value: 'identification' },
                    { text: 'ชื่อ-นามสกุล', value: 'cusName' },
                    { text: 'เพศ', value: 'customerSex.sex' },
                    { text: 'อายุ', value: 'age' },
                    { text: 'วันเกิด', value: 'birth' },
                    { text: 'ที่อยู่', value: 'address' },
                    { text: 'ตำบล', value: 'subDistrict' },
                    { text: 'อำเภอ', value: 'district' },
                    { text: 'จังหวัด', value: 'customerProvince.province' },
                    { text: 'เบอร์โทรศัพท์', value: 'tel' },
                    { text: 'E-mail', value: 'email' },
                    { text: 'ผู้บันทึกข้อมูล', value: 'createdBy.empName' },
                ],
                items: []
            };
        },
        methods: {
            /* eslint-disable no-console */
            // ดึงข้อมูล Customer ใส่ combobox
            getCustomers() {
                http
                    .get("/customer")
                    .then(response => {
                        this.items = response.data;
                        console.log(this.items);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            refreshList() {
                this.getCustomers();
            },
            deleteCustomer() {
                http
                    .delete("/customer/" + this.customer.id)
                    .then(response => {
                        console.log(response.data);
                        this.$emit("refreshData");
                        this.$router.push('/');
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }
            /* eslint-disable no-console */
        },
        mounted() {
            this.getCustomers();
        }

    };
</script>

<style>
    .cbg{
        display: block;
        margin-left: auto;
        margin-right: auto;
        height: 950px;
        width: 1800px;
        background-color: #0000;
    }
    .sbg{
        margin-right: auto;
        width: 500px;
    }
</style>