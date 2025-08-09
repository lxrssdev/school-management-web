document.addEventListener("DOMContentLoaded", function () {
  const darkModeSwitch = document.getElementById("darkModeSwitch");

  // Verificar preferencia del sistema o guardada
  const savedTheme =
    localStorage.getItem("theme") ||
    (window.matchMedia("(prefers-color-scheme: dark)").matches
      ? "dark"
      : "light");

  // Aplicar tema guardado
  if (savedTheme === "light") {
    document.documentElement.setAttribute("data-theme", "light");
    darkModeSwitch.checked = true;
  }

  // Cambiar icono según el tema
  updateSwitchIcon(darkModeSwitch.checked);

  // Escuchar cambios en el interruptor
  darkModeSwitch.addEventListener("change", function () {
    if (this.checked) {
      document.documentElement.setAttribute("data-theme", "light");
      localStorage.setItem("theme", "light");
    } else {
      document.documentElement.removeAttribute("data-theme");
      localStorage.setItem("theme", "dark");
    }
    updateSwitchIcon(this.checked);
  });

  function updateSwitchIcon(isLight) {
    const icon = darkModeSwitch.nextElementSibling.querySelector("i");
    icon.className = isLight ? "bi bi-sun" : "bi bi-moon-stars";
  }
});
