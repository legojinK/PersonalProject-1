<template>

  <v-container justify="center">
    <v-card class="place">
      
    <v-row justify="center">
      <v-col cols="12" class="label">Libarary seat</v-col>            
    </v-row>

       <v-row justify="center">
          <v-date-picker v-model="picker" @dblclick:date="dblClick" color="grey lighten-2" width="310px"
           :landscape="landscape" class="date" :min="new Date().toISOString().substr(0, 10)"></v-date-picker>
        </v-row>
   
      
    <v-row justify="center">
      <v-col>
        <ul class="showcase">
          <li>
            <div class="seat_avail"></div>
            <small>Available</small>
          </li>

          <li>
            <div class="seat_selected"></div>
            <small>Selected</small>
          </li>

          <li>
            <div class="seat_sold"></div>
            <small>Sold</small>
          </li>
        </ul>
      </v-col>
    </v-row>
  

    <v-row>
      <v-col v-model="seatNumber">
        <div class="llabel"> selected seat :  {{seatNumber}}  </div>
       
      </v-col>
    </v-row>
       
    <v-row justify="center">
      <v-col>
        <table class="container1">
          
          <tbody>
            <tr v-for="(i, idx) in seatRows" :key="idx">
              <td v-for="(j, idx) in seatCols" :key="idx" >
                <button @click="test(String(apblist[i])+String(j),$event)"
                  v-bind:id="String(apblist[i]) +String(j)"
                  v-bind:ref= "String(i) +String(j)" 
                  class="seat">
                  {{ apblist[i] }}{{ j }}
                </button>
              </td>
            </tr>
          </tbody>
   
        </table>
       
      </v-col>
    </v-row> 

    <v-row>    
      <router-link :to="{ name: 'ReservationPage',params: { picker,seatNumber,id } }"  style=" text-decoration:none">
        <v-btn class="selectSeatBtn" dark> select </v-btn>
      </router-link>  
    </v-row>    
      </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'
import { mapActions } from 'vuex'

export default {

  name:'SeatForm',

  data() {
    return {
      seatNumber:[],
      picker: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      blackseat:[],    
      seatRows: 3,
      seatCols: 7,
      reservation:'',
      apblist:['','A','B','C','D','E','F','G','H'],
      landscape: true,
      
    }
  }, 
  props: {
    reservations: {
      type: Array
    }
  },
  created(){
    this.id=this.$store.state.session.userId;
     setTimeout(this.dblClick, 1000);
      //this.reservation = this.$store.state.reservations[0]
  },
  mounted(){
    
      if (this.$store.state.session != null) {
      this.loginAuth = this.$store.state.auth.auth
    } else {
      alert("please login");
      this.$router.push("/Home");
    }
  },

  methods: {

     ...mapActions(['fetchReservationList','fetchReservation']),

     test(seatNum,event) {

      this.seatNumber = seatNum 
       if (event.currentTarget.style.background == "goldenrod") {
          event.currentTarget.style = "background:";
       }
       

        else if(event.currentTarget.style.background != "black"){
        event.currentTarget.style = "background:goldenrod"
       
      }

    },
    dblClick() {
      if (this.$store.state.reservations !== "" || this.$store.state.reservations == "" ){
        for(let i = 0; i < this.$store.state.reservations.length ; i++){

          var mm = this.$store.state.reservations[i].seatNumber
          document.getElementById(mm).style = "background:rgba(128, 128, 128, 0.199)"
          var target =document.getElementById(mm)

          target.disabled = false
        }
      }
    const{picker}=this
     axios.get(`http://localhost:7777/reservation/reservationList/${picker}`)
        .then( res => {

          console.log(res.data)
          var length = res.data.length

          for(let j = 0; j < length ; j++){

            console.log(j)
            
            var nn = res.data[j].seatNumber

            document.getElementById(nn).style = "background:black"

            
            //선택된 좌석 비활성화

            var target = document.getElementById(nn)
            
            target.disabled = true


          }
          this.$store.state.reservations = res.data
          
        })
        .catch(function (error) {
        console.log(error);
        })
      
     
    }
    

  }

}
</script>

<style scoped>

@import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Poiret+One&family=Sunflower:wght@300&family=Ubuntu:wght@300&display=swap");
@import url("https://fonts.googleapis.com/css?family=Arvo");


.llabel{
  font-family: 'Poiret One', cursive;
  font-size: 20px;
  margin-right:5%;
  text-align: center;
  padding-top: 50px; 
  
}
.label{
  font-family: 'Poiret One', cursive;
  font-size: 55px;
  font-weight: 400;
  margin-right:5%;
  text-align: center;
  padding-top: 25px;
  color:#d1a908;  

}

.place{
    background-color: #f8f8f8;
    padding: 5% 10% 5% 10%;
    margin-top:30px;
    margin-left:auto;
    margin-right:auto;
    margin-bottom:30px;
    width: 700px;
}
.date{
  margin-right: 14%;
  margin-top: 20%;
  margin-bottom: 10%;

}
.container1 {
  
  padding: 5% 7% 5% 5%;
  border-collapse: separate;
  
  
}
.v-text-field, .v-textarea{
    font-family: 'Sunflower', sans-serif;
}
.seat {
  color: #777;
  background-color:rgba(128, 128, 128, 0.199);
  height:40px;
  width: 45px;
  margin: 3px;
  font-size: 13px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  
}
.seat.avail{
  height:30px;
  width: 36px;
  background-color:rgba(128, 128, 128, 0.199);
}
.seat.selected {
  background-color: goldenrod;
  height:30px;
  width: 36px;
}
.seat.sold {
  background-color: black;
  height:30px;
  width: 36px;
}
.seat:nth-of-type(2) {
  margin-right: 18px;
}
.seat:nth-last-of-type(2) {
  margin-left: 18px;
}
.seat:not(.sold):hover {
  cursor: pointer;
  transform: scale(1.2);
}
.showcase .seat:not(.sold):hover {
  cursor: default;
  transform: scale(1);
}
.showcase {
  background: rgba(0, 0, 0, 0.1);
  padding: 10px 10px;
  border-radius: 5px;
  color: #777;
  list-style-type: none;
  display: flex;
  width: 420px;
 
  justify-content: space-between;
}
.showcase li {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 10px;
}
.showcase li small {
  margin-left: 2px;
}
.rrow {
  display: flex;
  height:300px;
}
.selectSeatBtn{
  margin-top: 5%;
  margin-left: 170px;  
}
.infos{
  grid-template-rows: repeat(300,50fr);
}

.seat_avail{
   background-color:rgba(128, 128, 128, 0.199);
  height:30px;
  width: 36px;
  margin: 3px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}
.seat_selected {
  background-color: goldenrod;
 height:30px;
  width: 36px;
  margin: 3px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}
.seat_sold {
  background-color: black;
  height:30px;
  width: 36px;
  margin: 3px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

</style>

