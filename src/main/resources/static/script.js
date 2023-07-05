const formulario = document.querySelector("form");
const Inome = document.querySelector("#nome");
const Isenha = document.querySelector("#senha");
const Icpf = document.querySelector("#cpf");
const Itelefone = document.querySelector("#telefone");
const Icargo = document.querySelector("#cargo");

function cadastrar (){
    fetch("http://localhost:8080/colaboradores",
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            nome: Inome.value,
            senha: Isenha.value,
            cpf: Icpf.value,
            telefone: Itelefone.value,
            cargo: Icargo.value
        })
    })
    .then(function (res) {console.log("teste" + res);})
    .catch(function (res) {console.log(res)})
};

function listar (){
    fetch("http://localhost:8080/colaboradores")
        .then((res) => res.json())
        .then((data) => {
            data.content.forEach(user => {
                const markup = `<td style="width: 400px;">${user.cargo}</td><td style="width: 400px;">${user.nome} </td><td style="width: 120px;">${user.score}%</td><td id="forca" style="width: 145px;"> ${user.forca}</td>`;
                document.querySelector('table').insertAdjacentHTML('beforeend', markup);
                //Seleciona todos os elementos <td> do documento.
                let tds = document.querySelectorAll("#forca");

                //Para cada elemento <td>...
                tds.forEach((td) => {
                  //...Se texto for "D" colore em vermelho caso contrário colore em verde.
                  td.style.backgroundColor = (td.innerText == 'MUITO_FRACO') ? "red" :
                  td.style.backgroundColor = (td.innerText == 'FRACO') ? "#e18686" :
                  td.style.backgroundColor = (td.innerText == 'BOM') ? "#FFEB3B" :
                  td.style.backgroundColor = (td.innerText == 'FORTE') ? "#8BC34A" :
                  td.style.backgroundColor = (td.innerText == 'MUITO_FORTE') ? "#4CAF50" : null;
                });


            });
        })
        .catch(error => console.log(error));
};

function limpar(){
    Inome.value = "";
    Isenha.value = "";
    Icpf.value = "";
    Itelefone.value = "";
    Icargo.value = "";
}

const form = document.getElementById('form');
form.addEventListener('submit', function(){
    cadastrar();
});

listar();