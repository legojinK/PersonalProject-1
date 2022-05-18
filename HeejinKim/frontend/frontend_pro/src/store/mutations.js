import {
    FETCH_MEMBER_LIST,
    FETCH_MEMBER,

    FETCH_AUTH,
    FETCH_AUTH_LIST,
   
    //커뮤니티 게시판
    FETCH_BOARD,
    FETCH_BOARD_LIST,
    FETCH_BOARD_COMMENTS_LIST,
    FETCH_BOARD_ID_LISTS,

    //서비스센터 게시판

    FETCH_CENTER_COMMENTS_LIST,
    FETCH_CENTER,
    FETCH_CENTER_LIST,
    FETCH_CENTER_ID_LISTS,

    //예약 게시판
    FETCH_RESERVATION,
    FETCH_RESERVATION_LIST,
    FETCH_RESERVATION_ID_LISTS, 

    //공지사항게시판
    FETCH_NOTICE_BOARD_LIST,
    FETCH_NOTICE_BOARD,

    FETCH_REGISTER_MEMBERS,
    FETCH_REGISTER_MEMBER,
    FETCH_REGISTER_MEMBER_AUTHS,
    FETCH_REGISTER_MEMBER_AUTH,
    



}from './mutation-types'

export default {

    [FETCH_BOARD_LIST] (state,boards) {
        state.boards = boards;
    },
    [FETCH_BOARD] (state, board) {
        state.board = board
    },
    [FETCH_BOARD_COMMENTS_LIST] (state, boardComments) {
        state.boardComments = boardComments
    },
    [FETCH_BOARD_ID_LISTS] (state, boardIdLists) {
        state.boardIdLists = boardIdLists
    },

    
    [FETCH_CENTER_LIST] (state,centers) {
        state.centers = centers;
    },
    [FETCH_CENTER] (state, center) {
        state.center = center
    },
    [FETCH_CENTER_COMMENTS_LIST] (state, centerComments) {
        state.centerComments = centerComments
    },
    [FETCH_CENTER_ID_LISTS] (state, centerIdLists) {
        state.centerIdLists = centerIdLists
    },




    [FETCH_MEMBER_LIST] (state, members) {
        state.boards = members
    },
    [FETCH_MEMBER] (state, member) {
        state.board = member
    },
    [FETCH_AUTH] (state, auth) {
        state.auth = auth;
    },
    [FETCH_AUTH_LIST] (state, auths) {
        state.auths = auths;
    },

    [FETCH_RESERVATION] (state, reservation) {
        state.reservation = reservation
    },
    [FETCH_RESERVATION_LIST] (state, reservations) {
        state.reservations = reservations
    },

    [FETCH_RESERVATION_ID_LISTS] (state, reservationIdLists) {
        state.reservationIdLists = reservationIdLists
    },

    [FETCH_NOTICE_BOARD_LIST](state, noticeBoards){
        state.noticeBoards = noticeBoards
    },
    [FETCH_NOTICE_BOARD](state, noticeBoard){
        state.noticeBoard = noticeBoard
    },

    [FETCH_REGISTER_MEMBERS](state, registerMembers){
        state.registerMembers = registerMembers
    },
    [FETCH_REGISTER_MEMBER_AUTHS](state, registerMemberAuths){
        state.registerMemberAuths = registerMemberAuths
    },
    [FETCH_REGISTER_MEMBER](state, registerMember){
        state.registerMember = registerMember
    },
    [FETCH_REGISTER_MEMBER_AUTH](state, registerMemberAuth){
        state.registerMemberAuth = registerMemberAuth
    },


}