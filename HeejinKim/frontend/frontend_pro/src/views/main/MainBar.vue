<template>
    <div class="main_bar">
        <v-toolbar white height="150px">

            <v-app-bar-nav-icon @click="nav_drawer = !nav_drawer">
            </v-app-bar-nav-icon>

            <div id="logo" >     
                    <router-link style="text-decoration: none;" :to="{name: 'Home'}">
                        <h1 style>The Village</h1>
                    </router-link>
            </div>

            <v-spacer></v-spacer>


            <login-form @submit="onLogin" v-if="!isLogin"></login-form>
            <register-form @submit="onRegister" v-if="!isLogin"></register-form>
            <member-bar :session="session" v-if="isLogin && this.$store.state.isAuth == false" @logout="logout"></member-bar>
            <manager-bar :session="session" v-if="isLogin && this.$store.state.isAuth" @logout="logout"></manager-bar>



 

        
        </v-toolbar>

        <!-- 관리자 페이지 만들때 
        <div :session="session" v-if="this.$store.state.isAuth" ><div v-if="isLogin && this.$store.state.session.auth.auth =='Individaul'">-->
            
           
        
            <div v-if="!isLogin||isLogin" >
            <v-navigation-drawer app v-model="nav_drawer" temporary>
                <v-list nav dense>
                    <v-list-item-group v-model="group" active-class="grey--text  ">
                        <v-list-item v-for="link in category" :key="link.name" router :to="link.route">
                            <v-list-item-action>
                                <v-icon right>
                                    {{ link.icon }}
                                </v-icon>
                            </v-list-item-action>

                            <v-list-item-content>
                                <v-list-item-title>
                                    {{ link.text }}
                                </v-list-item-title>
                            </v-list-item-content>

                        </v-list-item>
                    </v-list-item-group>
                </v-list>
            </v-navigation-drawer>
            </div>
            

            <div :session="session" v-if="this.$store.state.isAuth && isLogin" >
             <v-navigation-drawer app v-model="nav_drawer" temporary>
                <v-list nav dense>
                    <v-list-item-group v-model="group" active-class="grey--text  ">
                        <v-list-item v-for="link in authCategory" :key="link.name" router :to="link.route">
                            <v-list-item-action>
                                <v-icon>
                                    {{ link.icon }}
                                </v-icon>
                            </v-list-item-action>

                            <v-list-item-content>
                                <v-list-item-title>
                                    {{ link.text }}
                                </v-list-item-title>
                            </v-list-item-content>

                        </v-list-item>
                    </v-list-item-group>
                </v-list>
            </v-navigation-drawer>
        </div>

    </div>
</template>



<script>

import { mapState} from "vuex";
import LoginForm from '@/components/login/LoginForm.vue'
import RegisterForm from '@/components/login/RegisterForm.vue'
import MemberBar from '@/components/login/MemberBar.vue'
import ManagerBar from '@/components/login/ManagerBar.vue'
import Vue from "vue";
import axios from "axios";
import cookies from "vue-cookies";


Vue.use(cookies)
export default {
    name: 'MainPage',

    components:{
        LoginForm,
        RegisterForm,
        MemberBar,
        ManagerBar
        
    },
    computed:
    {
        ...mapState(['session','auth']),    
    },

    mounted() {
        
        this.$store.state.session = this.$cookies.get("user")
        
        if(this.$store.state.session != null) {

            this.isLogin = true
            this.auth =this.$store.state.auth;

        }else {
            this.isLogin = false
        }
               
        
    },
    
    data () {
        return {
            login_dialog: false, 
            nav_drawer: false,
            group: false,
            isLogin: false,
            

            
            
            category: [
                {  
                    icon: 'mdi-home',
                    name: 'Home',
                    route: '/Home'
                  
                },
                {   
                    text: 'About us',
                    name: 'ProduceForm',
                    route:'/produce'
                    
                },
                 {   route:'/noticeList',
                    text: 'Notice',
                    name: 'NoticeListPage',
                },
                { 
                    text: 'Community',
                    name: 'Community',
                    route:'/communityBoard'
                    
                },
                /*
                {
                    text: 'Reservation',
                    name: 'ReservationPage',
                    route:'/reservation',
                },*/
                {
                    text: 'Reservation',
                    name: 'SeatForm',
                    route:'/seat',
                },
               
             
                {
                     route:'/serviceBoard',
                    text: 'Service Center',
                    name: 'CenterList',
                },
                
            ],

            authCategory: [
                 {  
                    icon: 'mdi-home',
                    name: 'Home',
                    route: '/Home'
                  
                },
                {   
                    text: 'About us',
                    name: 'ProduceForm',
                    route:'/produce'
                    
                },
                { 
                    text: 'Community',
                    name: 'Community',
                    route:'/communityBoard'
                    
                },

                {   route:'/noticeList',
                    text: 'Notice',
                    name: 'NoticeListPage',
                },
 
                {
                     route:'/serviceBoard',
                    text: 'Service Center',
                    name: 'CenterList',
                },
                 {
                     route:'/memberManage',
                    text: 'Member Management',
                    name: 'MemberManagerPage',
                },
                
                
            ],
        }
    },
    

   
    methods: {
        onRegister (payload) {
            const {  userId, password, passwordCheck, email ,auth} = payload

                if(password == passwordCheck) {

                    axios.post(`http://localhost:7777/jpaMember/register`, { userId, password, passwordCheck, email, auth})
                    .then( res => {
                        if(res.data != null ) {
                            alert('Successful!')
                            this.userId = ''
                            this.password = ''
                            this.passwordCheck=''
                            this.email = ''
                            this.auth = ''
                            window.location.reload();
                        } else { 
                        alert('중복된 아이디입니다')
                    }
                    })
                    .catch(res => {
                        alert(res.response.data.message)
                    })
                }
                else {
                    alert('비밀번호가 일치하지 않습니다.')
                return false
                }        
        }, 
            

        onLogin (payload) {

            if(this.$store.state.session == null) {

                const {userId, password} = payload
                
                axios.post('http://localhost:7777/jpaMember/login', {userId, password})
                    .then(res => {

                            if (res.data != "") {
                                alert("Welcome" +"  "+res.data.userId )

                                this.isLogin = true;
                                this.$store.state.session = res.data 
                                this.$store.state.userInfo = res.data  
                                localStorage.setItem("userInfo", JSON.stringify(res.data)) 
                                localStorage.setItem("token", res.data.token)                          
                                this.$cookies.set("user", res.data, 3600)
                
                                if (res.data != "") {
                                    this.memberNo = res.data.memberNo
                                    const {memberNo}=this
                                    axios.get(`http://localhost:7777/memberManage/memberAuth/${memberNo}`)
                                    .then((res)=>{ 
                                        
                                        this.$store.state.auth = res.data
                                        //this.$store.state.auths = res.data
                                        this.$cookies.set("auth", res.data.auth, 3600)
                                        console.log(this.$store.state.userInfo.auth)
                                
                                        //localStorage.setItem("userInfo", JSON.stringify(res.data)) 
                                        
                                        if (res.data.auth == 'Manager') {
                                            this.$store.state.isAuth = true
                                            alert('운영자 아이디로 로그인')
                                        } else {
                                        this.$store.state.isAuth = false
                                        }
                                })
                                this.$router.push({ name: 'Home' }) 
                                } else {
                                    alert('아이디와 비밀번호를 확인해주세요. ' + res.data)
                                }
                            }

                        else {
                            alert('아이디와 비밀번호를 확인해주세요. ' + res.data)
                            window.location.reload();
                        }    
                    })
                   .catch(res => {
                        console.log(res)
                        alert('아이디와 비밀번호를 확인해주세요. ')
                    })
            } 
            else {
                alert("이미 로그인 되어 있습니다. 아이디 : " +this.$store.state.session.userId)
            }
        },     
        logout () {
            this.$cookies.remove('user')
            this.$cookies.remove('auth')
            this.isLogin = false
            this.$store.state.session = null
            this.$store.state.userInfo = null
            this.$store.state.auth = null
            localStorage.removeItem("token")
            localStorage.removeItem("userInfo")


            this.$router.push({ name: 'Home' })
        },
     
    
    },
}

    
    

</script>

<style scoped>
 
 @import url('https://fonts.googleapis.com/css2?family=Poiret+One&display=swap');
 @import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Poiret+One&family=Sunflower:wght@300&family=Ubuntu:wght@300&display=swap");

*{  margin: 0;
    padding: 0;
    box-sizing: border-box;
}
.main_bar{
   font-family: 'Ubuntu', sans-serif; 
   font-weight: bold;
      
}

#logo {
    font-family: 'Poiret One', cursive;
    text-align:center; 
    width: 1500vw;  
    
}
.v-application a {
      color:black;
}
h1 {
    display: block;
    font-size: 4em;
    letter-spacing:0.3vw;
    margin-block-start: 0.3 em;
    margin-block-end: 0.3 em;
    margin-inline-start: 0px;
    margin-inline-end: 0px; 
    margin-left: 10%;       
}

.login{
    text-decoration: none;  
    
    font-size: 20px;
    padding-top: 23px;
    margin-right: 30px;
}
.register{
    text-decoration: none;  
    font-weight: light; 
    font-size: 20px;
    padding-top: 23px

}
/* v-bar 패딩 넣기
.v-application--is-ltr .v-banner__wrapper {
     padding: 6px 5px 6px 5px; 
}
    */

</style>

