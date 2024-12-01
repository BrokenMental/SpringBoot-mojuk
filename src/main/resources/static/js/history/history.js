const swiper = new Swiper('.swiper-container', {
    loop: true,
    autoplay: false, /*{
        delay: 10000,
        disableOnInteraction: false, // 사용자가 상호작용 후에도 자동재생 유지
        pauseOnMouseEnter: true, // 마우스가 슬라이더 위에 있을 때 자동 이동 멈춤
    },*/

    pagination: {
        el: '.swiper-pagination',
        clickable: true,
        renderBullet: function (index, className) {
            const years = [2021, 2022, 2023];
            return `<span class="${className}">${years[index]}</span>`;
        },
    },

    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },

    scrollbar: {
        el: '.swiper-scrollbar',
    },

    spaceBetween: 10,
});

/* List.js */
const club_role = {
    0: '지도교수',
    1: '회장',
    2: '부회장',
    3: '학년부장',
    4: '회원'
}

const options = {
    valueNames: [ 'clubLevel', 'name', 'studentNumber' ],
    item: '<li>' +
        '<span class="clubLevel"></span>' +
        '<h3 class="name"></h3>' +
        '<p class="studentNumber"></p>' +
        '</li>'
};

//sample(level 1: 회장, 2: 부회장, 3: 학년부장, 4: 회원)
const values = [{
    name: 'A',
    studentNumber: '2012',
    clubLevel: club_role[1],
  },
  {
    name: 'B',
    studentNumber: '2013',
    clubLevel: club_role[2]
  },
  {
    name: 'C',
    studentNumber: '2016',
    clubLevel: club_role[4]
}];

const userList = new List('users', options, values);
//userList.add({name: "Gustaf Lindqvist", born: 1983});
/* List.js */