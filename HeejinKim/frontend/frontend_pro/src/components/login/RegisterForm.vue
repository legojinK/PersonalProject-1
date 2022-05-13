<template>
    <v-dialog v-model="register_dialog" max-width="350">
        <template v-slot:activator="{ on, attrs }">
            <v-btn text color="black" class="btn-flat" v-on="on" v-bind="attrs">
                Register
            </v-btn>
        </template>
        <v-card>
            <v-container >
                <validation-observer ref="observer" v-slot="{ invalid }"> 
                <form @submit.prevent="onRegister">
                    
                    <v-card class="card_box">
                        <h1 class="text-center"> Create Account </h1>
                        <div class="input" justify-center>
                           
                            <v-row> 
                                <v-col cols="8">
                                     <validation-provider v-slot="{ errors }" name="Id"  :rules="{ max: 10, required: true }">
                                    <v-text-field class="idsection" style="width:90%" flat solo v-model="userId" :disabled="valid" type="text" label="Id" :rules="rulesId" :error-messages="errors" :counter="12"/>
                                     </validation-provider>
                                </v-col>
                                <v-col cols="1">
                                   
                                    <v-icon @click="checkValid" :disabled="userId == ''" color="red" class="mt-5 ml-3">mdi-account-check-outline</v-icon>
                                </v-col>
                            </v-row>        
                                
                            <div class="input_area">
                                <validation-provider v-slot="{ errors }" name="password" :rules="{ max: 15, required: true }">
                            <v-text-field class="pl-4 pr-4 pt-3" flat solo v-model="password" type="password" label="password"
                             :error-messages="errors" :counter="15"> </v-text-field>
                            </validation-provider></div>

                            <div class="input_area">
                                   <validation-provider v-slot="{ errors }" name="passwordCheck" :rules="{ max: 15, confirmed: 'password', required: true }">
                            <v-text-field :error-messages="errors"  class="pl-4 pr-4" flat solo v-model="passwordCheck" type="password" label="passwordCheck" 
                            > </v-text-field>
                             </validation-provider></div>

                            <div class="input_area">
                                
                                 <validation-provider v-slot="{ errors }" name="email" :rules="{ required: true,email:true }">
                            <v-text-field class="pl-4 pr-4" flat solo v-model="email" type="text" label="email" :error-messages="errors" 
                            > </v-text-field>
                             </validation-provider></div>

                            
                            <div class="radiosection">
                                  <validation-provider v-slot="{ errors }" name="email" :rules="{ required: true }">
                                <v-radio-group v-model="radioGroup" row :error-messages="errors" >
                                  <v-radio :label="`${kindsOfMember[0]}`" :value="`${kindsOfMember[0]}`"></v-radio>
                                  <v-radio :label="`${kindsOfMember[1]}`" :value="`${kindsOfMember[1]}`"></v-radio>
                                </v-radio-group>
                                 </validation-provider>
                            </div>


                            <div>
                                <v-btn width="150" height="40px" type="submit" color="black" style="mb-5"
                                class="white--text" rounded :disabled="invalid" >
                                SIGN UP
                                </v-btn>
                            </div>
                        </div>
                    </v-card>
                </form>
                </validation-observer> 
            </v-container>
        </v-card>
    </v-dialog>
</template>

<script>

import axios from "axios";

export default {
    name: 'RegisterForm',

    props:{
        members:{
            type:Array
        }
    },

        data(){
            return {
                register_dialog: false,
                userId : '',
                password: '',
                passwordCheck : '',
                email : '',
                valid: false,
                radioGroup: 1,
                kindsOfMember: [
                    'Individual',
                    'Manager'
                ],
                rulesId:[
                    v => !!v || 'Enter your id'
                ],

                rulesPassword:[
                    v => !!v || 'Enter your password',
                    v => v.length >= 3 || 'Password must be more than 3 characters'     
                ],

                rulesPWCheck:[
                    v => !!v || 'Confirm your password',
                    v => v === this.password || 'Wrong password'
                ],

                rulesEmail:[
                v => !!v ||  'Enter your email',
                v => /.+@.+\..+/.test(v) || 'Email must be valid',
                
                ]
                
            }
        },
    methods: {
        onRegister () {

            if (!this.valid) {
            alert("please check your registerForm")
            }else{
            const {  userId, password, passwordCheck, email, radioGroup } = this
            const auth = (radioGroup == 'Individual' ? 'Individual' : 'Manager')
            this.$emit('submit', { userId, password, passwordCheck, email, auth})
            }
            
        },
        checkValid() {
            const { userId } = this
            let temp;
            axios.get(`http://localhost:7777/jpaMember/check/${userId}`)
                .then(res => {
                    this.temp = res.data;
                    if(this.temp){
                        alert("Valid Id")
                        this.valid = res.data;
                    }else {
                        alert("Duplicate Id")
                        this.valid = false;
                    }
                })
            return temp
        }
    },
    
}
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Poiret+One&display=swap');

.theme--light.v-card {
    background-color:#f3f3f3;
}
.card_box{
    
    height: 680px;
    text-align: center;
    position: relative;
    display: center;
    padding: 5% 0% 30% 0%;
    width: 500px;
    
}
.radiosection{
    height: 80px;
    padding-bottom: 10px;
 
}
.idsection{
    margin-left: 30px;
    padding-top:10px
}
.row{
    height: 100px;
}

.card_box h1{

    font-family: 'Poiret One', cursive;
    font-size: 46px;
    margin-top: 20px;
    color: black;
    text-align: center;
    
}
.input{
    
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 200px; 
    height: 80px; 

}
.v-input{  
    font-size: 20px;
}




/* 입력칸 배경색
.theme--light.v-text-field--solo > .v-input__control > .v-input__slot {
    background: #583d3d;
}*/





</style>
