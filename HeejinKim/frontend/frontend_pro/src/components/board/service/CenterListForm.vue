<template>
  <v-card class="cardStyle">
    
    <v-card-title>

      <h2>Service Center</h2>

      <v-spacer></v-spacer>

      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"      
        label="Search"
        single-line
        hide-details
        class="shrink pr-15"
        color="#e3c832">
      </v-text-field>
      

     

    </v-card-title>
    
    <v-data-table 
      @click:row="showBoard"
      :headers="headers"
      :items="centers"
      :search="search"
      :page.sync="page"
      :items-per-page="itemsPerPage"
      class="table"
      hide-default-footer
      @page-count="pageCount = $event"
    >
    <template v-slot:[`item.title`]="{ item }">
    <v-icon color="grey" size="15px"> mdi-lock-outline</v-icon>&nbsp;&nbsp; {{ item.title }} <span style="color:#D50000;"> [{{ item.comments.length }}] </span>
    </template>
    
    
    
    
    
    
    
    </v-data-table>

    <div class="text-center pt-2">
      <v-pagination class="grey lighten-4"
      v-model="page"
      :length="pageCount"
      ></v-pagination>
    </div>

  </v-card>
  
</template>

<script>

//import axios from 'axios'

export default {

  name: "CenterListForm",
  props: {
    centers: {
      type: Array
    }
  },
  data () {
    return {
      search: '',
      page: 1,
      pageCount: 0,
      itemsPerPage: 9,
      checkId : this.$store.state.session.userId,
      
      headers: [
        {text:'글 번호', align: 'center', value: 'centerNo', width:'10%'}, 
        {text: '제목', align: 'center', value: 'title', width: "60%"},
        {text: '작성자', align: 'center',value: 'writer', width:'15%'},
        {text: '등록일자',align: 'center', value: 'updDate',width:'15%'},
      ],
    }
  },
   created() {
    if (this.$store.state.session != null) {
      this.loginAuth = this.$store.state.auth.auth
    } else {
      alert("please login");
      this.$router.push("/Home");
    }
  },
  methods: {
    showBoard(event, idx) {
      if(idx.item.writer == this.checkId || this.loginAuth == 'Manager'){
          this.$router.push({ name: 'CenterRead',  params: { centerNo:String(idx.item.centerNo) } })
      }
      else {
        alert('You cannot read this content')
      this.$router.push('/serviceBoard') 
      }
    }
    
    },
  }

</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Poiret+One&family=Sunflower:wght@300&display=swap');


.table{
  cursor: pointer;
  font-family: 'Ubuntu', sans-serif;

}

.cardStyle{

 font-family: 'Ubuntu',  sans-serif;
  margin: 30px;
  width:90%;
}
.v-data-table::v-deep th {
  font-size: 15px !important;
  font-family: 'Sunflower', sans-serif; 

}


</style>
