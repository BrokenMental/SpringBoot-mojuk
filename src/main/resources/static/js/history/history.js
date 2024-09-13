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

// 데이터 생성
const chartData = [
    { id: 1, name: "회장" },
    { id: 2, name: "지도교수" },
    { id: 3, pid: 1, name: "부회장", layout: OrgChart.mixed },
    { id: 4, pid: 1, name: "저학년부장", layout: OrgChart.mixed },
    { id: 5, pid: 1, name: "총무" },
    { id: 6, pid: 3, name: "회원" },
    { id: 7, pid: 3, name: "회원" },
    { id: 8, pid: 3, name: "회원" },
    { id: 9, pid: 3, name: "회원" },
    { id: 10, pid: 4, name: "회원" },
    { id: 11, pid: 4, name: "회원" },
    { id: 12, pid: 4, name: "회원" },
    { id: 13, pid: 4, name: "회원" },
    { id: 14, pid: 4, name: "회원" },
    { id: 15, pid: 4, name: "회원" },
    { id: 16, pid: 4, name: "회원" },
    { id: 17, pid: 4, name: "회원" }
];

// OrgChart 초기화
const chart = new OrgChart(document.getElementById("tree"), {
    nodes: chartData,
    nodeBinding: {
        field_0: "name"
    },
    // 옵션 설정 (커스터마이징 가능)
    layout: OrgChart.mixed,
    template: "diva",
    enableSearch: false,
    nodeMouseClick: OrgChart.action.none,
    mouseScrool: OrgChart.action.none,
    scaleInitial: 0.7
});
