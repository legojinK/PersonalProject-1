<template>
    <v-container>
        <v-tabs v-model="active_tab" fixed-tabs background-color="white" color="grey">
            <v-tab v-for="item in tabs" :key="item.id"
            v-show="item.enable === 'Y'" 
            @click="movePage(item.id)">{{item.name}}</v-tab>
        </v-tabs>
        <component :is="currentView" :mainId="data.mainId" :keyId="data.keyId"/>          
    </v-container>    
</template>

<script>

import ReservationMyListPage from '@/views/board/reservation/ReservationMyListPage.vue'
import MyInfoModifyPage from '@/views/my/MyInfoModifyPage.vue'
import BoardMyListPage from '@/views/my/BoardMyListPage.vue'
import CenterMyListPage from '@/views/my/CenterMyListPage.vue'



export default {
    name:'MyInfoPage',  
    components:{
        MyInfoModifyPage,
        CenterMyListPage,
        ReservationMyListPage,
        BoardMyListPage
    },
    data:() =>({
        currentView:'ReservationMyListPage',
        tabs:[{ 
            id:'ReservationMyListPage', name: 'Reservation',enable:'Y'},
            {id:'BoardMyListPage' ,name: 'My board list',enable:'Y'},
            {id:'CenterMyListPage' ,name: 'My service board',enable:'Y'},
            {id:'MyInfoModifyPage', name: 'My profile',enable:'Y'},
        ],
        data:{
        mainId:"",
        keyId:"",
        selectedTab:'ReservationMyListPage',
        },
        active_tab:0,
    }),
     created(){
        this.currentView = 'ReservationMyListPage'
       
    },
    mounted(){
         this.active_tab =0
    },
    methods:{        
        movePage(id){
            this.currentView = id;
        },
    },  
   
}
      
</script>