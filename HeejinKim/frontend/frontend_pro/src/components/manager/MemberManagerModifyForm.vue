<template>

  <v-container >
    <v-form @submit.prevent="onSubmit">
    <v-card  class="card">    

      <v-row justify="center">
        <v-col cols="12" class="title1"> Infomation</v-col>
      </v-row>

      <v-row>
         <v-col cols="3"> <v-icon class="listIcon" justify="center"> mdi-alpha-n-box-outline</v-icon></v-col>
        <v-col cols="7">
          <v-text-field class="date pt-8" rounded readonly  :value="registerMember.memberNo"/>
        </v-col>
      </v-row>


      <v-row>
       <v-col cols="3"> <v-icon class="listIcon" justify="center"> mdi-account-circle-outline</v-icon></v-col>
        <v-col cols="7">
          <v-text-field class="date pt-8" rounded readonly  :value="registerMember.userId"/>
        </v-col>
      </v-row>
       <v-row>
       <v-col cols="2"> <v-icon class="listIcon" justify="center"> mdi-email</v-icon></v-col>
        <v-col cols="10">
          <v-text-field class="date pt-8" rounded readonly :value="registerMember.email"/>
        </v-col>
      </v-row>
       <v-row>
       <v-col cols="2"> <v-icon class="listIcon" justify="center"> mdi-calendar-month</v-icon></v-col>
        <v-col cols="10">
          <v-text-field class="date pt-8" rounded readonly :value="registerMember.regDate"/>
        </v-col>
      </v-row>


      <v-row>
         <v-col cols="2"> <v-icon class="listIcon" color="red" justify="center"> mdi-check-bold</v-icon></v-col>
         <v-col col="10">
        <v-radio-group v-model="radioGroup" class="mt-8" row>
          <v-radio color="red" v-for="grades in gradesAuth" :key="grades" :label="`${grades}`" :value="grades" ></v-radio>
        </v-radio-group>
        </v-col>
      </v-row>


      <v-row justify="center">
      
        <v-btn class="updateBtn"  type="submit" color="red" dark>   <v-icon> mdi-check-circle-outline </v-icon> </v-btn>
     
      
      </v-row>  
      </v-card>
    </v-form>

   </v-container>
</template>



<script>

export default {
  name: "MemberManagerModifyForm",
  props: {
    registerMember: {
      type: Object,
      required: true,
    },
    registerMemberAuth: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {   
       userInfo: "",
      userAuth: "",

      radioGroup: "",
      gradesAuth: ["Individaul", "Manager"],
      memberNo: this.registerMember.memberNo,
    }
  },
  methods: {
    onSubmit() {
      const { radioGroup } = this
      const auth = radioGroup
      this.$emit("submit", { auth })
    },
    goList(){
      this.$router.push({name: 'MemberManagerPage'})

    }
  },
  
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
  background-color:#fdfaf6da;
  padding: 5% 7% 5% 7%;
  margin-left:auto;
  margin-right:auto;
  width:450px;
  height:630px;
  
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