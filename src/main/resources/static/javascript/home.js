//1
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("submit-button")
const noteContainer = document.getElementById("note-container")
const flashContainer = document.getElementById("flash-container")
const loginBtn = document.getElementById('')
const logoutBtn = document.getElementById('')
const registerBtn = document.getElementById('')

//Modal Elements
let noteBody = document.getElementById("note-body")
let updateNoteBtn = document.getElementById("update-note-button")

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1"

function doButtons () {
    if (cookieArr[1] != null) {
        getFlashcards(userId);
        getNotes(userId);
        // alert('logged in')

    } else {
        window.location.replace("http://localhost:8080/register.html")
        // alert('please sign in')
    }
}

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
        .catch(err => console.error(err.message))
}

async function getFlashcards(userId) {
    await fetch(baseUrl + `/flashcards/user/` + userId , {
        method: "GET" ,
        headers: headers
    })
        .then(res => res.json())
        .then(data => createFlashcards(data))
        .catch(err => console.error(err.message))
}

//5
async function getNoteById(noteId){
    await fetch(baseUrl + `/library/` + noteId , {
        method: "GET" ,
        headers: headers
    })
        .then(res => res.json())
        .then(data => makeFlash(data))
        .catch(err => console.error(err.message))
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
        .catch(err => console.error(err.message))

    return getNotes(userId);
}

//6
async function handleDelete(noteId){
    await fetch(baseUrl + `/library/` + noteId , {
        method: "DELETE" ,
        headers: headers
    })
        .catch(err => console.error(err.message))

        getNotes(userId)
    return 
}

async function handleDelete2(noteId){
    await fetch(baseUrl + `/flashcards/` + noteId , {
        method: "DELETE" ,
        headers: headers
    })
        .catch(err => console.error(err.message))

        getFlashcards(userId)
    return 
}

//7
function handlXfer(data) {
    getNoteById(data)
}

function makeFlash(data) {
    let splitArray = data.index.split("~")
    let flashCard = document.createElement("div")
        flashCard.classList.add("m-3")
        flashCard.innerHTML = 
        `
        <div class="flashcard">
            <div class="flash-index">
                <div class="flashcard-text" id="back-text">
                    ${splitArray[1]}
                </div>
                <div class="flashcard-text" id="front-text">
                    ${splitArray[0]}
            </div>
        </div>
        `
        addFlash(splitArray)
        flashContainer.append(flashCard);
    
}

async function addFlash(arr) {

    const frontBack = {
        "front": arr[0] ,
        "back": arr[1]
    }

    const response = await fetch (baseUrl + `/flashcards/user/` + userId , {
        method: "POST" ,
        body: JSON.stringify(frontBack)  ,
        headers: headers
    })
    .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getFlashcards(userId);
    } 
}





const createNoteCards = (array) => {
    noteContainer.innerHTML = ''
    Array.from(array).forEach(obj => {
        let noteCard = document.createElement("div")
        noteCard.classList.add("m-3")
        noteCard.innerHTML = `
        <div class="card" >
            <div class="index">
                <div class="card-text">

            ${obj.index}</div>
               
                <button class="btn-delete" 
                    onclick="handleDelete(${obj.id})" >
                    x</button>
                    
                    <button class="flashcardify" 
                    onClick="handlXfer(${obj.id})"
                    >+</button>
                    
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
        <div class="flashcard">
            <div class="flash-index">
                <div class="flashcard-text" id="back-text">
                    ${obj.back}
                </div>
                <div class="flashcard-text" id="front-text">
                    ${obj.front}
            </div>

            </div>
            <button class="btn-delete" 
            style="height: 3vh; width: 2vw;"
            onclick="handleDelete2(${obj.id})" >
            x</button>

        `
        flashContainer.append(flashCard);

    })
}

const populateModal = (obj) =>{
    noteBody.innerText = ''
    noteBody.innerText = obj.body
    updateNoteBtn.setAttribute('data-note-id' , obj.id)
}

doButtons();

submitForm.addEventListener('click' , (e)=>{
    let noteId = e.target.getAttribute('data-note-id')
    handleSubmit(e)
})
