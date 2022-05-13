<template>
  <v-container >
    <v-card  class="card">    

      <v-row justify="center">
        <v-col cols="12" class="title1"> Reservation</v-col>
      </v-row>

      <v-row>
         <v-col cols="4"> <v-icon class="listIcon" justify="center"> mdi-account-circle-outline</v-icon></v-col>
        <v-col cols="5">
          <v-text-field class="date pt-8" rounded readonly :value="reservation.id"/>
        </v-col>
      </v-row>


      <v-row>
       <v-col cols="4"> <v-icon class="listIcon" justify="center"> mdi-alpha-n-box-outline</v-icon></v-col>
        <v-col cols="5">
          <v-text-field class="date pt-8" rounded readonly :value="reservation.reservationNo"/>
        </v-col>
      </v-row>
       <v-row>
       <v-col cols="2"> <v-icon class="listIcon" justify="center"> mdi-calendar-month-outline</v-icon></v-col>
        <v-col cols="8">
          <v-text-field class="date pt-8" rounded readonly :value="reservation.picker"/>
        </v-col>
      </v-row>


      <v-row>
        <v-col cols="4"> <v-icon class="listIcon" justify="center"> mdi-sofa-single-outline</v-icon></v-col>
        <v-col cols="5">
          <v-text-field class="date pt-8" rounded readonly :value="reservation.seatNumber"/>
        </v-col>
      </v-row>


      <v-row justify="center">
        <router-link :to="{ name: 'SeatForm' }"  style=" text-decoration:none">
          <v-btn class="updateBtn" color="red" dark>   <v-icon color="#eddf68"> mdi-check-circle-outline </v-icon> </v-btn>
        </router-link>
        <v-btn @click=onDelete class="deleteBtn" color="#eddf68" >
                <v-icon color="white"> mdi-trash-can-outline </v-icon>
        </v-btn>
      </v-row>  
      </v-card>

   </v-container>
</template>

<script>

import { mapActions } from 'vuex'
import axios from 'axios'
export default {

  name:'ReservationCheck',
  
  props: {
    reservation: {
      type: Object,
      required: true
    },
  
  },

  data() {
    return {

    }
  },
  created () {
  
        this.id = this.$store.state.session.userId
        this.reservationNo=this.$route.params.reservationNo
    },   
  
  methods: {

    ...mapActions(['fetchReservation']),

    onDelete () {
      const { reservationNo } = this.reservation
      
      axios.delete(`http://localhost:7777/reservation/${reservationNo}`, )
        .then(() => {
          alert('게시글 삭제')
          this.$router.push({ name: 'Home'})
        })
        .catch(() => {
          alert('삭제 실패')
        })
        },

    
  }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Poiret+One&family=Sunflower:wght@300&family=Ubuntu:wght@300&display=swap");

.title1{  

  font-family: 'Ubuntu', cursive;
  font-size: 30pt;
  margin-right:5%;
  text-align: center;
  padding-top: 20px; 

}
.label{
  font-family: 'Poiret One', cursive;
    font-size: 17pt;
    margin-right:5%;
    text-align: center;
    padding-top: 30px; 
}

.card{
  background-color:#f7f6f4;
  padding: 5% 9% 5% 9%;
  margin-left:auto;
  margin-right:auto;
  width:450px;
  height:570px;
  
}
.updateBtn{
   color:white;
  margin-top: 30px;
  margin-right: 20px;

}
.v-text-field, .v-textarea{
    font-family: 'Sunflower', sans-serif;
}

.deleteBtn{
  color:rgba(214, 190, 86, 0.822); 
  margin-top: 30px;

}
.listIcon{
  margin-top: 40px;
  color:#d1a908;

}
.row{
  height:80px;
}
</style>
