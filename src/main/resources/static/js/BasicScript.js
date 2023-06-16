$(document).ready(function() {

    // rozwijanie menu
    $('.dropdown-toggle').click(function() {
        var menu = $(this).parent().find('.dropdown-menu');
        menu.toggleClass('show');
    });

    // chowanie rozwiniętego menu
    $('.dropdown').on('hide.bs.dropdown', function () {
        var menu = $(this).find('.dropdown-menu');
        menu.removeClass('show');
    });

    // załadowanie wybranego elementu
    $(document).on('click', '.dropdown-menu a', function(event) {
        event.preventDefault();

        var pageName = $(this).attr('data-page');
        if (pageName) {
            loadPage(pageName);
            history.pushState({}, '', '/' + pageName);
        }
    });
});

function loadPage(pageName) {
    $.ajax({
        url: '/' + pageName,
        success: function(data) {
            $('#content').html(data);
            $('.dropdown-menu').removeClass('show');
            if (pageName === 'pages/filmPosters') {
                loadFilmPosters();
            }
        }
    });
}

window.onpopstate = function(event) {
    var pageName = window.location.pathname.substring(1);

    if (pageName === '') {
        pageName = 'pages/homePage';
    }
    loadPage(pageName);
};

function loadTopUpWallet() {

    fetch('/users/topUpWallet')
        .then(response => response.text())
        .then(html => {
            document.getElementById('content').innerHTML = '<br>' + html;
            document.getElementById('submitBtn').addEventListener('click', submitTopUp);
        })
        .catch(error => {
            console.log('Wystąpił błąd:', error);
        });
}

function submitTopUp(event) {
    event.preventDefault();

    var form = document.getElementById('topUpForm');
    var formData = new FormData(form);

    fetch('/users/topUpWallet', {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(responseHtml => {
            document.getElementById('content').innerHTML = '<br>' + responseHtml;
        })
        .catch(error => {
            console.log('Wystąpił błąd:', error);
        });
}

function loadUserData() {

    fetch('/users/getData')
        .then(response => response.text())
        .then(html => {
            document.getElementById('content').innerHTML = '<br>' + html;
        })
        .catch(error => {
            console.log('Wystąpił błąd:', error);
        });
}

function loadTodayScreenings() {
    fetch('/screenings/showToday')
        .then(response => response.text())
        .then(html => {
            document.getElementById('content').innerHTML = '<br>' + html;

            var checkBtns = document.getElementsByClassName('checkBtn');
            for (var i = 0; i < checkBtns.length; i++) {
                checkBtns[i].addEventListener('click', checkScreeningDetails);
            }
        })
        .catch(error => {
            console.log('Wystąpił błąd:', error);
        });
}

function loadShowAllScreenings() {
    fetch('/screenings/showAll')
        .then(response => response.text())
        .then(html => {
            document.getElementById('content').innerHTML = '<br>' + html;

            var checkBtns = document.getElementsByClassName('checkBtn');
            for (var i = 0; i < checkBtns.length; i++) {
                checkBtns[i].addEventListener('click', checkScreeningDetails);
            }
        })
        .catch(error => {
            console.log('Wystąpił błąd:', error);
        });
}

function checkScreeningDetails(event) {
    event.preventDefault();

    var form = event.target.closest('form');
    var formData = new FormData(form);

    fetch('/screenings/details', {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(responseHtml => {
            document.getElementById('content').innerHTML = '<br>' + responseHtml;
        })
        .catch(error => {
            console.log('Wystąpił błąd:', error);
        });
}

function loadCreateScreeningForm() {
    fetch('/screenings/create')
        .then(response => response.text())
        .then(html => {
            document.getElementById('content').innerHTML = html;
            document.getElementById('submitBtn').addEventListener('click', submitForm);
        })
        .catch(error => {
            console.log('Wystąpił błąd:', error);
        });
}

function submitForm(event) {
    event.preventDefault();

    var form = document.getElementById('screeningForm');
    var formData = new FormData(form);

    fetch('/screenings/create', {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(responseHtml => {
            document.getElementById('content').innerHTML = '<br>' + responseHtml + '<br><br><button onclick="loadCreateScreeningForm()">OK</button>';
        })
        .catch(error => {
            console.log('Wystąpił błąd:', error);
        });
}

function logout() {
    fetch('/logout', { method: 'POST' })
        .then(() => {
            window.location.href = '/'; // Przekierowanie do strony logowania
        })
        .catch(error => {
            console.log('Wystąpił błąd:', error);
        });
}

function loadFilmPosters() {
    let currentPage = 1;
    let isLoading = false;
    let allMoviesLoaded = false;
    let moviesPerPage = 2;

    $(document).ready(function() {

        $(window).scroll(function() {
            if (!allMoviesLoaded && !isLoading && $(window).scrollTop() + $(window).height() >= $(document).height() - 200) {
                loadMoreMovies();
            }
        });

        loadMoreMovies();
    });

    function loadMoreMovies() {
        if (isLoading) {
            return;
        }

        isLoading = true;

        let response = {
            movies: [
                {
                    title: "Jaws",
                    year: "1975",
                    image: "/static/images/jaws.jpg"
                },
                {
                    title: "Star Wars: A New Hope",
                    year: "1977",
                    image: "/static/images/star_wars.jpg"
                },
                {
                    title: "Pulp Fiction",
                    year: "1994",
                    image: "/static/images/pulp_fiction.jpg"
                },
                {
                    title: "Alien",
                    year: "1979",
                    image: "/static/images/alien.jpg"
                },
                {
                    title: "Vertigo",
                    year: "1958",
                    image: "/static/images/vertigo.jpg"
                },
                {
                    title: "Anatomy of a Murder",
                    year: "1959",
                    image: "/static/images/anatomy_of_a_murder.jpg"
                },
                {
                    title: "The Silence of the Lambs",
                    year: "1991",
                    image: "/static/images/the_silence_of_the_lambs.jpg"
                },
                {
                    title: "Scarface",
                    year: "1983",
                    image: "/static/images/scarface.jpg"
                },
                {
                    title: "Back to the Future",
                    year: "1985",
                    image: "/static/images/back_to_the_future.jpg"
                }
            ]
        };

        let movies = response.movies;

        let movieList = $("#movieList");

        let startIndex = (currentPage - 1) * moviesPerPage;
        let endIndex = startIndex + moviesPerPage;
        let moviesToLoad = movies.slice(startIndex, endIndex);


        moviesToLoad.forEach(function(movie) {
            let listItem = $("<li>");
            let title = $("<h2>").text(movie.title);
            let year = $("<h4>").text("Rok powstania: " + movie.year);
            let image = $("<img>").attr("src", movie.image).addClass("movie-poster").attr("alt", movie.title);

            listItem.append(title, year, image);
            movieList.append(listItem);
        });

        isLoading = false;

        if (endIndex >= movies.length) {
            allMoviesLoaded = true;
            $(window).off("scroll");
        }

        currentPage++;
    }
}