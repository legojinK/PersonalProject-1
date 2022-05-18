<template>
  <div>
    <member-manager-form :registerMembers="registerMembers" 
    :registerMemberAuths="registerMemberAuths" @submit="onSubmit" />
  </div>
</template>

<script>
import MemberManagerForm from "@/components/manager/MemberManagerForm.vue"
import { mapActions, mapState } from "vuex"


export default {
  name: "MemberManagerPage",
  components: {
    MemberManagerForm,
  },
  computed: {
    ...mapState(["registerMembers", "registerMemberAuths"]),
  },
  methods: {
    ...mapActions(["fetchRegisterMembers", "fetchRegisterMemberAuths"]),
  },
  mounted() {
    this.fetchRegisterMembers()
    this.fetchRegisterMemberAuths()
  
  },
}
</script>