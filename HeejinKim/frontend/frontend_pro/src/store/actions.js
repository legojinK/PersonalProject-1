import{
    FETCH_BOARD,
    FETCH_BOARD_LIST,
    FETCH_BOARD_COMMENTS_LIST,
    FETCH_BOARD_ID_LISTS,

    FETCH_RESERVATION,
    FETCH_RESERVATION_LIST,
    FETCH_RESERVATION_ID_LISTS,

    FETCH_CENTER_LIST,
    FETCH_CENTER,
    FETCH_CENTER_COMMENTS_LIST,
    FETCH_CENTER_ID_LISTS,

    FETCH_MEMBER,

    FETCH_NOTICE_BOARD_LIST,
    FETCH_NOTICE_BOARD,

    FETCH_REGISTER_MEMBERS,
    FETCH_REGISTER_MEMBER,

    FETCH_REGISTER_MEMBER_AUTHS,
    FETCH_REGISTER_MEMBER_AUTH,




} from './mutation-types'

import Vue from "vue";
import axios from 'axios'
import cookies from 'vue-cookies'
//import router from '@/router'

Vue.use(cookies)

export default {

     fetchMember({commit}, memberNo) {
       return axios.get(`http://localhost:7777/jpaMember/${memberNo}`)
       .then(res => {
           commit(FETCH_MEMBER, res.data)
       })
   },

    fetchBoardList ({ commit }) {
        return axios.get(`http://localhost:7777/board/community/list`)
            .then((res) => {
                commit(FETCH_BOARD_LIST, res.data)
            })
    },

    fetchBoard ({ commit }, boardNo) {
        return axios.get(`http://localhost:7777/board/community/${boardNo}`)
            .then((res) => {
                commit(FETCH_BOARD, res.data)
            })
    },

    fetchBoardCommentsList({ commit }, boardNo ) {
        return axios.get(`http://localhost:7777/boardComments/list/${boardNo}`)
                .then((res) => {
                    commit(FETCH_BOARD_COMMENTS_LIST, res.data)
                })
    },
    fetchBoardIdLists ({commit}, writer) {
        return axios.get(`http://localhost:7777/board/community/list/${writer}`)
                .then((res) => {
                    commit(FETCH_BOARD_ID_LISTS, res.data)
                })
    },


    //예약 게시판 
     fetchReservation ({ commit }, reservationNo) {
        return axios.get(`http://localhost:7777/reservation/checkBooking/${reservationNo}`)
            .then((res) => {
                commit(FETCH_RESERVATION, res.data)
            })
    },
   
    fetchReservationList ({ commit },picker) {
        return axios.get(`http://localhost:7777/reservation/reservationList/${picker}`)
            .then((res) => {
                commit(FETCH_RESERVATION_LIST, res.data)
            })
    },

    fetchReservationIdLists ({commit}, id) {
        return axios.get(`http://localhost:7777/reservation/list/${id}`)
                .then((res) => {
                    commit(FETCH_RESERVATION_ID_LISTS, res.data)
                })
    },

    //서비스 센터 게시판
    fetchCenterList ({ commit }) {
        return axios.get(`http://localhost:7777/board/center/list`)
            .then((res) => {
                commit(FETCH_CENTER_LIST, res.data)
            })
    },

    fetchCenter ({ commit }, centerNo) {
        return axios.get(`http://localhost:7777/board/center/${centerNo}`)
            .then((res) => {
                commit(FETCH_CENTER, res.data)
            })
    },
    
    fetchCenterCommentsList({ commit }, centerNo ) {
        return axios.get(`http://localhost:7777/centerComments/list/${centerNo}`)
                .then((res) => {
                    commit(FETCH_CENTER_COMMENTS_LIST, res.data)
                })
    },
    fetchCenterIdLists ({commit}, writer) {
        return axios.get(`http://localhost:7777/board/center/list/${writer}`)
                .then((res) => {
                    commit(FETCH_CENTER_ID_LISTS, res.data)
                })
    },


    //공지사항 게시판 
    fetchNoticeBoardList({commit}) {
        return axios.get("http://localhost:7777/noticeBoard/list")
        .then((res)=>{
            commit(FETCH_NOTICE_BOARD_LIST, res.data)
        })
    },
    fetchNoticeBoard({commit},boardNo) {
        return axios.get(`http://localhost:7777/noticeBoard/${boardNo}`)
        .then((res)=>{
            commit(FETCH_NOTICE_BOARD,res.data)
        })
    },

    //등록된 회원 리스트
    fetchRegisterMembers({commit}) {
        return axios.get("http://localhost:7777/memberManage/list")
        .then((res)=>{
            commit(FETCH_REGISTER_MEMBERS, res.data)
        })
    },
    fetchRegisterMember({commit},memberNo) {
        return axios.get(`http://localhost:7777/memberManage/member/${memberNo}`)
        .then((res)=>{
            commit(FETCH_REGISTER_MEMBER,res.data)
        })
    },
    fetchRegisterMemberAuths({commit}) {
        return axios.get("http://localhost:7777/memberManage/authList")
        .then((res)=>{
            commit(FETCH_REGISTER_MEMBER_AUTHS, res.data)
        })
    },
    fetchRegisterMemberAuth({commit},memberNo) {
        return axios.get(`http://localhost:7777/memberManage/memberAuth/${memberNo}`)
        .then((res)=>{
            commit(FETCH_REGISTER_MEMBER_AUTH,res.data)
        })
    },




 
}