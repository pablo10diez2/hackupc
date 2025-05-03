const categorias = [
    "Seguridad",
    "Cultura/Arte",
    "Comida",
    "Tiempo",
    "Fiesta",
    "Deporte",
    "Naturaleza"
  ];
  
  let categoriaEstrellas = {};
  const personas = [];
  
  const abrirFormulario = document.getElementById("abrirFormulario");
  const formulario = document.getElementById("formularioPersona");
  const cerrarFormulario = document.getElementById("cerrarFormulario");
  const listaPersonas = document.getElementById("lista-personas");
  
  // Mostrar formulario
  abrirFormulario.addEventListener("click", () => {
    formulario.classList.remove("hidden");
    generarCategorias();
  });
  
  // Cerrar formulario
  cerrarFormulario.addEventListener("click", () => {
    formulario.classList.add("hidden");
    document.getElementById("form-persona").reset();
  });
  
  // Generar estrellas para cada categoría
  function generarCategorias() {
    const contenedor = document.getElementById("categorias-container");
    contenedor.innerHTML = "";
    categoriaEstrellas = {};
  
    categorias.forEach((cat) => {
      const block = document.createElement("div");
      block.className = "categoria-block";
  
      const label = document.createElement("label");
      label.textContent = cat;
      block.appendChild(label);
  
      const starsDiv = document.createElement("div");
      starsDiv.className = "stars";
  
      for (let i = 1; i <= 5; i++) {
        const star = document.createElement("span");
        star.innerHTML = "☆";
        star.dataset.value = i;
        star.classList.add("star");
  
        star.addEventListener("click", () => {
          categoriaEstrellas[cat] = i;
          actualizarEstrellas(starsDiv, i);
        });
  
        starsDiv.appendChild(star);
      }
  
      block.appendChild(starsDiv);
      contenedor.appendChild(block);
    });
  }
  
  function actualizarEstrellas(container, cantidad) {
    const stars = container.querySelectorAll(".star");
    stars.forEach((s, index) => {
      s.innerHTML = index < cantidad ? "★" : "☆";
    });
  }
  
  // Guardar persona
  document.getElementById("form-persona").addEventListener("submit", (e) => {
    e.preventDefault();
  
    const persona = {
      nombre: document.getElementById("nombrePersona").value,
      origen: document.getElementById("origenPersona").value,
      presupuesto: parseInt(document.getElementById("presupuestoPersona").value),
      preferencias: { ...categoriaEstrellas },
    };
  
    personas.push(persona);
    mostrarPersona(persona);
  
    e.target.reset();
    formulario.classList.add("hidden");
    document.getElementById("categorias-container").innerHTML = "";
  });
  
  // Mostrar personas (con 0 estrellas si no hay)
  function mostrarPersona(persona) {
    const li = document.createElement("li");
    li.innerHTML = `
      <strong>${persona.nombre}</strong> 
      <button class="ver-detalles">info</button>
    `;
  
    const detalles = document.createElement("div");
    detalles.classList.add("detalles-persona");
    detalles.innerHTML = `
      <p><strong>Origen:</strong> ${persona.origen}</p>
      <p><strong>Presupuesto:</strong> €${persona.presupuesto}</p>
      <p><strong>Preferencias:</strong></p>
      <ul>
        ${categorias.map(cat => {
          const val = persona.preferencias[cat] || 0;
          return `<li>${cat}: ${val}★</li>`;
        }).join("")}
      </ul>
    `;
    detalles.style.display = "none";
  
    li.appendChild(detalles);
  
    li.querySelector(".ver-detalles").addEventListener("click", () => {
      const abiertos = document.querySelectorAll(".detalles-persona");
      abiertos.forEach((d) => {
        if (d !== detalles) d.style.display = "none";
      });
  
      detalles.style.display = detalles.style.display === "none" ? "block" : "none";
    });
  
    listaPersonas.appendChild(li);
  }
  
  // SALIR
  document.getElementById("cancelar").addEventListener("click", () => {
    window.location.href = "dashboard.html";
  });
  
  // MODO OSCURO
  const darkToggle = document.getElementById("dark-toggle");
  darkToggle.addEventListener("click", () => {
    document.body.classList.toggle("dark-mode");
  
    if (document.body.classList.contains("dark-mode")) {
      darkToggle.innerHTML = "☀️ Modo claro";
    } else {
      darkToggle.innerHTML = "🌙 Modo oscuro";
    }
  });
  
  // CAMBIO DE IDIOMA
  const langSelect = document.getElementById("lang-select");
  langSelect.addEventListener("change", () => {
    const lang = langSelect.value;
    const translations = {
      es: {
        welcome: "¡Vamos a completar tu viaje!",
        addTitle: "Añadir persona",
        save: "Guardar persona",
        cancel: "← Cancelar",
        salir: "SALIR",
        personas: "Personas añadidas",
        nombre: "Nombre",
        origen: "Origen",
        presupuesto: "Presupuesto (€)",
        categorias: "Categorías (0 a 5 estrellas)",
      },
      en: {
        welcome: "Let's complete your trip!",
        addTitle: "Add person",
        save: "Save person",
        cancel: "← Cancel",
        salir: "EXIT",
        personas: "People added",
        nombre: "Name",
        origen: "Origin",
        presupuesto: "Budget (€)",
        categorias: "Categories (0 to 5 stars)",
      }
    };
  
    const t = translations[lang];
  
    document.getElementById("welcome").textContent = t.welcome;
    document.querySelector(".form-container h2").textContent = t.addTitle;
    document.querySelector("#form-persona button[type='submit']").textContent = t.save;
    document.getElementById("cerrarFormulario").textContent = t.cancel;
    document.getElementById("cancelar").textContent = t.salir;
    document.querySelector(".derecha h2").textContent = t.personas;
  
    document.querySelector("label[for='nombrePersona']").textContent = t.nombre;
    document.querySelector("label[for='origenPersona']").textContent = t.origen;
    document.querySelector("label[for='presupuestoPersona']").textContent = t.presupuesto;
    document.querySelector("#form-persona label:nth-of-type(4)").textContent = t.categorias;
  });

  const ciudadesUnicas = ["Belgrade", "Bali", "Kandy", "Beirut", "Pekin", "Cracovia", "Atenas", 
    "Varna", "Marruecos", "Baku", "Belgrado", "Sousse", "Nueva Delhi", "Chisinau", "Hoi An", "Seattle", 
    "St. Petersburg", "Lausanne", "Valletta", "Hokkaido", "Rovaniemi", "Bruges", "Brno", "Budapest", "Oporto", 
    "Milan", "Cannes", "Denpasar", "Nueva York", "Split", "Muscat", "Puerto Rico", "Christchurch", "Phnom Penh", 
    "San Miguel de Allende", "La Haya", "Amritsar", "Amman", "Dubai", "Mumbai", "Tallin", "Helsinki", "Foz do Iguacu", 
    "Havana", "Manaos", "Khiva", "Rosario", "Niza", "La Paz", "Lyon", "Rio de Janeiro", "Sao Paulo", "Tbilisi", "Lviv", 
    "Burdeos", "Luxor", "Varsovia", "Faro", "La Habana", "Portofino", "Aix-en-Provence", "Ginebra", "Osaka", "Montreal", 
    "Mendoza", "Puno", "Lanzarote", "Tokio", "Kyoto", "Cape Town", "Rethymno", "Paraty", "Kiev", "Zurich", "Melbourne", 
    "Nairobi", "Puerto Vallarta", "Trinidad", "Bled", "Luxembourg", "Roma", "Riad", "Oslo", "Barcelona", "Jerusalem", 
    "Calgary", "Jerusalen", "Saint Petersburg", "Bratislava", "Cuenca", "Seul", "Arequipa", "Ciudad de Mexico", "Kioto", 
    "Yerevan", "Nagasaki", "Doha", "Cluj-Napoca", "Tallinn", "Taipéi", "Granada", "Kuala Lumpur", "Antigua", "Reykjavik", 
    "Lisboa", "Manama", "Sucre", "Pyongyang", "Bansko", "Bergen", "Matera", "Quebec", "Bariloche", "Almaty", "Miami", "Cairo", 
    "San Petersburgo", "Rio", "Belem", "Estocolmo", "Praga", "Casablanca", "Kotor", "Yogyakarta", "Florence", "Monterrey", 
    "Tenerife", "Edimburgo", "Tulum", "Agadir", "Genoa", "Lusaka", "Las Vegas", "Georgetown", "Kansas City", "Abu Dhabi", 
    "Bangkok", "Santiago", "Lijiang", "Madrid", "Toronto", "Tangier", "Vancouver", "Hong Kong", "Ibiza", "Johor Bahru", 
    "Quito", "Siem Reap", "Moscu", "Cancun", "Amsterdam", "Damasco", "Santiago de Chile", "Riga", "Münich", "Nice", "Limerick", 
    "New Orleans", "Suzhou", "Buenos Aires", "Djerba", "Nanjing", "Paphos", "Aman", "Londres", "Peking", "Copenhague", 
    "Vientiane", "Mykonos", "Cuzco", "Porto", "Fes", "Vilna", "Ulaanbaatar", "Aberdeen", "Dublin", "Cartagena", "Shangai", 
    "Cartago", "Riyadh", "Palermo", "Girona", "Memphis", "San Sebastián", "Taichung", "Mar del Plata", "Trieste", "Tel Aviv", 
    "Oaxaca", "Berlin", "Medellin", "Phuket", "Toulouse", "Cusco", "Sidney", "Bogota", "Stockholm", "Sapporo", "Vilnius", 
    "Dubrovnik", "Estambul", "Saigon", "Frankfurt", "Napoles", "El Cairo", "Singapur", "Viena", "Tiflis", "Punta Cana", 
    "Zadar", "Marrakech", "Ubud", "Lima", "Colombo", "Edinburgh", "Montevideo", "Cork", "Yangon", "Krakow", "Chengdu", 
    "Macao", "Funchal", "Los Angeles", "Hvar", "Paris"]
  
  const inputOrigen = document.getElementById("origenPersona");
  const listaSugerencias = document.getElementById("autocomplete-list");
  
  inputOrigen.addEventListener("input", () => {
    const valor = inputOrigen.value.toLowerCase();
    listaSugerencias.innerHTML = "";
  
    if (!valor) return;
  
    const sugerencias = ciudadesUnicas.filter(ciudad =>
      ciudad.toLowerCase().includes(valor)
    ).slice(0, 10);
  
    sugerencias.forEach(ciudad => {
      const div = document.createElement("div");
      div.textContent = ciudad;
      div.addEventListener("click", () => {
        inputOrigen.value = ciudad;
        listaSugerencias.innerHTML = "";
      });
      listaSugerencias.appendChild(div);
    });
  });
  
  document.addEventListener("click", e => {
    if (!e.target.closest("#origenPersona")) {
      listaSugerencias.innerHTML = "";
    }
  });
  