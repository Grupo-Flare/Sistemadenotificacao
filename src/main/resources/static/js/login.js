// function to set a given theme/color-scheme
function setTheme(themeName) {
    localStorage.setItem('theme', themeName);
    document.documentElement.className = themeName;
}
// function to toggle between light and dark theme
function toggleTheme() {
    let change = document.getElementById("change_theme");

    if (localStorage.getItem('theme') === 'theme-dark') {
        setTheme('theme-light');
        change.src = "/images/dark_mode.svg";
        change.setAttribute("fill", "black");
    } else {
        setTheme('theme-dark');
        change.src = "/images/light_mode.svg";
        change.setAttribute("fill", "white");
    }
}
// Immediately invoked function to set the theme on initial load
(function () {
    let change = document.getElementById("change_theme");
    if (localStorage.getItem('theme') === 'theme-dark') {
        setTheme('theme-dark');
        change.src = "/images/light_mode.svg";
    } else {
        setTheme('theme-light');
        change.src = "/images/dark_mode.svg";
    }
})();