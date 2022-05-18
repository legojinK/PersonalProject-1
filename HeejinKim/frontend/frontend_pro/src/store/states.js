export default{

members:[],
member:null,

registerMembers:[],
registerMember: null,
registerMemberAuths: [],
registerMemberAuth: null,

userInfo: JSON.parse(localStorage.getItem('userInfo')),
token: localStorage.getItem('token'),

//isAuth: false,(일단 이렇게 해놓고)
isAuth: true,

auth: null,
auths: [],

//커뮤니티 게시판
board: null,
boards: [],
boardComments:[],

center:null,
centers:[],
centerComments:[],

reservation:null,
reservations:[],


noticeBoards: [],
noticeBoard: null,


reservationIdLists:[],

bookInfo:[],
session: [],

}
