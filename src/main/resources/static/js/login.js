// function to set a given theme/color-scheme
function setTheme(themeName) {
    document.getElementById("login").className = themeName;
    document.documentElement.className = themeName;
}
// function to toggle between light and dark theme
function toggleTheme() {
    let change = document.getElementById("change_theme");
    let emge_dhc = document.getElementById("emge_dhc");

    if (document.getElementById("login").className === 'theme-dark') {
        setTheme('theme-light');
        change.innerHTML = "dark_mode";
        emge_dhc.src = "/images/logo_emge_dhc.png";
    } else {
        setTheme('theme-dark');
        change.innerHTML = "light_mode";
        emge_dhc.src = "/images/logo_emge_dhc_branco.png";
    }
}
// Immediately invoked function to set the theme on initial load
(function () {
    let change = document.getElementById("change_theme");
    if (document.getElementById("login").className === 'theme-dark') {
        setTheme('theme-dark');
        change.innerHTML = "light_mode";
        emge_dhc.src = "/images/logo_emge_dhc_branco.png";
    } else {
        setTheme('theme-light');
        change.innerHTML = "dark_mode";
        emge_dhc.src = "/images/logo_emge_dhc.png";
    }
})();