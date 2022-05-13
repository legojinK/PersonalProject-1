
<template>
  <v-container class="writeForm">
    <v-row>
      <reservation-form @submit="onSubmit" :reservations="reservations"/>
    </v-row>
  </v-container>    
</template>

<script>

import ReservationForm from '@/components/board/reservation/ReservationForm.vue'

import axios from 'axios'
import { mapState,mapActions } from "vuex"; 



export default {
    name:'ReservationPage',
       
    data () {
      return {                  
      }
    },
    computed:
    {
      ...mapState(['reservation','reservations']),    
    },

    components:{
      ReservationForm,    
    },

    mounted() {
      this.picker=this.$route.params.picker
      this.fetchReservationList()
    
  },
     methods: {

    ...mapActions(['fetchReservation','fetchReservationList']),

    onSubmit(payload) { 
      
      const{picker,id, seatNumber} = payload

     

      axios.post(`http://localhost:7777/reservation/finish`,{picker,id,seatNumber})
        .then(() => {

          this.seatNumber =''
          this.picker = '' 
          alert('Reservation complete')
          this.$router.push({name: 'ReservationMyListPage'})
         
        })
        .catch(() => {
          alert('Please select your reservation')
        })
    },
     
      
    }
}
    

</script>
