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
            //const years = [2021, 2022, 2023];
            return `<span class="${className}"></span>`;
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
    /*
    on: {
        //swiper 버튼 클릭 시 실행 될 이벤트
        slideChange: function () {
            const activeIndex = this.realIndex;
            console.log(activeIndex);
            //const selectedYear = years[activeIndex];
            //fetchDataForYear(selectedYear);
        },
    },
    */
});

const originalSlides = Array.from(document.querySelectorAll('.swiper-slide')).map(slide => {
  return {
    content: slide.innerHTML
  };
});

const manageYearList = async () => {
    try {
        const response = await axios.get('/history/manageYears');
        const years = response.data.map(yearList => yearList.manageYear);

        // Swiper 슬라이드 업데이트
        swiper.removeAllSlides();
        years.forEach((year, index) => {
            swiper.appendSlide(`<div class="swiper-slide">Slide ${index + 1}</div>`);

            // 기존에 저장된 자식 요소가 있다면 복원
            if (originalSlides[index]) {
                const newSlide = swiper.slides[swiper.slides.length - 1];
                newSlide.innerHTML = originalSlides[index].content;
            }
        });

        // 페이지네이션 업데이트
        //swiper.pagination.render();

        // 루프 모드 다시 생성
        swiper.update();
        //swiper.loopCreate();

        swiper.pagination.update();

        // 페이지네이션 불릿에 연도 데이터 추가
        const bullets = document.querySelectorAll('.swiper-pagination .swiper-pagination-bullet');
        bullets.forEach((bullet, index) => {
            bullet.textContent = years[index];
        });

        clubDisplay();
    } catch (err) {
        console.error('데이터를 가져오는 중 오류가 발생했습니다:', err);
    }
}

manageYearList();

/* List.js */
const clubDisplay = () => {
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
            '<div>' +
            '<i class="fa-brands fa-git-alt"></i>' +
            '<i class="fa-solid fa-house"></i>' +
            '</div>' +
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

    const userList = new List('usersContainer', options, values);
    //userList.add({name: "Gustaf Lindqvist", born: 1983});

    const usersContainer = document.getElementById('usersContainer');
    usersContainer.addEventListener('click', function(e) {
        //git 버튼
        if(e.target.classList.contains('fa-git-alt')) {
            const inli = e.target.closest('li');
            const clubLevel = inli.querySelector('.clubLevel').textContent;
            const name = inli.querySelector('.name').textContent;
            const studentNumber = inli.querySelector('.studentNumber').textContent;

            console.log(`${clubLevel} ${name} ${studentNumber}`);
        }

        //홈페이지 버튼
        if(e.target.classList.contains('fa-house')) {
            const inli = e.target.closest('li');
            const clubLevel = inli.querySelector('.clubLevel').textContent;
            const name = inli.querySelector('.name').textContent;
            const studentNumber = inli.querySelector('.studentNumber').textContent;

            console.log(`${clubLevel} ${name} ${studentNumber}`);
        }
    });
}
/* List.js */

