const swiper = new Swiper('.swiper-container', {
    loop: true,
    autoplay: {
        delay: 10000,
        disableOnInteraction: false, // 사용자가 상호작용 후에도 자동재생 유지
        pauseOnMouseEnter: true, // 마우스가 슬라이더 위에 있을 때 자동 이동 멈춤
    },

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
    { id: 1, name: "CEO" },
    { id: 2, pid: 1, name: "Vice President 1" },
    { id: 3, pid: 1, name: "Vice President 2" },
    { id: 4, pid: 2, name: "Manager 1" },
    { id: 5, pid: 2, name: "Manager 2" },
    { id: 6, pid: 3, name: "Manager 3" }
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
    nodeMouseClick: OrgChart.action.none
});
