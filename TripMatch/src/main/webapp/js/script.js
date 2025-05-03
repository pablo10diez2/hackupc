const btnLogin = document.getElementById('btn-login');
const btnRegister = document.getElementById('btn-register');
const formLogin = document.getElementById('form-login');
const formRegister = document.getElementById('form-register');
const langSelect = document.getElementById('lang-select');
const darkToggle = document.getElementById('dark-toggle');

// Alternar entre login y registro
btnLogin.addEventListener('click', () => {
  formLogin.classList.remove('hidden');
  formRegister.classList.add('hidden');
  btnLogin.classList.add('active');
  btnRegister.classList.remove('active');
});

btnRegister.addEventListener('click', () => {
  formRegister.classList.remove('hidden');
  formLogin.classList.add('hidden');
  btnRegister.classList.add('active');
  btnLogin.classList.remove('active');
});

// Convertir DNI/NIE a mayúsculas
document.getElementById('login-dni').addEventListener('input', function () {
  this.value = this.value.toUpperCase();
});

document.getElementById('reg-dni').addEventListener('input', function () {
  this.value = this.value.toUpperCase();
});

// Cambiar idioma de textos
langSelect.addEventListener('change', function () {
  const lang = langSelect.value;

  if (lang === 'es') {
    document.getElementById('btn-login').textContent = 'Iniciar Sesión';
    document.getElementById('btn-register').textContent = 'Crear Cuenta';
    document.getElementById('login-dni').placeholder = 'DNI/NIE';
    document.getElementById('login-pass').placeholder = 'Contraseña';
    document.getElementById('btn-entrar').textContent = 'Entrar';
    document.getElementById('reg-nombre').placeholder = 'Nombre';
    document.getElementById('reg-dni').placeholder = 'DNI/NIE';
    document.getElementById('reg-pass').placeholder = 'Contraseña';
    document.getElementById('btn-registrarse').textContent = 'Registrarse';
    darkToggle.textContent = '🌙 Modo oscuro';
  } else if (lang === 'en') {
    document.getElementById('btn-login').textContent = 'Login';
    document.getElementById('btn-register').textContent = 'Register';
    document.getElementById('login-dni').placeholder = 'ID Number';
    document.getElementById('login-pass').placeholder = 'Password';
    document.getElementById('btn-entrar').textContent = 'Enter';
    document.getElementById('reg-nombre').placeholder = 'Name';
    document.getElementById('reg-dni').placeholder = 'ID Number';
    document.getElementById('reg-pass').placeholder = 'Password';
    document.getElementById('btn-registrarse').textContent = 'Register';
    darkToggle.textContent = '🌙 Dark Mode';
  }
});

// Alternar modo oscuro
darkToggle.addEventListener('click', () => {
  document.body.classList.toggle('dark');
});

// Enviar login usando application/x-www-form-urlencoded
document.getElementById("form-login").addEventListener("submit", async function (e) {
  e.preventDefault();

  const dni = document.getElementById("login-dni").value;
  const contrasena = document.getElementById("login-pass").value;

  const formData = new URLSearchParams();
  formData.append("Dni_Usuario", dni);
  formData.append("contrasena_Usuario", contrasena); // <- CORRECTO

  try {
    const res = await fetch("LoginServlet", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      body: formData
    });

    const result = await res.text();

    if (result === "OK") {
      window.location.href = "dashboard.html";
    } else {
      alert(result);
    }
  } catch (err) {
    alert("Error al conectar con el servidor.");
  }
});
