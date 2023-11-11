async function populate() {
    console.log("here");
    const requestURL =
      "http://localhost:8080/LK/s";
    const request = new Request(requestURL);
  
    const response = await fetch(request);
    const superHeroes = await response.json();

    console.log(superHeroes);
    populateHeader(superHeroes);
  }
  function populateHeader(obj) {
    const header = document.querySelector("header");
    const myH1 = document.createElement("h1");
    myH1.textContent = obj.email;
    header.appendChild(myH1);
  
    const myPara = document.createElement("p");
    myPara.textContent = `Hometown: ${obj.email} // Formed: ${obj.email}`;
    header.appendChild(myPara);
  }
  populate();