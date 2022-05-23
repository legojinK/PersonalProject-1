<template>
    <v-container class="title">
        <v-row justify="center">
            <v-dialog v-model="dialogDelete" width="460">
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn text v-bind="attrs" v-on="on"  color="red">
                                     <v-icon class="deleteIcon"> mdi-trash-can-outline </v-icon>
                                </v-btn>
                            </template>

                            <v-card>
                                <v-card-text class="pt-5">
                                    <h3>If you would prefer to end your membership of this website, you may push to 'Resignation'.</h3> 
                                </v-card-text>

                                <v-card-actions>
                                    <v-spacer></v-spacer>

                                    <v-btn @click="onDelete" text color="grey"> Resignation </v-btn>

                                    <v-btn color="red" text @click="dialogDelete = false">Cancle</v-btn>

                                </v-card-actions>
                            </v-card>
                        </v-dialog>
        </v-row>
        <br/>
        <v-divider/>
        <br/>
        <v-row>
            <my-info-modify :userInfo="userInfo" @submit="onSubmit"/>
        </v-row>
    </v-container>
</template>

<script>
import MyInfoModify from '@/components/my/MyInfoModify.vue'
import { mapActions, mapState } from 'vuex'
import axios from 'axios'
export default  {
    name:'MyInfoModifyPage',
    components: {
        MyInfoModify
    },
     data () {
        return {
            
        }
    },
     computed: {
        ...mapState(['userInfo'])
    },
    created() {
        this.fetchMember(this.$store.state.userInfo.userId)
        .then(res=>{
            console.log(res.data)   
        })
        .catch(() => {

        })
    },
   
    methods: {
        ...mapActions(['fetchMember']),
        onSubmit (payload) {
            const {password,passwordCheck, email } = payload
            axios.put(`http://localhost:7777/jpaMember/${this.$store.state.userInfo.userId}`,
                { userId: this.$store.state.userInfo.userId,password,passwordCheck, email,
                auth:this.$store.state.auth.auth})
                    .then(res => {
                        alert('Modify success!')
                        localStorage.removeItem("userInfo")
                        localStorage.setItem("userInfo", JSON.stringify(res.data))
                        //localStorage.setItem("token", res.data.token)
                         alert('Your account information has been changed.Please login again')
                         this.$cookies.remove('user')
                         this.$cookies.remove('auth')
                         this.$store.state.isLogin = false  
                         this.$store.state.userInfo = null
                         this.$store.state.auth = null
                        localStorage.removeItem("token")
                        localStorage.removeItem("userInfo")
                        this.$router.push({ name: 'Home' })
                        window.location.reload();
                        
                        
                    })
                    .catch(() => {
                        alert('땡땡')
                    })
        },
         onDelete () {
            const { memberNo } = this.userInfo
       
           
  
            axios.delete(`http://localhost:7777/jpaMember/${memberNo}`)
                    .then(() => {
                        
                   
                        alert('Completed. Have a good day!')
                        this.$cookies.remove('user')
                        
                        this.$store.state.isLogin = false  
                        this.$store.state.userInfo = null
                        this.$store.state.auth = null
                        localStorage.removeItem("userInfo")
                        localStorage.removeItem("token")
                        this.$router.push({ name: 'Home' })
                        window.location.reload();
                        
                         

                      
                    })
                    .catch(() => {
                        alert('withdraw problem')
                    })
           
        }
    }
}
</script>

<style scoped>

.exit {
    position: absolute;
    left: 80%;
    top:4%;
}

</style>