// Mostrar nombre del usuario
const nombreUsuario = localStorage.getItem("nombreUsuario") || "user";
document.getElementById("welcome").textContent = `¡Bienvenido, ${nombreUsuario}!`;

// Modo oscuro
document.getElementById("dark-toggle").addEventListener("click", () => {
  document.body.classList.toggle("dark");
});

// Cambiar idioma (y traducir todo)
document.getElementById("lang-select").addEventListener("change", function () {
  const lang = this.value;
  const welcome = document.getElementById("welcome");
  const logout = document.getElementById("logout");
  const darkToggle = document.getElementById("dark-toggle");

  const formTitle = document.getElementById("form-title");
  const labelNombre = document.getElementById("label-nombre");
  const labelFecha = document.getElementById("label-fecha");
  const guardarBtn = document.getElementById("guardarBtn");
  const cancelarBtn = document.getElementById("cancelarFormulario");

  if (lang === "en") {
    welcome.textContent = `Welcome, ${nombreUsuario}!`;
    logout.textContent = "LOG OUT";
    darkToggle.textContent = "🌙 Dark Mode";

    formTitle.textContent = "Create Trip";
    labelNombre.textContent = "Trip name";
    labelFecha.textContent = "Trip date";
    guardarBtn.textContent = "Save";
    cancelarBtn.textContent = "← Cancel";
  } else {
    welcome.textContent = `¡Bienvenido, ${nombreUsuario}!`;
    logout.textContent = "SALIR";
    darkToggle.textContent = "🌙 Modo oscuro";

    formTitle.textContent = "Crear Viaje";
    labelNombre.textContent = "Nombre del viaje";
    labelFecha.textContent = "Fecha del viaje";
    guardarBtn.textContent = "Guardar";
    cancelarBtn.textContent = "← Cancelar";
  }
});

// Salir
document.getElementById("logout").addEventListener("click", () => {
  localStorage.clear();
  window.location.href = "index.html";
});

// Mostrar formulario
document.querySelector(".add-button").addEventListener("click", () => {
  document.getElementById("viaje-form").classList.remove("hidden");
});

// Cancelar formulario
document.getElementById("cancelarFormulario").addEventListener("click", () => {
  document.getElementById("viaje-form").classList.add("hidden");
});

// Enviar formulario
document.getElementById("crearViaje").addEventListener("submit", (e) => {
  e.preventDefault();
  const nombre = document.getElementById("nombreViaje").value;
  const fecha = document.getElementById("fechaViaje").value;

  alert(`Viaje creado:\nNombre: ${nombre}\nFecha: ${fecha}`);

  document.getElementById("viaje-form").classList.add("hidden");
  e.target.reset();
});
