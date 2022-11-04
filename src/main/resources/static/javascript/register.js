const registerForm = document.getElementById('register-form')
const registerUsername = document.getElementById('register-username')
const registerPassword = document.getElementById('register-password')

const headers = {
        'Content-Type':'application/json'
}

const baseUrl = 'http://localhost"8008/users'





const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    }

    const response = await fetch (`${baseUrl}/register` , {
        method: "POST" ,
        body: JSON.stringify(bodyObj) ,
        headers: headers
    })

    .catch(err => console.error(err.message))


    const reponseArr = await response.json()

    if (response.stauts === 200) {
        window.location.replace(responseArr[0])
    }

//   I don't think we actually need this because of the switch to impl. we shall see window.location.href = 'http://localhost"8008/home.html'
}


registerForm.addEventListener("submit" , handleSubmit)