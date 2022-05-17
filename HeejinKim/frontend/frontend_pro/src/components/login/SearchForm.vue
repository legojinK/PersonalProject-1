<template>
    <v-container >
      <v-row justify="center" width="600px" class="mt-4">
        <v-col cols="auto" style="padding-bottom: 90px">
          <v-stepper v-model="e13" vertical> <v-stepper-step step="1" color="red"> Check your Email</v-stepper-step>

          <v-stepper-content step="1">
          
            <v-card color="#f7f7f0"  class="mb-12"  height="300px" v-if="!isPass"  >       
              <v-card-text class="text-center px-12 py-16">         
                <validation-observer ref="observer" v-slot="{ invalid }"> 
                <v-form>
                  <v-row> 
                    <v-col cols="12">
                  <validation-provider v-slot="{ errors }" name="Id"  :rules="{ max: 10, required: true }">
                    <v-text-field  color="#e3c832" class="pl-2 pr-4" flat :error-messages="errors" v-model="userId" label="Id" prepend-icon="mdi-account-outline" :counter="10"/>              
                  </validation-provider>
                    </v-col>
                  </v-row>   
                      <!--  <validation-provider v-slot="{ errors }" name="email" :rules="{ email: true, required: true }">    -->  
                  <v-row> 
                      <v-col cols="10">
                          <v-text-field  color="#e3c832" class="pl-2" flat :error-messages="errors"  v-model="email" label="email"  prepend-icon="mdi-email" :counter="20"/>
                      </v-col>
                      <v-col cols="1">
                         <v-btn @click="e13 = 2" class="mt-4">
                        <v-icon @click="matchIdEmail" :disabled="invalid" color="red">mdi-account-check-outline</v-icon></v-btn>
                      </v-col>
                  </v-row>        
                </v-form>      
                 </validation-observer> 
              </v-card-text>
              
            </v-card>

         

           
          </v-stepper-content>
    
     <v-stepper-step
      step="2"
      color="red"
    >
      new password
    </v-stepper-step>

    <v-stepper-content step="2">
      
        <v-card
        color="#f7f7f0"
        class="mb-12"
        height="300px"
        v-if="isPass"> 
          <v-card-text class="text-center px-12 py-16">
            <validation-observer ref="observer" v-slot="{ invalid }">
            <v-form>
              <v-row> 
                <v-col cols="12">
                  <validation-provider v-slot="{ errors }" name="password" :rules="{ max: 15, required: true }">
                    <v-text-field  :error-messages="errors" type="password" v-model="password" label="password"  prepend-icon="mdi-lock-outline" :counter="15" />
                  </validation-provider>
                </v-col>
              </v-row>   
              <v-row> 
                <v-col cols="12">
                  <validation-provider v-slot="{ errors }" name="passwordCheck" :rules="{ max: 15, confirmed: 'password', required: true }">
                    <v-text-field :error-messages="errors"  type="password" v-model="passwordCheck" label="passwordCheck"  prepend-icon="mdi-lock-check-outline" :counter="15" />
                  </validation-provider>
                </v-col>
              </v-row>           
            </v-form>
            <v-btn @click="resetPw" style="margin-top:4%;" :disabled="invalid" type="submit" color="red" dark>change</v-btn> 
            </validation-observer>              
          </v-card-text>
        </v-card>           
        
      
    </v-stepper-content>
  </v-stepper>
        
        </v-col>
      </v-row>
    </v-container>
</template>

<script>
import axios from "axios"
export default {
  name: "SearchForm",
  data() {
    return {
      userId: "",
      email: "",
      isPass: false,
      password: "",
      passwordCheck: "",
       e13: 1,
    }
  },
  methods: {
    matchIdEmail() {
      const { userId, email } = this
      axios
        .post("http://localhost:7777/jpaMember/matchIdEmail", { userId, email })
        .then((res) => {
          if (res.data) {
            alert("find your account")
            this.isPass = true
          } else {
            alert("no account")
            this.isPass = false
            window.location.reload();
            this.$router.push('/searchPw')

          }
        })
    },
    created(){
    this.id=this.$store.state.session.userId
     
      
  },
    resetPw() {
      const { userId, password } = this
      axios
        .post(`http://localhost:7777/jpaMember/modifyFindPw/${userId}`, { password,
        })
        .then(() => {
          alert("Change your password. please login again")
          this.$router.push('/Home')
        })
    },
  },
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400&display=swap");
</style>