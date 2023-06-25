//https://countriesnow.space/api/v0.1/countries/cities


//https://countriesnow.space/api/v0.1/countries/states

const countrySelect =document.getElementById("country");
const stateSelect =document.getElementById("state");
const citySelect =document.getElementById("city");

const fetchCountry = async () => {
 const data =await fetch("https://countriesnow.space/api/v0.1/countries")
 return data.json()
}

const fetchState = async (json) => {
    const data = await fetch("https://countriesnow.space/api/v0.1/countries/states",{
    method: "post",
    headers: {
          "Content-Type": "application/json",
    },
    body: JSON.stringify(json),
    })
    return data.json()
}

const fetchCities = async (json) => {
    const data = await fetch("https://countriesnow.space/api/v0.1/countries/cities",{
    method: "post",
    headers: {
          "Content-Type": "application/json",
    },
    body: JSON.stringify(json),
    })
    return data.json()
}

fetchCountry().then( (data) => {
   data.data.forEach( (currentValue, index) => {
            const node = document.createElement("option" );
            node.setAttribute("value",currentValue.country)
            const textnode = document.createTextNode(currentValue.country);
            node.appendChild(textnode);
            countrySelect.appendChild(node)
    })
})



function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}


countrySelect.addEventListener('change',(e) => {
    removeAllChildNodes(stateSelect)
   fetchState({
                   "country": e.target.value
               }).then( (data) => {

        if(data.error){
            alert("false")
        }else{
            data.data.states.forEach( (currentValue,index) => {
                            const node = document.createElement("option" );
                            node.setAttribute("value",currentValue.name)

                            const textnode = document.createTextNode(currentValue.name);
                            node.appendChild(textnode);
                            stateSelect.appendChild(node)
            })
        }
    })
})


stateSelect.addEventListener('change', (e) => {
   removeAllChildNodes(citySelect)
   fetchCities({
                   "country": countrySelect.value,
                   "state": e.target.value
               }).then( (data) => {

        if(data.error){
            alert("false")
        }else{
              data.data.forEach( (currentValue,index) => {
              const node = document.createElement("option" );
              node.setAttribute("value",currentValue)
              const textnode = document.createTextNode(currentValue);
              node.appendChild(textnode);
              citySelect.appendChild(node)
            })
        }
    })
})