<template>

  <v-container class="place" justify="center">
    <v-form @submit.prevent="onSubmit">
      
    <v-row justify="center">
      <v-col cols="8" class="label" > Seat checking</v-col>            
    </v-row>

    <v-row>
      <v-col cols="1">
        <v-icon class="listIcon" justify="center"> mdi-sofa-single-outline</v-icon></v-col>
      <v-col cols="10" justify="center">
        <div class="llabel"> {{$route.params.seatNumber}} </div>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="1">
        <v-icon class="listIcon" justify="center"> mdi-calendar-clock-outline</v-icon></v-col>
      <v-col cols="10" justify="center">
          <div class="llabel">  {{$route.params.picker}} </div>
      </v-col>
    </v-row>
    


    <v-row> 
       <v-btn @click="goList" text class="listBtn">
       <v-icon class="goseat" > mdi-keyboard-backspace</v-icon></v-btn>             
        <v-btn class="selectSeatBtn" type="submit" dark>
       select</v-btn>

    </v-row>
      
    </v-form>
    
 </v-container>
</template>

<script>

import { mapActions } from 'vuex'

export default {

  name:'ReservationForm',

  data() {
    return {
      seatNumber:'',
      id:'',
      picker:''

    }
  }, 
  mounted() {
    this.picker=this.$route.params.picker
    this.seatNumber=this.$route.params.seatNumber
    this.id=this.$store.state.session.userId
    
  },
 
  methods: {

     ...mapActions(['fetchReservation']),
   
    
    //디비로 확정 정보 넣는 것(reservation 쪽에)--날리기

    onSubmit  () {
      const { picker,id,seatNumber} = this                
      this.$emit('submit', { picker,id, seatNumber })
      
    },
    goList(){
       this.$router.push("/seat")
    }
    
  }
}
</script>

<style scoped>

.llabel{
 font-family: 'Poiret One', cursive;
  font-size: 25px;
  margin-left:15%;
}
.label{
  font-family: 'Poiret One', cursive;
  font-size:30pt;
  margin-right:5%;
  text-align: center;
  padding-top: 20px; 
  font-weight:bold;
}
.place{
    background-color: #f8f8f8;
    padding: 5% 10% 5% 10%;
    margin-top:30px;
    margin-left:auto;
    margin-right:auto;
    margin-bottom:30px;
    width: 600px;
}
.container {
  width: 450px;
  padding: 5% 5% 5% 5%;
  border-collapse: separate;
  
}
.v-text-field, .v-textarea{
    font-family: 'Sunflower', sans-serif;
}

.selectSeatBtn{
  margin-left: 20%;
   margin-top: 10%

}
.listIcon{
  color:#d1a908;
  
}
.listBtn{

   margin-top: 10%;

}


</style>
