//1
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

console.log(cookieArr[1])

//DOM Elements
const submitForm = document.getElementById("submit-button")
const noteContainer = document.getElementById("note-container")

//Modal Elements
let noteBody = document.getElementById("note-body")
let updateNoteBtn = document.getElementById("update-note-button")

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1"


//2
function handleLogout () {
    let c = document.cookie.split(";");
    for (let i in c) {
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

//3
const handleSubmit = async (e) => {
    e.preventDefault()
    let obj = document.getElementById("note-input").value
    const newFormat = {
        "index": obj
    }

    await addNote(obj);
    document.getElementById("note-input").value = ''
    
    async function addNote(obj) {

        const response = await fetch (baseUrl + `/library/user/` + userId , {
            method: "POST" ,
            body: JSON.stringify(newFormat)  ,
            headers: headers
            
        })

        .catch(err => console.error(err.message))
        if (response.status == 200) {
            return getNotes(userId);
        }
    }
getNotes(userId);
}
    
//4
async function getNotes(userId) {
    await fetch(baseUrl + `/library/user/` + userId , {
        method: "GET" ,
        headers: headers
    })
        .then(res => res.json())
        .then(data => createNoteCards(data))
        .catch(err => console.error(err))
}

//5
async function getNoteById(noteId){
    await fetch(baseUrl + `/library/` + noteId , {
        method: "GET" ,
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.err(err.message))
}

async function handleNoteEdit(noteId) {
    let bodyObj = {
        id: noteId ,
        body: noteBody.value
    }

    await fetch(baseUrl + `/library/` + noteId , {
        method: "PUT" ,
        body: JSON.stringify(bodyObj) ,
        headers: headers
    })
        .catch(err => console.error(err))

    return getNotes(userId);
}

//6
async function handleDelete(noteId){
    await fetch(baseUrl + `/library/` + noteId , {
        method: "DELETE" ,
        headers: headers
    })
        .catch(err => console.error(err))

        getNotes(userId)
    return 
}


//idkwtfigo
/*
innerhtml edit btn code
<button onclick="handleNoteEdit(${obj.index})" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="note-edit-modal">
Edit</button>
*/



const createNoteCards = (array) => {
    noteContainer.innerHTML = ''
    Array.from(array).forEach(obj => {
        let noteCard = document.createElement("div")
        noteCard.classList.add("m-2")
        noteCard.innerHTML = `
        <div class="card" style="background: #000a">
            <div class="index">
                <div class="card-text" >${obj.index}</div>
                <div style="">
                    <button class="btn-delete" 
                    onclick="handleDelete(${obj.id})" >X</button>
                    
                </div>
            </div>
        </div>
        `
        noteContainer.append(noteCard);
    })
}

const createFlashcards = (array) => {
    flashContainer.innerHTML = ''
    Array.from(array).forEach(obj => {
        let flashCard = document.createElement("div")
        flashCard.classList.add("m-3")
        flashCard.innerHTML = `
        <div class="card" style="background: #000a">
            <div class="index">
                <div class="card-text" >${obj.}
        `
    })
}

const populateModal = (obj) =>{
    noteBody.innerText = ''
    noteBody.innerText = obj.body
    updateNoteBtn.setAttribute('data-note-id' , obj.id)
}

getNotes(userId);

submitForm.addEventListener('click' , (e)=>{
    let noteId = e.target.getAttribute('data-note-id')
    handleSubmit(e)

})

// !submitForm.addEventListener('click' , (e)=>{
//     let noteId = e.target.getAttribute('data-note-id')
//     handleNoteEdit(noteId);
// })