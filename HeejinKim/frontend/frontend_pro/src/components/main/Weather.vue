<template>
  <v-container class="wee" >
   
      
        <v-card class="color">
    
        <v-card-title class="text-center justify-center py-6">
          <h1 class="font-weight-bold grey--text"> Weather </h1>
        </v-card-title>  

        <v-card-title class="text-right justify-center ">
          <h5 class="date"> {{ dateBuilder() }}</h5>
        </v-card-title>

        <v-tabs v-model="tab" color="grey" grow>
          <v-tab v-for="city in cities" :key="city" @click="selectCity(city)">
            {{ city }}
          </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item v-for="city in cities" :key="city" >
            <v-card  class="color">
              <v-card-title class="headline gray--text font-weight-bold justify-center ma-5">
                {{ city }}
              </v-card-title>

              <v-list class="color">  
                <v-list-item class="text-center">
                  <v-list-item-title class="title font-weight-bold gray--text">
                  
                <v-img v-if="`${icon}`=='01d'" class= "sun" :src="require(`@/assets/icon/${icon}.png`)"></v-img>
                <v-img v-if="`${icon}`!='01d'" class= "nosun" :src="require(`@/assets/icon/${icon}.png`)"></v-img>
                  </v-list-item-title>
                </v-list-item> 
                <v-list-item class="text-center">
                  <v-list-item-title class="title font-weight-bold gray--text">
                    {{ weather }}
                  </v-list-item-title>
                </v-list-item> 

                <v-list-item class="text-center">
                  <v-list-item-title class="gray--text">
                    <v-icon>mdi-thermometer</v-icon>
                    {{ temp }} ℃
                  </v-list-item-title>
                </v-list-item>

                <v-list-item class="text-center">
                  <v-list-item-title class="gray--text">
                    <v-icon>mdi-water-percent</v-icon>
                    {{ humidity }} %
                  </v-list-item-title>
                </v-list-item>  

              </v-list>
            </v-card>
          </v-tab-item>
        </v-tabs-items> 
      </v-card>
 
  
    
 

</v-container>
</template>


<script>

import axios from 'axios'

export default {
  data () {
    return {

      temp: null,
      humidity:'' ,
      weather:'' ,
      tab: null,
      city: 'Seoul',
      icon: '02d',
     
      
      
      cities: [
        'Seoul',
        'Busan',
        'Incheon',
        'Gwangju',
        'Daegu',
        'Daejeon',
        'Ulsan',
        'Jeju',
      ],
      
    }
  },beforeMount () {

    this.selectCity(this.city)
  },
  
  methods: {

    selectCity (city) {
      
      
      const key = 'e39e4f8d4566233ee9cc271d7c918afd'
      
      axios.get(` https://weathertest13.herokuapp.com/https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${key}`)
      
      //axios.get(`${this.heroku}${this.requestLink}weather?q=${city}&units=metric&appid=${key}`) https://weathertest13.herokuapp.com/

        .then(res => {
      
          console.log(res.data) 
          const list = res.data
          this.city = city
          this.temp = Math.round(list.main.temp)
          this.humidity = list.main.humidity
          this.icon = list.weather[0].icon
          this.weather = list.weather[0].main
           
          
        })
       
        .catch(weatherError=>{
            console.log(weatherError)
        })

    
  
    },
    selectIcon(){
      let iconurl = "http://openweathermap.org/img/w/" + this.icon + ".png"
      this.iconurl = iconurl
    },
    dateBuilder () {
      const d = new Date();
      const months = ["Jan",
                      "Feb",
                      "Mar",
                      "Apr",
                      "May",
                      "Jun",
                      "Jul",
                      "Aug",
                      "Sep",
                      "Oct",
                      "Nov",
                      "Dec"];
      const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

      const day = days[d.getDay()];
      const date = d.getDate();
      const month = months[d.getMonth()];
      const year = d.getFullYear();

      return `${day} ${date} ${month} ${year}`;
    }
  }
}
</script>


<style scoped>
.wee{
  margin-left:170px
}
.gray--text {
  color:rgb(100, 100, 100)
}
.v-application .ma-5 {
     margin: 0px  !important; 
}
.color{
   background: rgb(248, 246, 246);
   font-family: 'Poiret One', cursive;
   margin: 5% 5% 5% 5%;
}

.weather{
  
  background: rgb(248, 246, 246);
  font-family: 'Poiret One', cursive;
}

.sun {
  animation: rotate 12s infinite linear; 
  position: relative;
  display: inline-block;
  width: 7em;
  height: 7em;
  font-size: 1em;

}.nosun{
  position: relative;
  display: inline-block;
  width: 7em;
  height: 7em;
  font-size: 1em;

}

@keyframes rotate {
  from {
    -webkit-transform: rotate(0deg);
    -o-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  to {
    -webkit-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}

</style>