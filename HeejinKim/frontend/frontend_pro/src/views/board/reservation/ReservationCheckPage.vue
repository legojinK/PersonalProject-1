<template>
  <v-container class="writeForm">

    <v-row>
      <reservation-check  :reservation="reservation" />             
    </v-row>
    
  </v-container>
      
</template>

<script>


import ReservationCheck from '@/components/board/reservation/ReservationCheck.vue'
//import axios from 'axios'
import { mapState,mapActions} from "vuex"; 


export default {
    name:'ReservationCheckPage',          
    
    props: {
        reservationNo: {
            type: String,
            required: true
        }
    },
     components:{
      ReservationCheck
    },
    computed:
    {
      ...mapState(['reservation','reservationIdLists']),    
    },
    
     created () {
  
        this.reservationNo = this.$route.params.reservationNo
       // this.getReadBooking()
       this.fetchReservation(this.reservationNo)
                .catch(() => {
                    alert('게시물 요청 실패!')
                    console.log(this.reservationNo)
                    this.$router.push()
                })
    },
     
      
    

   
    methods: {
            ...mapActions(['fetchReservationIdLists','fetchReservation']),

    }
}
      
</script>
