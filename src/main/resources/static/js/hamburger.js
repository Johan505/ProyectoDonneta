const hamburgerMenu = document.getElementById('navbarNavAltMarkup');

hamburgerMenu.onclick = (e) => {
  const openClose = menu.classList[1]

  if (openClose === 'closed') {
    menu.classList.remove('closed')
    menu.classList.add('opened')
  } 
  else if (openClose === 'opened') {
    menu.classList.add('closed')
    menu.classList.remove('opened')
  }
}

const link= document.getElementById('linkPrueba');
link.onclick = () => {
    window.location.href = "/index";
  }
  const InicioS=document.getElementById('InicioSesion');

InicioS.onclick = () => {
    window.location.href = "/Login";
}